package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.guineaPigs.GuineaPigDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigDetailsDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigFormDTO;
import br.com.aperturescience.dtos.guineaPigs.GuineaPigFormUpdateDTO;
import br.com.aperturescience.models.GuineaPig;
import br.com.aperturescience.repositories.GuineaPigRepository;
import br.com.aperturescience.services.interfaces.GuineaPigService;
import br.com.aperturescience.services.updaters.GuineaPigUpdater;

@Service
public class GuineaPigServiceImpl implements GuineaPigService {

    private final GuineaPigRepository guineaPigRepository;
    private final GuineaPigUpdater guineaPigUpdater;

    @Autowired
    public GuineaPigServiceImpl(GuineaPigRepository guineaPigRepository, GuineaPigUpdater guineaPigUpdater) {
        this.guineaPigRepository = guineaPigRepository;
        this.guineaPigUpdater = guineaPigUpdater;
    }

    @Override
    public GuineaPigDTO saveGuineaPig(GuineaPigFormDTO form) {
        GuineaPig guineaPig = form.toEntity();
        return new GuineaPigDTO(guineaPigRepository.save(guineaPig));
    }

    @Override
    public List<GuineaPigDTO> listGuineaPigs() {
        return guineaPigRepository.findAll().stream().map(GuineaPigDTO::new).collect(Collectors.toList());
    }

    @Override
    public GuineaPigDetailsDTO findrGuineaPigById(Long id) {
        GuineaPig guineaPig = guineaPigRepository.findById(id).orElse(null);
        return guineaPig != null ? new GuineaPigDetailsDTO(guineaPig) : null;
    }

    @Override
    public GuineaPigDTO updateGuineaPig(Long id, GuineaPigFormUpdateDTO form) {
        GuineaPig guineaPig = guineaPigRepository.findById(id).orElseThrow(RuntimeException::new);
        guineaPigUpdater.updateGuineaPig(guineaPig, form);
        return new GuineaPigDTO(guineaPigRepository.save(guineaPig));
    }

    @Override
    public void deleteGuineaPig(Long id) {
        guineaPigRepository.deleteById(id);
    }

}
