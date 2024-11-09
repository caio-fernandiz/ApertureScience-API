package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.cobaias.CobaiaDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaDetailsDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaFormDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaFormUpdateDTO;

public interface CobaiaService {

    CobaiaDTO salvarCobaia(CobaiaFormDTO form);

    List<CobaiaDTO> listaCobaias();

    CobaiaDetailsDTO buscarCobaiaPorId(Long id);

    CobaiaDTO atualizarCobaia(Long id, CobaiaFormUpdateDTO form);

    void excluirCobaia(Long id);
}
