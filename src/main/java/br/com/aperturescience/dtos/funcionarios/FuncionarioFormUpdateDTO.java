package br.com.aperturescience.dtos.funcionarios;



public record FuncionarioFormUpdateDTO(String nome, Integer idade, String cpf, String email, 
    String telefone, String cargo, Integer nivelAcesso, String senha, String codigoLogin) {

}
