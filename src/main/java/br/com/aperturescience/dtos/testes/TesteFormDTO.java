package br.com.aperturescience.dtos.testes;

import br.com.aperturescience.models.Teste;

public record TesteFormDTO(String camaraAtual, String objetivoTeste, String descricaoTeste, String anotacoes,
                            Integer quantidadeTestes, String resultadoAtual, Integer baixas) {

    public Teste toEntity(){
        return new Teste(null, camaraAtual, objetivoTeste, descricaoTeste, 
        anotacoes, quantidadeTestes, resultadoAtual, baixas);
    }
}
