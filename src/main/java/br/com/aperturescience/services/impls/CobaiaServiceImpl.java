package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.cobaias.CobaiaDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaDetailsDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaFormDTO;
import br.com.aperturescience.dtos.cobaias.CobaiaFormUpdateDTO;
import br.com.aperturescience.models.Cobaia;
import br.com.aperturescience.repositories.CobaiaRepository;
import br.com.aperturescience.services.interfaces.CobaiaService;
import br.com.aperturescience.services.updaters.CobaiaUpdater;

@Service
public class CobaiaServiceImpl implements CobaiaService {

    private final CobaiaRepository cobaiaRepository;
    private final CobaiaUpdater cobaiaUpdater;

    @Autowired
    public CobaiaServiceImpl(CobaiaRepository cobaiaRepository, CobaiaUpdater cobaiaUpdater) {
        this.cobaiaRepository = cobaiaRepository;
        this.cobaiaUpdater = cobaiaUpdater;
    }

    @Override
    public CobaiaDTO salvarCobaia(CobaiaFormDTO form) {
        Cobaia cobaia = form.toEntity();
        return new CobaiaDTO(cobaiaRepository.save(cobaia));
    }

    @Override
    public List<CobaiaDTO> listaCobaias() {
        return cobaiaRepository.findAll().stream().map(CobaiaDTO::new).collect(Collectors.toList());
    }

    @Override
    public CobaiaDetailsDTO buscarCobaiaPorId(Long id) {
        Cobaia cobaia = cobaiaRepository.findById(id).orElse(null);
        return cobaia != null ? new CobaiaDetailsDTO(cobaia) : null;
    }

    @Override
    public CobaiaDTO atualizarCobaia(Long id, CobaiaFormUpdateDTO form) {
        Cobaia cobaia = cobaiaRepository.findById(id).orElseThrow(RuntimeException::new);
        cobaiaUpdater.atualizarCobaia(cobaia, form);
        return new CobaiaDTO(cobaiaRepository.save(cobaia));
    }

    @Override
    public void excluirCobaia(Long id) {
        cobaiaRepository.deleteById(id);
    }

}
