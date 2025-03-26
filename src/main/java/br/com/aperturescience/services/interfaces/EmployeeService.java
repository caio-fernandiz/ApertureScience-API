package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.funcionarios.EmployeeDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeDetailsDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeFormDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeFormUpdateDTO;

public interface EmployeeService {

    EmployeeDTO salvarEmployee(EmployeeFormDTO form);

    List<EmployeeDTO> listaEmployees();

    EmployeeDetailsDTO buscarEmployeePorId(Long id);

    EmployeeDTO atualizarEmployee(Long id, EmployeeFormUpdateDTO form);

    void excluirEmployee(Long id);
}
