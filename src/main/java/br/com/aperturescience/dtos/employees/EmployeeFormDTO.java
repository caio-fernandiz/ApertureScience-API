package br.com.aperturescience.dtos.employees;

import br.com.aperturescience.models.Employee;

public record EmployeeFormDTO(String name, Integer age, String cpf, String email,
        String telephone, String role, Integer accessLevel, String psswrd, String loginCode) {

    public Employee toEntity() {
        return new Employee(null, name, age, cpf, email, telephone, role, accessLevel, psswrd, loginCode);
    }
}
