package br.com.fiap.smartmottu.api.model.controller;

import br.com.fiap.smartmottu.api.model.LoginDto;
import br.com.fiap.smartmottu.entity.Usuario;
import br.com.fiap.smartmottu.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController implements UsuarioAPi {

    @Autowired
    private UsuarioRepository usuarioRepo;


    @Override
    public ResponseEntity<List<Usuario>> listAll() {
        List<Usuario> usuarios = usuarioRepo.findAll(
                Sort.by(Sort.Direction.ASC, "idUsuario")
        );

        return ResponseEntity.ok(usuarios);
    }

    @Override
    public ResponseEntity<Usuario> getById(Long idUsuario) {
        return usuarioRepo.findById(idUsuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario) {
        Usuario saved = usuarioRepo.save(usuario);
        return ResponseEntity.ok().body(saved);
    }

    @Override
    public ResponseEntity<Usuario> update(Long idUsuario,
                                          @Valid @RequestBody Usuario usuario) {
        if (!usuarioRepo.existsById(idUsuario)) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdUsuario(idUsuario);
        Usuario updated = usuarioRepo.save(usuario);
        return ResponseEntity.ok().body(updated);
    }

    @Override
    public ResponseEntity<Void> delete(Long idUsuario) {
        if (!usuarioRepo.existsById(idUsuario)) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepo.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginRequest) {
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
}

