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
public class Teste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String camaraAtual;
    private String titulo;
    private String objetivoTeste;
    private String descricaoTeste;
    private String anotacoes;
    private Integer quantidadeTestes;
    private String resultadoAtual;
    private Integer baixas;

}
