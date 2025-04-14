package br.com.aperturescience.dtos.employees;

import br.com.aperturescience.infra.user.UserRole;

public record EmployeeFormUpdateDTO(String name, Integer age, String cpf, String email, 
    String telephone, UserRole role, Integer accessLevel, String psswrd, String loginCode) {

}
