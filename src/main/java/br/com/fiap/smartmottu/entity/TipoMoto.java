package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_TIPO_MOTO")
@SequenceGenerator(name = "tipoMoto", sequenceName = "SQ_T_SMARTMOTTU_TIPO_MOTO", allocationSize = 1)
@Setter

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TipoMoto {

    @Id
    @Column(name = "id_tipo")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipoMoto")
    private Long idTipo;

    @Column(name = "nm_tipo")
    private String nmTipo;
}
