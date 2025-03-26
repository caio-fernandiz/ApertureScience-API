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

import br.com.aperturescience.dtos.Tests.TestDTO;
import br.com.aperturescience.dtos.Tests.TestDetailsDTO;
import br.com.aperturescience.dtos.Tests.TestFormDTO;
import br.com.aperturescience.dtos.Tests.TestFormUpdateDTO;
import br.com.aperturescience.services.interfaces.TestService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/Tests")
public class TestController {

    private final TestService TestService;

    @Autowired
    public TestController(TestService TestService) {
        this.TestService = TestService;
    }

    @PostMapping
    public ResponseEntity<TestDTO> salvarTest(@RequestBody TestFormDTO TestFormDTO) {
        TestDTO novoTestDTO = TestService.salvarTest(TestFormDTO);
        return new ResponseEntity<>(novoTestDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TestDTO>> listaTests() {
        List<TestDTO> Tests = TestService.listaTests();
        return new ResponseEntity<>(Tests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDetailsDTO> buscarTestPorId(@PathVariable Long id) {
        TestDetailsDTO Test = TestService.buscarTestPorId(id);
        return new ResponseEntity<>(Test, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> atualizarTest(@PathVariable Long id, @RequestBody TestFormUpdateDTO updateForm) {
        TestDTO TestAtualizado = TestService.atualizarTest(id, updateForm);
        return ResponseEntity.ok(TestAtualizado);
    }

}
