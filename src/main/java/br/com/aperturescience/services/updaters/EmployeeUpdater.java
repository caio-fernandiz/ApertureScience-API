package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.funcionarios.EmployeeFormUpdateDTO;
import br.com.aperturescience.infra.user.UserRole;
import br.com.aperturescience.models.Employee;

@Component
public class EmployeeUpdater {

    public void atualizarEmployee(Employee Employee, EmployeeFormUpdateDTO form) {
        if (form.name() != null)
            Employee.setName(form.name());
        if (form.age() != null)
            Employee.setAge(form.age());
        if (form.cpf() != null)
            Employee.setCpf(form.cpf());
        if (form.email() != null)
            Employee.setEmail(form.email());
        if (form.telephone() != null)
            Employee.setTelephone(form.telephone());
        if (form.role() != null)
            Employee.setRole(UserRole.valueOf(form.role()));
        if (form.acessLvl() != null)
            Employee.setAcessLvl(form.acessLvl());
        if (form.psswrd() != null)
            Employee.setPsswrd(form.psswrd());
        if (form.loginCode() != null)
            Employee.setLoginCode(form.loginCode());
    }
}
