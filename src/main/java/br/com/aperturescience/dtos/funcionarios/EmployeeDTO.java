package br.com.aperturescience.dtos.funcionarios;

import br.com.aperturescience.infra.user.UserRole;
import br.com.aperturescience.models.Employee;

public record EmployeeDTO(Long id, String name, Integer age, String cpf, String email, 
    String telephone, UserRole role, Integer acessLvl, String psswrd, String loginCode) {

    public EmployeeDTO(Employee Employee){
        this(Employee.getId(), Employee.getName(), Employee.getAge(), Employee.getCpf(), Employee.getEmail(), 
        Employee.getTelephone(), Employee.getRole(), Employee.getAcessLvl(), Employee.getPsswrd(), Employee.getLoginCode());
    }
}
