package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.employees.EmployeeDTO;
import br.com.aperturescience.dtos.employees.EmployeeDetailsDTO;
import br.com.aperturescience.dtos.employees.EmployeeFormDTO;
import br.com.aperturescience.dtos.employees.EmployeeFormUpdateDTO;

public interface EmployeeService {

    EmployeeDTO saveEmployee(EmployeeFormDTO form);

    List<EmployeeDTO> listEmployees();

    EmployeeDetailsDTO findEmployeeById(Long id);

    EmployeeDTO updateEmployee(Long id, EmployeeFormUpdateDTO form);

    void deleteEmployee(Long id);
}
