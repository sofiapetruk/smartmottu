package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_PATIO")
@SequenceGenerator(name = "patio", sequenceName = "SQ_T_SMARTMOTTU_PATIO", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Patio {


    @Id
    @Column(name = "id_patio")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patio")
    private Long idPatio;

    @ManyToOne
    @JoinColumn(name = "id_endereco")
    private  Endereco endereco;


}
