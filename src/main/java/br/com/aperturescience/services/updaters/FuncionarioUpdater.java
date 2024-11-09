package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.funcionarios.FuncionarioFormUpdateDTO;
import br.com.aperturescience.models.Funcionario;

@Component
public class FuncionarioUpdater {

    public void atualizarFuncionario(Funcionario funcionario, FuncionarioFormUpdateDTO form){
        if (form.nome() != null) funcionario.setNome(form.nome());
        if (form.cargo() !=null) funcionario.setCargo(form.cargo());
    }
}
