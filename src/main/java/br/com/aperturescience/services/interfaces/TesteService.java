package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.testes.TesteDTO;
import br.com.aperturescience.dtos.testes.TesteDetailsDTO;
import br.com.aperturescience.dtos.testes.TesteFormDTO;
import br.com.aperturescience.dtos.testes.TesteFormUpdateDTO;

public interface TesteService {

    TesteDTO salvarTeste(TesteFormDTO form);

    List<TesteDTO> listaTestes();
    
    TesteDetailsDTO buscarTestePorId(Long id);

    TesteDTO atualizarTeste(Long id, TesteFormUpdateDTO form);
}
