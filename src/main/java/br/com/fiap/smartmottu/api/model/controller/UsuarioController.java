package br.com.fiap.smartmottu.api.model.controller;

import br.com.fiap.smartmottu.dto.LoginRequestDto;
import br.com.fiap.smartmottu.dto.UsuarioRequestDto;
import br.com.fiap.smartmottu.dto.UsuarioResponseDto;
import br.com.fiap.smartmottu.entity.Usuario;
import br.com.fiap.smartmottu.exception.IdNotFoundException;
import br.com.fiap.smartmottu.repository.UsuarioRepository;
import br.com.fiap.smartmottu.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UsuarioController implements UsuarioAPi {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepo;


    @Override
    public ResponseEntity<List<UsuarioResponseDto>> listAll() {
        List<Usuario> usuarios = usuarioService.findAll(
                Sort.by(Sort.Direction.ASC, "idUsuario")
        );

        List<UsuarioResponseDto> responseDtos = usuarios.stream()
                .map(usuario -> new UsuarioResponseDto(
                        usuario.getIdUsuario(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getSenha()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseDtos);

    }

    @Override
    public ResponseEntity<UsuarioResponseDto> getById(@PathVariable Long idUsuario) throws IdNotFoundException {

        return usuarioService.findById(idUsuario)
                .map(usuario -> ResponseEntity.ok(new UsuarioResponseDto(
                        usuario.getIdUsuario(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getSenha())))
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> create(@Valid @RequestBody UsuarioRequestDto request) {

        Usuario usuario = new Usuario(
                request.getNome(),
                request.getEmail(),
                request.getSenha()
        );

        Usuario savedUsuario = usuarioService.save(usuario);

        UsuarioResponseDto response = new UsuarioResponseDto(
                savedUsuario.getIdUsuario(),
                savedUsuario.getNome(),
                savedUsuario.getEmail(),
                savedUsuario.getSenha()
        );

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<UsuarioResponseDto> update(Long idUsuario,
                                          @Valid @RequestBody UsuarioRequestDto request) {
        return usuarioService.findById(idUsuario)
                .map(usuario -> {
                    usuario.setNome(request.getNome());
                    usuario.setEmail(request.getEmail());
                    usuario.setSenha(request.getSenha());

                    Usuario updateUser = usuarioService.save(usuario);

                    return ResponseEntity.ok(new UsuarioResponseDto(updateUser.getIdUsuario(),updateUser.getNome(), updateUser.getEmail(), updateUser.getSenha()));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Void> delete(@PathVariable Long idUsuario) throws IdNotFoundException {
        if (usuarioService.findById(idUsuario).isPresent()) {
            usuarioService.delete(idUsuario);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        boolean valido = usuarioRepo.existsByEmailAndSenha(
                loginRequest.getEmail(),
                loginRequest.getSenha()
        );
        if (valido) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/paginacao")
    public ResponseEntity<Page<UsuarioResponseDto>> findAllPage(
            @RequestParam(value = "pagina", defaultValue = "0") Integer page,
            @RequestParam(value = "tamanho", defaultValue = "2") Integer size) {

        PageRequest pageRequest = PageRequest.of(page, size);

        Page<Usuario> paginacaoUsuario = usuarioService.findAllPage(pageRequest);

        Page<UsuarioResponseDto> paginacaoUsuarioDto = paginacaoUsuario.map(usuario ->
                new UsuarioResponseDto(
                        usuario.getIdUsuario(),
                        usuario.getNome(),
                        usuario.getEmail(),
                        usuario.getSenha()
                )
        );

        return ResponseEntity.ok(paginacaoUsuarioDto);
    }

}