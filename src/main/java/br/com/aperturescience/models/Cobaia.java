package br.com.aperturescience.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Cobaia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer idade;
    private String cpf;
    private String altura;
    private String tipoSanguineo;
    private Integer qi;
    private Integer resultadoTesteResistencia;
    private Integer resultadoTesteForca;
    private Integer resultadoTesteVelocidade;
    private String formacaoAcademica;
}
