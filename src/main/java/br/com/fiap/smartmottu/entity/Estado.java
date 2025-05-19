package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_ESTADO")
@SequenceGenerator(name = "estado", sequenceName = "SQ_T_SMARTMOTTU_ESTADO", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Estado {

    @Id
    @Column(name = "id_estado")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "estado")
    private Long idEstado;

    @NotBlank @Size(min = 2, max = 2)
    @Column(name = "nm_estado")
    private String nmEstado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_id_pais", referencedColumnName = "id_pais")
    private  Pais pais;



}
