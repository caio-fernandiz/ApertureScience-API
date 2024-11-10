package br.com.aperturescience.dtos.testes;

import br.com.aperturescience.models.Teste;

public record TesteDetailsDTO(String camaraAtual, String objetivoTeste, String descricaoTeste, String anotacoes,
                                Integer quantidadeTestes, String resultadoAtual, Integer baixas) {

     public TesteDetailsDTO(Teste Teste){
        this(Teste.getCamaraAtual(), Teste.getObjetivoTeste(), Teste.getDescricaoTeste(),
        Teste.getAnotacoes(), Teste.getQuantidadeTestes(), Teste.getResultadoAtual(), Teste.getBaixas());
    }

}
