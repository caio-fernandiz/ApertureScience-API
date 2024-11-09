package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.models.Funcionario;

public record FuncionarioFormUpdateDTO(String nome, String cargo) {

    public FuncionarioFormUpdateDTO(Funcionario funcionario){
        this(funcionario.getNome(), funcionario.getCargo());
    }
}
