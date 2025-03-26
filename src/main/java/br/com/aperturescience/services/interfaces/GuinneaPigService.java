package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigDetailsDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigFormDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigFormUpdateDTO;

public interface GuinneaPigService {

    GuinneaPigDTO salvarGuinneaPig(GuinneaPigFormDTO form);

    List<GuinneaPigDTO> listaGuinneaPigs();

    GuinneaPigDetailsDTO buscarGuinneaPigPorId(Long id);

    GuinneaPigDTO atualizarGuinneaPig(Long id, GuinneaPigFormUpdateDTO form);

    void excluirGuinneaPig(Long id);
}
