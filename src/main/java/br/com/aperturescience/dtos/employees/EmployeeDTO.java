package br.com.aperturescience.dtos.employees;

import br.com.aperturescience.models.Employee;

public record EmployeeDTO(Long id, String name, Integer age, String cpf, String email, 
    String telefone, String role, Integer accessLevel, String psswrd, String loginCode) {

    public EmployeeDTO(Employee employee){
        this(employee.getId(), employee.getName(), employee.getAge(), employee.getCpf(), employee.getEmail(), 
        employee.getTelefone(), employee.getRole(), employee.getAccessLevel(), employee.getPsswrd(), employee.getLoginCode());
    }
}
