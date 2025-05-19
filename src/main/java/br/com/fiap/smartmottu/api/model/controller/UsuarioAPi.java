package br.com.fiap.smartmottu.api.model.controller;

import br.com.fiap.smartmottu.api.model.LoginDto;
import br.com.fiap.smartmottu.entity.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/usuarios")
@Tag(name = "Usuários", description = "CRUD de usuários")
public interface UsuarioAPi {


    @Operation(summary = "Listar todos os usuários")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lista retornada com sucesso")
    })
    @GetMapping
    ResponseEntity<List<Usuario>> listAll();

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário encontrado"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{idUsuario}")
    ResponseEntity<Usuario> getById(@PathVariable("idUsuario") Long idUsuario);

    @Operation(summary = "Criar um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    ResponseEntity<Usuario> create(@Valid @RequestBody Usuario usuario);

    @Operation(summary = "Atualizar um usuário existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{idUsuario}")
    ResponseEntity<Usuario> update(
            @PathVariable("idUsuario") Long idUsuario,
            @Valid @RequestBody Usuario usuario
    );

    @Operation(summary = "Excluir um usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @DeleteMapping("/{idUsuario}")
    ResponseEntity<Void> delete(@PathVariable("idUsuario") Long idUsuario);


    @Operation(summary = "Fazer login de usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login bem-sucedido"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    ResponseEntity<Void> login(@Valid @RequestBody LoginDto loginRequest);

}
