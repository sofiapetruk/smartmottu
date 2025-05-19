package br.com.fiap.smartmottu.api.model;

import lombok.Getter;

@Getter
public enum TipoMotoEnum {

    MOTTU_SPORT_110I(1, "Mottu Sport 110i"),
    MOTTU_E(2, "Mottu-e"),
    MOTTU_POP(3, "Mottu Pop");



    private final int id;
    private  final String descricao;

    TipoMotoEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

}
