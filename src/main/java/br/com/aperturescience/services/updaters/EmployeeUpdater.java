package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.employees.EmployeeFormUpdateDTO;
import br.com.aperturescience.models.Employee;

@Component
public class EmployeeUpdater {

    public void updateEmployee(Employee employee, EmployeeFormUpdateDTO form) {
        if (form.name() != null)
            employee.setName(form.name());
        if (form.age() != null)
            employee.setAge(form.age());
        if (form.cpf() != null)
            employee.setCpf(form.cpf());
        if (form.email() != null)
            employee.setEmail(form.email());
        if (form.telephone() != null)
            employee.setTelephone(form.telephone());
        if (form.role() != null)
            employee.setRole(form.role());
        if (form.accessLevel() != null)
            employee.setAccessLevel(form.accessLevel());
        if (form.psswrd() != null)
            employee.setPsswrd(form.psswrd());
        if (form.loginCode() != null)
            employee.setLoginCode(form.loginCode());
    }
}
