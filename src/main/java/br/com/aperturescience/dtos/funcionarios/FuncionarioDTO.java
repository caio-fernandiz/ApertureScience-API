package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.infra.security.UserRole;
import br.com.aperturescience.models.Funcionario;

public record FuncionarioDTO(Long id, String nome, Integer idade, String cpf, String email, 
    String telefone, UserRole cargo, Integer nivelAcesso, String senha, String codigoLogin) {

    public FuncionarioDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getIdade(), funcionario.getCpf(), funcionario.getEmail(), 
        funcionario.getTelefone(), funcionario.getCargo(), funcionario.getNivelAcesso(), funcionario.getSenha(), funcionario.getCodigoLogin());
    }
}
