package br.com.aperturescience.dtos.testes;

import br.com.aperturescience.models.Teste;

public record TesteDTO(Long id, String camaraAtual, String titulo, String objetivoTeste, String descricaoTeste, String anotacoes,
Integer quantidadeTestes, String resultadoAtual, Integer baixass) {

    public TesteDTO(Teste Teste){
        this(Teste.getId(), Teste.getCamaraAtual(), Teste.getTitulo(), Teste.getObjetivoTeste(), Teste.getDescricaoTeste(),
        Teste.getAnotacoes(), Teste.getQuantidadeTestes(), Teste.getResultadoAtual(), Teste.getBaixas());
    }
}
