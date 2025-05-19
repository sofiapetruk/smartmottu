package br.com.fiap.smartmottu.api.model.controller;

import br.com.fiap.smartmottu.api.model.MotoDto;
import br.com.fiap.smartmottu.api.model.MotoResponse;
import br.com.fiap.smartmottu.entity.Moto;
import br.com.fiap.smartmottu.entity.StatusMoto;
import br.com.fiap.smartmottu.entity.TipoMoto;
import br.com.fiap.smartmottu.repository.MotoRepository;
import br.com.fiap.smartmottu.repository.StatusMotoRepository;
import br.com.fiap.smartmottu.repository.TipoMotoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MotoController implements MotoAPi {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private StatusMotoRepository statusRepository;

//    @Autowired
//    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private TipoMotoRepository tipoMotoRepository;


    @Override
    public ResponseEntity<List<MotoResponse>> listAll() {
        List<MotoResponse> resp = motoRepository.findAll().stream()
                .map(m -> new MotoResponse(
                        m.getIdMoto(),
                        m.getNmChassi(),
                        m.getPlaca(),
                        m.getUnidade(),
                        m.getStatus().getStatus(),
                        m.getModelo().getNmTipo()
                ))
                .toList();
        return ResponseEntity.ok(resp);
    }

    @Override
    public ResponseEntity<MotoResponse> getById(Long idMoto) {
        return motoRepository.findById(idMoto)
                .map(m -> new MotoResponse(
                        m.getIdMoto(),
                        m.getNmChassi(),
                        m.getPlaca(),
                        m.getUnidade(),
                        m.getStatus().getStatus(),
                        m.getModelo().getNmTipo()
                ))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Moto> create(@Valid @RequestBody MotoDto motoDto) {

        StatusMoto status = statusRepository.findByStatus(motoDto.getStatus())
                .orElseGet(() -> {
                    StatusMoto s = new StatusMoto();
                    s.setStatus(motoDto.getStatus());
                    s.setData(LocalDate.now());
                    return statusRepository.save(s);
                });

        TipoMoto tipo = tipoMotoRepository.findByNmTipo(motoDto.getModelo())
                .orElseGet(() -> {
                    TipoMoto t = new TipoMoto();
                    t.setNmTipo(motoDto.getModelo());
                    return tipoMotoRepository.save(t);
                });

        Moto moto = new Moto();
        moto.setNmChassi(motoDto.getNmChassi());
        moto.setPlaca(motoDto.getPlaca());
        moto.setUnidade(motoDto.getUnidade());
        moto.setStatus(status);
        moto.setModelo(tipo);


        Moto saved = motoRepository.save(moto);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Moto> update(@PathVariable Long idMoto, @Valid @RequestBody MotoDto motoDto) {
        return motoRepository.findById(idMoto)
                .map(existingMoto -> {

                    StatusMoto status = statusRepository.findByStatus(motoDto.getStatus())
                            .orElseGet(() -> {
                                StatusMoto s = new StatusMoto();
                                s.setStatus(motoDto.getStatus());
                                s.setData(LocalDate.now());
                                return statusRepository.save(s);
                            });

                    TipoMoto tipo = tipoMotoRepository.findByNmTipo(motoDto.getModelo())
                            .orElseGet(() -> {
                                TipoMoto t = new TipoMoto();
                                t.setNmTipo(motoDto.getModelo());
                                return tipoMotoRepository.save(t);
                            });

                    existingMoto.setNmChassi(motoDto.getNmChassi());
                    existingMoto.setPlaca(motoDto.getPlaca());
                    existingMoto.setUnidade(motoDto.getUnidade());
                    existingMoto.setStatus(status);
                    existingMoto.setModelo(tipo);

                    Moto updated = motoRepository.save(existingMoto);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(Long idMoto) {
        Moto moto = motoRepository.findById(idMoto).orElse(null);

        if (moto == null) {
            return  ResponseEntity.notFound().build();
        }

        motoRepository.delete(moto);
        return ResponseEntity.noContent().build();
    }
}
