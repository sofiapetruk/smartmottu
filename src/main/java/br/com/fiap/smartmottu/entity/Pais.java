package br.com.fiap.smartmottu.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "T_SMARTMOTTU_PAIS")
@SequenceGenerator(name = "pais", sequenceName = "SQ_T_SMARTMOTTU_PAIS", allocationSize = 1)

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Pais {


    @Id
    @Column(name = "id_pais")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pais")
    private Long idPais;

    @NotBlank @Size(max = 100)
    @Column(name = "nm_Pais")
    private String nmPais;
}
