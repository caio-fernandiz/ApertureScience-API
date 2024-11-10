package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.testes.TesteDTO;
import br.com.aperturescience.dtos.testes.TesteDetailsDTO;
import br.com.aperturescience.dtos.testes.TesteFormDTO;
import br.com.aperturescience.dtos.testes.TesteFormUpdateDTO;
import br.com.aperturescience.models.Teste;
import br.com.aperturescience.repositories.TesteRepository;
import br.com.aperturescience.services.interfaces.TesteService;
import br.com.aperturescience.services.updaters.TesteUpdater;

@Service
public class TesteServiceImpl implements TesteService{

    private final TesteRepository testeRepository;
    private final TesteUpdater testeUpdater;

    @Autowired
    public TesteServiceImpl(TesteRepository testeRepository, TesteUpdater testeUpdater){
        this.testeRepository = testeRepository;
        this.testeUpdater = testeUpdater;
    }

    @Override
public TesteDTO salvarTeste(TesteFormDTO form) {
    Teste teste = form.toEntity();
    return new TesteDTO(testeRepository.save(teste));
}

@Override
public List<TesteDTO> listaTestes() {
    return testeRepository.findAll().stream().map(TesteDTO::new).collect(Collectors.toList());
}

@Override
public TesteDetailsDTO buscarTestePorId(Long id) {
    Teste teste = testeRepository.findById(id).orElse(null);
    return teste != null ? new TesteDetailsDTO(teste) : null;
}

@Override
public TesteDTO atualizarTeste(Long id, TesteFormUpdateDTO form) {
    Teste teste = testeRepository.findById(id).orElseThrow(RuntimeException::new);
    testeUpdater.atualizarTeste(teste, form);
    return new TesteDTO(testeRepository.save(teste));
}

}
