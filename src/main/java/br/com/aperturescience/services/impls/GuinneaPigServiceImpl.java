package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigDetailsDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigFormDTO;
import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigFormUpdateDTO;
import br.com.aperturescience.models.GuinneaPig;
import br.com.aperturescience.repositories.GuinneaPigRepository;
import br.com.aperturescience.services.interfaces.GuinneaPigService;
import br.com.aperturescience.services.updaters.GuinneaPigUpdater;

@Service
public class GuinneaPigServiceImpl implements GuinneaPigService {

    private final GuinneaPigRepository GuinneaPigRepository;
    private final GuinneaPigUpdater GuinneaPigUpdater;

    @Autowired
    public GuinneaPigServiceImpl(GuinneaPigRepository GuinneaPigRepository, GuinneaPigUpdater GuinneaPigUpdater) {
        this.GuinneaPigRepository = GuinneaPigRepository;
        this.GuinneaPigUpdater = GuinneaPigUpdater;
    }

    @Override
    public GuinneaPigDTO salvarGuinneaPig(GuinneaPigFormDTO form) {
        GuinneaPig GuinneaPig = form.toEntity();
        return new GuinneaPigDTO(GuinneaPigRepository.save(GuinneaPig));
    }

    @Override
    public List<GuinneaPigDTO> listaGuinneaPigs() {
        return GuinneaPigRepository.findAll().stream().map(GuinneaPigDTO::new).collect(Collectors.toList());
    }

    @Override
    public GuinneaPigDetailsDTO buscarGuinneaPigPorId(Long id) {
        GuinneaPig GuinneaPig = GuinneaPigRepository.findById(id).orElse(null);
        return GuinneaPig != null ? new GuinneaPigDetailsDTO(GuinneaPig) : null;
    }

    @Override
    public GuinneaPigDTO atualizarGuinneaPig(Long id, GuinneaPigFormUpdateDTO form) {
        GuinneaPig GuinneaPig = GuinneaPigRepository.findById(id).orElseThrow(RuntimeException::new);
        GuinneaPigUpdater.atualizarGuinneaPig(GuinneaPig, form);
        return new GuinneaPigDTO(GuinneaPigRepository.save(GuinneaPig));
    }

    @Override
    public void excluirGuinneaPig(Long id) {
        GuinneaPigRepository.deleteById(id);
    }



}
