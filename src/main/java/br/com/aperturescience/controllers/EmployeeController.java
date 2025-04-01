package br.com.aperturescience.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aperturescience.dtos.employees.EmployeeDTO;
import br.com.aperturescience.dtos.employees.EmployeeDetailsDTO;
import br.com.aperturescience.dtos.employees.EmployeeFormDTO;
import br.com.aperturescience.dtos.employees.EmployeeFormUpdateDTO;
import br.com.aperturescience.services.interfaces.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> salveEmployee(@RequestBody EmployeeFormDTO employeeFormDTO){
        EmployeeDTO newEmployeeDTO = employeeService.saveEmployee(employeeFormDTO);
        return new ResponseEntity<>(newEmployeeDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listEmployees(){
        List<EmployeeDTO> employees = employeeService.listEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailsDTO> findEmployeeById(@PathVariable Long id){
        EmployeeDetailsDTO employee = employeeService.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeFormUpdateDTO updateForm){
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(id, updateForm);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
