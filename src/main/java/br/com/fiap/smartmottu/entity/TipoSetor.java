package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_TIPO_SETOR")
@SequenceGenerator(name = "tipo_setor", sequenceName = "SQ_T_SMARTMOTTU_TIPO_SETOR", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TipoSetor {

    @Id
    @Column(name = "id_tipo_setor")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_setor")
    private Long idSetor;

    @NotBlank(message = "O tipo de moto não pode ficar em branco") @Size(max = 100)
    @Column(name = "tp_setor")
    private String tpSetor;


}
