package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.funcionarios.EmployeeDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeDetailsDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeFormDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeFormUpdateDTO;
import br.com.aperturescience.models.Employee;
import br.com.aperturescience.repositories.EmployeeRepository;
import br.com.aperturescience.services.interfaces.EmployeeService;
import br.com.aperturescience.services.updaters.EmployeeUpdater;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository EmployeeRepository;
    private final EmployeeUpdater EmployeeUpdater;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository EmployeeRepository, EmployeeUpdater EmployeeUpdater) {
        this.EmployeeRepository = EmployeeRepository;
        this.EmployeeUpdater = EmployeeUpdater;
    }

    @Override
    public EmployeeDTO salvarEmployee(EmployeeFormDTO form) {
        Employee Employee = form.toEntity();
        return new EmployeeDTO(EmployeeRepository.save(Employee));
    }

    @Override
    public List<EmployeeDTO> listaEmployees() {
        return EmployeeRepository.findAll().stream().map(EmployeeDTO::new).collect(Collectors.toList());
    }

    @Override
    public EmployeeDetailsDTO buscarEmployeePorId(Long id) {
        Employee Employee = EmployeeRepository.findById(id).orElse(null);
        return Employee != null ? new EmployeeDetailsDTO(Employee) : null;
    }

    @Override
    public EmployeeDTO atualizarEmployee(Long id, EmployeeFormUpdateDTO form) {
        Employee Employee = EmployeeRepository.findById(id).orElseThrow(RuntimeException::new);
        EmployeeUpdater.atualizarEmployee(Employee, form);
        return new EmployeeDTO(EmployeeRepository.save(Employee));
    }

    @Override
    public void excluirEmployee(Long id) {
        EmployeeRepository.deleteById(id);
    }

    
}
