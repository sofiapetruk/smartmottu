package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "T_SMARTMOTTU_ENDERECO")
@SequenceGenerator(name = "endereco", sequenceName = "SQ_T_SMARTMOTTU_ENDERECO", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {


    @Id
    @Column(name = "id_endereco")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "endereco")
    private Long idEndereco;

    @Column(name = "nr_cep")
    private int nrCep;

    @Column(name = "nm_logradouro")
    private String nmLogradouro;




}
