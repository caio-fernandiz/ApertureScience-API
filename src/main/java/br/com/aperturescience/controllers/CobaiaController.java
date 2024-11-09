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

import br.com.aperturescience.dtos.cobaias.CobaiaDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaDetailsDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaFormDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaFormUpdateDTO;
import br.com.aperturescience.services.interfaces.CobaiaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/as/cobaias")
public class CobaiaController {

    private final CobaiaService cobaiaService;

    @Autowired
    public CobaiaController(CobaiaService cobaiaService){
        this.cobaiaService = cobaiaService;
    }

    @PostMapping
public ResponseEntity<CobaiaDTO> salvarCobaia(@RequestBody CobaiaFormDTO cobaiaFormDTO) {
    CobaiaDTO novaCobaiaDTO = cobaiaService.salvarCobaia(cobaiaFormDTO);
    return new ResponseEntity<>(novaCobaiaDTO, HttpStatus.CREATED);
}

@GetMapping
public ResponseEntity<List<CobaiaDTO>> listaCobaias() {
    List<CobaiaDTO> cobaias = cobaiaService.listaCobaias();
    return new ResponseEntity<>(cobaias, HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<CobaiaDetailsDTO> buscarCobaiaPorId(@PathVariable Long id) {
    CobaiaDetailsDTO cobaia = cobaiaService.buscarCobaiaPorId(id);
    return new ResponseEntity<>(cobaia, HttpStatus.OK);
}

@PutMapping("/{id}")
public ResponseEntity<CobaiaDTO> atualizarCobaia(@PathVariable Long id, @RequestBody CobaiaFormUpdateDTO updateForm) {
    CobaiaDTO cobaiaAtualizada = cobaiaService.atualizarCobaia(id, updateForm);
    return ResponseEntity.ok(cobaiaAtualizada);
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> excluirCobaia(@PathVariable Long id) {
    cobaiaService.excluirCobaia(id);
    return ResponseEntity.noContent().build();
}


}
