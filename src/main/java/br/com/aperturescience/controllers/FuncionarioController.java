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

import br.com.aperturescience.dtos.funcionarios.FuncionarioDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioDetailsDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioFormDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioFormUpdateDTO;
import br.com.aperturescience.services.interfaces.FuncionarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/funcionarios")
public class FuncionarioController {

    private final FuncionarioService funcionarioService;

    @Autowired
    public FuncionarioController(FuncionarioService funcionarioService){
        this.funcionarioService = funcionarioService;
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> salvarFuncionario(@RequestBody FuncionarioFormDTO funcionarioFormDTO){
        FuncionarioDTO novoFuncionarioDTO = funcionarioService.salvarFuncionario(funcionarioFormDTO);
        return new ResponseEntity<>(novoFuncionarioDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> listaFuncionarios(){
        List<FuncionarioDTO> funcionarios = funcionarioService.listaFuncionarios();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDetailsDTO> buscarFuncionarioPorId(@PathVariable Long id){
        FuncionarioDetailsDTO funcionario = funcionarioService.buscarFuncionarioPorId(id);
        return new ResponseEntity<>(funcionario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> atualizarFuncionario(@PathVariable Long id, @RequestBody FuncionarioFormUpdateDTO updateForm){
        FuncionarioDTO funcionarioAtualizado = funcionarioService.atualizarFuncionario(id, updateForm);
        return ResponseEntity.ok(funcionarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirFuncionario(@PathVariable Long id){
        funcionarioService.excluirFuncionario(id);
        return ResponseEntity.noContent().build();
    }

}
