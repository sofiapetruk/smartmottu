package br.com.fiap.smartmottu.api.model;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MotoResponse {

    private Long   idMoto;
    private String nmChassi;
    private String placa;
    private String unidade;
    private String status;
    private String modelo;

}
