package br.com.fiap.smartmottu.api.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, max = 15)
    private String senha;

}
