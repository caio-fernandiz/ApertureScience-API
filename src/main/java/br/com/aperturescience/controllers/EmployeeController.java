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

import br.com.aperturescience.dtos.funcionarios.EmployeeDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeDetailsDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeFormDTO;
import br.com.aperturescience.dtos.funcionarios.EmployeeFormUpdateDTO;
import br.com.aperturescience.services.interfaces.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/Employees")
public class EmployeeController {

    private final EmployeeService EmployeeService;

    @Autowired
    public EmployeeController(EmployeeService EmployeeService){
        this.EmployeeService = EmployeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> salvarEmployee(@RequestBody EmployeeFormDTO EmployeeFormDTO){
        EmployeeDTO novoEmployeeDTO = EmployeeService.salvarEmployee(EmployeeFormDTO);
        return new ResponseEntity<>(novoEmployeeDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> listaEmployees(){
        List<EmployeeDTO> Employees = EmployeeService.listaEmployees();
        return new ResponseEntity<>(Employees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDetailsDTO> buscarEmployeePorId(@PathVariable Long id){
        EmployeeDetailsDTO Employee = EmployeeService.buscarEmployeePorId(id);
        return new ResponseEntity<>(Employee, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDTO> atualizarEmployee(@PathVariable Long id, @RequestBody EmployeeFormUpdateDTO updateForm){
        EmployeeDTO EmployeeAtualizado = EmployeeService.atualizarEmployee(id, updateForm);
        return ResponseEntity.ok(EmployeeAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEmployee(@PathVariable Long id){
        EmployeeService.excluirEmployee(id);
        return ResponseEntity.noContent().build();
    }

}
