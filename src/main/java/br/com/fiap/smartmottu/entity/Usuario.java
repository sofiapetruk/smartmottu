package br.com.fiap.smartmottu.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_USUARIO")
@SequenceGenerator(name = "usuario", sequenceName = "SQ_T_SMARTMOTTU_USUARIO", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Usuario {

    @Id
    @Column(name = "id_usuario")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario")
    private Long idUsuario;

    @NotBlank
    @Column(name = "nome")
    private String nome;


    @Email @NotBlank
    @Column(name = "email")
    private String email;

    @Size(min = 8, max = 15)
    @NotBlank
    @Column(name = "senha")
    private String senha;



}
