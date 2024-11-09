package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.models.Funcionario;

public record FuncionarioDetailsDTO(Long id, String nome, String cargo) {

    public FuncionarioDetailsDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCargo());
    }

}
