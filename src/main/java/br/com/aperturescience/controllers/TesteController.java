package br.com.aperturescience.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aperturescience.dtos.testes.TesteDTO;
import br.com.aperturescience.dtos.testes.TesteDetailsDTO;
import br.com.aperturescience.dtos.testes.TesteFormDTO;
import br.com.aperturescience.dtos.testes.TesteFormUpdateDTO;
import br.com.aperturescience.services.interfaces.TesteService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/testes")
public class TesteController {

    private final TesteService testeService;

    @Autowired
    public TesteController(TesteService testeService) {
        this.testeService = testeService;
    }

    @PostMapping
    public ResponseEntity<TesteDTO> salvarTeste(@RequestBody TesteFormDTO testeFormDTO) {
        TesteDTO novoTesteDTO = testeService.salvarTeste(testeFormDTO);
        return new ResponseEntity<>(novoTesteDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TesteDTO>> listaTestes() {
        List<TesteDTO> testes = testeService.listaTestes();
        return new ResponseEntity<>(testes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TesteDetailsDTO> buscarTestePorId(@PathVariable Long id) {
        TesteDetailsDTO teste = testeService.buscarTestePorId(id);
        return new ResponseEntity<>(teste, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TesteDTO> atualizarTeste(@PathVariable Long id, @RequestBody TesteFormUpdateDTO updateForm) {
        TesteDTO testeAtualizado = testeService.atualizarTeste(id, updateForm);
        return ResponseEntity.ok(testeAtualizado);
    }

}
