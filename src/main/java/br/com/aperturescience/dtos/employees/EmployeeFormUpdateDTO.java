package br.com.aperturescience.dtos.employees;

public record EmployeeFormUpdateDTO(String name, Integer age, String cpf, String email, 
    String telefone, String role, Integer accessLevel, String psswrd, String loginCode) {

}
