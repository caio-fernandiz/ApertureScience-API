package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.models.Funcionario;

public record FuncionarioFormDTO(String nome, String cargo) {

    public Funcionario toEntity(){
        return new Funcionario(null, nome, cargo);
    }
}
