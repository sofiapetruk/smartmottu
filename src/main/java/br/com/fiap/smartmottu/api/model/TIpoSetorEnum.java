package br.com.fiap.smartmottu.api.model;

import lombok.Getter;

@Getter
public enum TIpoSetorEnum {

    PENDECINAS(1, "Pedências"),
    REPARO_SIMPLES(2, "Reparo Simples"),
    DANOS_ESTRUTURAIS_GRAVES(3, "Danos Estruturais Graves"),
    MOTOR_DEFEITUOSO(4, "Motor Defeituoso"),
    AGENDADA_PARA_MANUTENCAO(5, "Agenfafa para Manutenção"),
    PRONTO_PARA_ALUGUEL(6, "Pronto para Aluguel"),
    SEM_PLACA(7, "Sem Placa"),
    MINHA_MOTTU(8, "Minha Mottu"),
    OUTRO(9, "Outro");

    private final int id;
    private  final String descricao;

    TIpoSetorEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }
}
