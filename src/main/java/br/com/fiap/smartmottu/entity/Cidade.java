package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_CIDADE")
@SequenceGenerator(name = "cidade", sequenceName = "SQ_T_SMARTMOTTU_CIDADE", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cidade {

    @Id
    @Column(name = "id_cidade")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cidade")
    private Long idCidade;

    @NotBlank
    @Size(max = 100)
    @Column(name = "nm_cidade")
    private String nmCidade;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_id_estado", referencedColumnName = "id_estado")
    private Estado estado;



}
