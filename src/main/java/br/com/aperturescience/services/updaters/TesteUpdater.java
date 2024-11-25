package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.testes.TesteFormUpdateDTO;
import br.com.aperturescience.models.Teste;

@Component
public class TesteUpdater {

    public void atualizarTeste(Teste Teste, TesteFormUpdateDTO form) {
        if (form.camaraAtual() != null)
            Teste.setCamaraAtual(form.camaraAtual());
        if(form.titulo() != null)
            Teste.setTitulo(form.titulo());
        if (form.objetivoTeste() != null)
            Teste.setObjetivoTeste(form.objetivoTeste());
        if (form.descricaoTeste() != null)
            Teste.setDescricaoTeste(form.descricaoTeste());
        if (form.anotacoes() != null)
            Teste.setAnotacoes(form.anotacoes());
        if (form.quantidadeTestes() != null)
            Teste.setQuantidadeTestes(form.quantidadeTestes());
        if (form.resultadoAtual() != null)
            Teste.setResultadoAtual(form.resultadoAtual());
        if (form.baixas() != null)
            Teste.setBaixas(form.baixas());
    }
}
