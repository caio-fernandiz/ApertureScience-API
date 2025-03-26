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

import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigDetailsDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigFormDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigFormUpdateDTO;
import br.com.aperturescience.services.interfaces.GuinneaPigService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/GuinneaPigs")
public class GuinneaPigController {

    private final GuinneaPigService GuinneaPigService;

    @Autowired
    public GuinneaPigController(GuinneaPigService GuinneaPigService){
        this.GuinneaPigService = GuinneaPigService;
    }

    @PostMapping
public ResponseEntity<GuinneaPigDTO> salvarGuinneaPig(@RequestBody GuinneaPigFormDTO GuinneaPigFormDTO) {
    GuinneaPigDTO novaGuinneaPigDTO = GuinneaPigService.salvarGuinneaPig(GuinneaPigFormDTO);
    return new ResponseEntity<>(novaGuinneaPigDTO, HttpStatus.CREATED);
}

@GetMapping
public ResponseEntity<List<GuinneaPigDTO>> listaGuinneaPigs() {
    List<GuinneaPigDTO> GuinneaPigs = GuinneaPigService.listaGuinneaPigs();
    return new ResponseEntity<>(GuinneaPigs, HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<GuinneaPigDetailsDTO> buscarGuinneaPigPorId(@PathVariable Long id) {
    GuinneaPigDetailsDTO GuinneaPig = GuinneaPigService.buscarGuinneaPigPorId(id);
    return new ResponseEntity<>(GuinneaPig, HttpStatus.OK);
}

@PutMapping("/{id}")
public ResponseEntity<GuinneaPigDTO> atualizarGuinneaPig(@PathVariable Long id, @RequestBody GuinneaPigFormUpdateDTO updateForm) {
    GuinneaPigDTO GuinneaPigAtualizada = GuinneaPigService.atualizarGuinneaPig(id, updateForm);
    return ResponseEntity.ok(GuinneaPigAtualizada);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> excluirGuinneaPig(@PathVariable Long id) {
    GuinneaPigService.excluirGuinneaPig(id);
    return ResponseEntity.noContent().build();
}


}
