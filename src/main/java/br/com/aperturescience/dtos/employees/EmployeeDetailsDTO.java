package br.com.aperturescience.dtos.employees;

import br.com.aperturescience.infra.user.UserRole;
import br.com.aperturescience.models.Employee;

public record EmployeeDetailsDTO(Long id, String name, Integer age, String cpf, String email, 
    String telephone, UserRole role, Integer accessLevel, String psswrd, String loginCode) {

    public EmployeeDetailsDTO(Employee employee){
        this(employee.getId(), employee.getName(), employee.getAge(), employee.getCpf(), employee.getEmail(), 
        employee.getTelephone(), employee.getRole(), employee.getAccessLevel(), employee.getPsswrd(), employee.getLoginCode());
    }

}
