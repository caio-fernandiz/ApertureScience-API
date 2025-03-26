package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.infra.user.UserRole;
import br.com.aperturescience.models.Employee;

public record EmployeeFormDTO(String name, Integer age, String cpf, String email,
        String telephone, UserRole role, Integer acessLvl, String psswrd, String loginCode) {

    public Employee toEntity() {
        return new Employee(null, name, age, cpf, email, telephone, role, acessLvl, psswrd, loginCode);
    }
}
