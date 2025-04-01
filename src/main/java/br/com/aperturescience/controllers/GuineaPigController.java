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

import br.com.aperturescience.dtos.guineaPigs.GuineaPigDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigDetailsDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigFormDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigFormUpdateDTO;
import br.com.aperturescience.services.interfaces.GuineaPigService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/guineaPigs")
public class GuineaPigController {

    private final GuineaPigService guineaPigService;

    @Autowired
    public GuineaPigController(GuineaPigService guineaPigService) {
        this.guineaPigService = guineaPigService;
    }

    @PostMapping
    public ResponseEntity<GuineaPigDTO> saveGuineaPig(@RequestBody GuineaPigFormDTO guineaPigFormDTO) {
        GuineaPigDTO newGuineaPigDTO = guineaPigService.saveGuineaPig(guineaPigFormDTO);
        return new ResponseEntity<>(newGuineaPigDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GuineaPigDTO>> listGuineaPigs() {
        List<GuineaPigDTO> guineaPigs = guineaPigService.listGuineaPigs();
        return new ResponseEntity<>(guineaPigs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GuineaPigDetailsDTO> findrGuineaPigById(@PathVariable Long id) {
        GuineaPigDetailsDTO guineaPig = guineaPigService.findrGuineaPigById(id);
        return new ResponseEntity<>(guineaPig, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GuineaPigDTO> updateGuineaPig(@PathVariable Long id,
            @RequestBody GuineaPigFormUpdateDTO updateForm) {
        GuineaPigDTO updatedGuineaPig = guineaPigService.updateGuineaPig(id, updateForm);
        return ResponseEntity.ok(updatedGuineaPig);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGuineaPig(@PathVariable Long id) {
        guineaPigService.deleteGuineaPig(id);
        return ResponseEntity.noContent().build();
    }

}
