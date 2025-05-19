package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_SETOR")
@SequenceGenerator(name = "setor", sequenceName = "SQ_T_SMARTMOTTU_SETOR", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Setor {

    @Id
    @Column(name = "id_setor")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "setor")
    private Long idSetor;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_id_patio", referencedColumnName = "id_patio")
    private Patio patio;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_id_tipo_setor", referencedColumnName = "id_tipo_setor")
    private TipoSetor tipoSetor;


}
