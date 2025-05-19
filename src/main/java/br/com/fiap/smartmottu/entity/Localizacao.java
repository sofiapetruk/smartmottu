package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_LOCALIZACAO")
@SequenceGenerator(name = "localizacao", sequenceName = "SQ_T_SMARTMOTTU_LOCALIZACAO", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {


    @Id
    @Column(name = "id_localizacao")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "localizacao")
    private Long idLocalizacao;


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_id_patio", referencedColumnName = "id_patio")
    private Patio patio;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_id_setor", referencedColumnName = "id_setor")
    private Setor setor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_id_endereco", referencedColumnName = "id_endereco")
    private  Endereco endereco;



}
