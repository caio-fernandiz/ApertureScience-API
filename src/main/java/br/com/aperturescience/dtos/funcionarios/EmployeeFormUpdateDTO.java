package br.com.aperturescience.dtos.funcionarios;



public record EmployeeFormUpdateDTO(String name, Integer age, String cpf, String email, 
    String telephone, String role, Integer acessLvl, String psswrd, String loginCode) {

}
