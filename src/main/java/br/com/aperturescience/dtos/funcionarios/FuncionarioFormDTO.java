package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.models.Funcionario;

public record FuncionarioFormDTO(String nome, Integer idade, String cpf, String email,
        String telefone, String cargo, Integer nivelAcesso, String senha, String codigoLogin) {

    public Funcionario toEntity() {
        return new Funcionario(null, nome, idade, cpf, email, telefone, cargo, nivelAcesso, senha, codigoLogin);
    }
}
