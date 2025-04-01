package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.guineaPigs.GuineaPigDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigDetailsDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigFormDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigFormUpdateDTO;

public interface GuineaPigService {

    GuineaPigDTO saveGuineaPig(GuineaPigFormDTO form);

    List<GuineaPigDTO> listGuineaPigs();

    GuineaPigDetailsDTO findrGuineaPigById(Long id);

    GuineaPigDTO updateGuineaPig(Long id, GuineaPigFormUpdateDTO form);

    void deleteGuineaPig(Long id);
}
