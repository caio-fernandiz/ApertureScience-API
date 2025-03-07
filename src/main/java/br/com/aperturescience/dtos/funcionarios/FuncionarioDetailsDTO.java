package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.models.Funcionario;

public record FuncionarioDetailsDTO(Long id, String nome, Integer idade, String cpf, String email, 
    String telefone, String cargo, Integer nivelAcesso, String senha, String codigoLogin) {

    public FuncionarioDetailsDTO(Funcionario funcionario){
        this(funcionario.getId(), funcionario.getNome(), funcionario.getIdade(), funcionario.getCpf(), funcionario.getEmail(), 
        funcionario.getTelefone(), funcionario.getCargo(), funcionario.getNivelAcesso(), funcionario.getSenha(), funcionario.getCodigoLogin());
    }

}
