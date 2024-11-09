package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.models.Funcionario;

public record FuncionarioDTO(Long id, String nome, String cargo) {

    public FuncionarioDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getCargo());
    }
}
