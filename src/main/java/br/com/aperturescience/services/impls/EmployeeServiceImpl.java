package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.employees.EmployeeDTO;
import br.com.aperturescience.dtos.employees.EmployeeDetailsDTO;
import br.com.aperturescience.dtos.employees.EmployeeFormDTO;
import br.com.aperturescience.dtos.employees.EmployeeFormUpdateDTO;
import br.com.aperturescience.models.Employee;
import br.com.aperturescience.repositories.EmployeeRepository;
import br.com.aperturescience.services.interfaces.EmployeeService;
import br.com.aperturescience.services.updaters.EmployeeUpdater;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeUpdater employeeUpdater;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeUpdater employeeUpdater) {
        this.employeeRepository = employeeRepository;
        this.employeeUpdater = employeeUpdater;
    }

    @Override
    public EmployeeDTO saveEmployee(EmployeeFormDTO form) {
        Employee employee = form.toEntity();
        return new EmployeeDTO(employeeRepository.save(employee));
    }

    @Override
    public List<EmployeeDTO> listEmployees() {
        return employeeRepository.findAll().stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    @Override
    public EmployeeDetailsDTO findEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        return employee != null ? new EmployeeDetailsDTO(employee) : null;
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeFormUpdateDTO form) {
        Employee Employee = employeeRepository.findById(id).orElseThrow(RuntimeException::new);
        employeeUpdater.updateEmployee(Employee, form);
        return new EmployeeDTO(employeeRepository.save(Employee));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

}
