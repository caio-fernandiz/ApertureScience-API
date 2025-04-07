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

import br.com.aperturescience.dtos.tests.TestDTO;
import br.com.aperturescience.dtos.tests.TestDetailsDTO;
import br.com.aperturescience.dtos.tests.TestFormDTO;
import br.com.aperturescience.dtos.tests.TestFormUpdateDTO;
import br.com.aperturescience.services.interfaces.TestService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/tests")
public class TestController {

    private final TestService TestService;

    @Autowired
    public TestController(TestService TestService) {
        this.TestService = TestService;
    }

    @PostMapping
    public ResponseEntity<TestDTO> saveTest(@RequestBody TestFormDTO TestFormDTO) {
        TestDTO novoTestDTO = TestService.saveTest(TestFormDTO);
        return new ResponseEntity<>(novoTestDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TestDTO>> listTests() {
        List<TestDTO> Tests = TestService.listTests();
        return new ResponseEntity<>(Tests, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDetailsDTO> findTestById(@PathVariable Long id) {
        TestDetailsDTO Test = TestService.findTestById(id);
        return new ResponseEntity<>(Test, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestDTO> updateTest(@PathVariable Long id, @RequestBody TestFormUpdateDTO updateForm) {
        TestDTO updatedTest = TestService.updateTest(id, updateForm);
        return ResponseEntity.ok(updatedTest);
    }

}
