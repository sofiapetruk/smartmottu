package br.com.fiap.smartmottu.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MotoDto {

    @NotBlank
    @Size(min = 17, max = 17)
    private String nmChassi;

    @NotBlank @Size(min = 7, max = 7)
    private String placa;

    @NotBlank
    private String status;

    @NotBlank
    private String modelo;

    @NotBlank
    private String unidade;



}
