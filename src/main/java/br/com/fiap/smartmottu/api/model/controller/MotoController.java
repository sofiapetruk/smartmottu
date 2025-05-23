package br.com.fiap.smartmottu.api.model.controller;

import br.com.fiap.smartmottu.dto.MotoRequestDto;
import br.com.fiap.smartmottu.dto.MotoResponseDto;
import br.com.fiap.smartmottu.entity.Moto;
import br.com.fiap.smartmottu.entity.StatusMoto;
import br.com.fiap.smartmottu.entity.TipoMoto;
import br.com.fiap.smartmottu.exception.IdNotFoundException;
import br.com.fiap.smartmottu.repository.MotoRepository;
import br.com.fiap.smartmottu.repository.StatusMotoRepository;
import br.com.fiap.smartmottu.repository.TipoMotoRepository;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class MotoController implements MotoAPi {

    @Autowired
    private MotoRepository motoRepository;

    @Autowired
    private StatusMotoRepository statusRepository;

    @Autowired
    private TipoMotoRepository tipoMotoRepository;


    @Override
    public ResponseEntity<List<MotoResponseDto>> listAll() {
        List<MotoResponseDto> resp = motoRepository.findAll().stream()
                .map(m -> new MotoResponseDto(
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
    public ResponseEntity<MotoResponseDto> getById(Long idMoto) throws IdNotFoundException {
        return motoRepository.findById(idMoto)
                .map(m -> new MotoResponseDto(
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
    public ResponseEntity<Moto> create(@Valid @RequestBody MotoRequestDto motoRequestDto) {

        StatusMoto status = statusRepository.findByStatus(motoRequestDto.getStatus())
                .orElseGet(() -> {
                    StatusMoto s = new StatusMoto();
                    s.setStatus(motoRequestDto.getStatus());
                    s.setData(LocalDate.now());
                    return statusRepository.save(s);
                });

        TipoMoto tipo = tipoMotoRepository.findByNmTipo(motoRequestDto.getModelo())
                .orElseGet(() -> {
                    TipoMoto t = new TipoMoto();
                    t.setNmTipo(motoRequestDto.getModelo());
                    return tipoMotoRepository.save(t);
                });

        Moto moto = new Moto();
        moto.setNmChassi(motoRequestDto.getNmChassi());
        moto.setPlaca(motoRequestDto.getPlaca());
        moto.setUnidade(motoRequestDto.getUnidade());
        moto.setStatus(status);
        moto.setModelo(tipo);


        Moto saved = motoRepository.save(moto);
        return ResponseEntity.ok(saved);
    }

    @Override
    public ResponseEntity<Moto> update(@PathVariable Long idMoto, @Valid @RequestBody MotoRequestDto motoRequestDto) throws IdNotFoundException {
        return motoRepository.findById(idMoto)
                .map(existingMoto -> {

                    StatusMoto status = statusRepository.findByStatus(motoRequestDto.getStatus())
                            .orElseGet(() -> {
                                StatusMoto s = new StatusMoto();
                                s.setStatus(motoRequestDto.getStatus());
                                s.setData(LocalDate.now());
                                return statusRepository.save(s);
                            });

                    TipoMoto tipo = tipoMotoRepository.findByNmTipo(motoRequestDto.getModelo())
                            .orElseGet(() -> {
                                TipoMoto t = new TipoMoto();
                                t.setNmTipo(motoRequestDto.getModelo());
                                return tipoMotoRepository.save(t);
                            });

                    existingMoto.setNmChassi(motoRequestDto.getNmChassi());
                    existingMoto.setPlaca(motoRequestDto.getPlaca());
                    existingMoto.setUnidade(motoRequestDto.getUnidade());
                    existingMoto.setStatus(status);
                    existingMoto.setModelo(tipo);

                    Moto updated = motoRepository.save(existingMoto);
                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long idMoto) throws IdNotFoundException {
        Moto moto = motoRepository.findById(idMoto).orElse(null);

        if (moto == null) {
            return ResponseEntity.notFound().build();
        }

        motoRepository.delete(moto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginacao")
    public ResponseEntity<Page<MotoResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Moto> motosPage = motoRepository.findAll(pageable);

        Page<MotoResponseDto> motosDtoPage = motosPage.map(m -> new MotoResponseDto(
                m.getIdMoto(),
                m.getNmChassi(),
                m.getPlaca(),
                m.getUnidade(),
                m.getStatus().getStatus(),
                m.getModelo().getNmTipo()
        ));

        return ResponseEntity.ok(motosDtoPage);
    }

}

