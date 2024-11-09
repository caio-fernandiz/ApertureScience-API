package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.funcionarios.FuncionarioDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioDetailsDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioFormDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioFormUpdateDTO;
import br.com.aperturescience.models.Funcionario;
import br.com.aperturescience.repositories.FuncionarioRepository;
import br.com.aperturescience.services.interfaces.FuncionarioService;
import br.com.aperturescience.services.updaters.FuncionarioUpdater;

@Service
public class FuncionarioServiceImpl implements FuncionarioService{

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioUpdater funcionarioUpdater;

    @Autowired
    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository, FuncionarioUpdater funcionarioUpdater){
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioUpdater = funcionarioUpdater;
    }

    @Override
    public FuncionarioDTO salvarFuncionario(FuncionarioFormDTO form){
        Funcionario funcionario = form.toEntity();
        return new  FuncionarioDTO(funcionarioRepository.save(funcionario));
    }

    @Override
    public List<FuncionarioDTO> listaFuncionarios(){
        return funcionarioRepository.findAll().stream().map(FuncionarioDTO::new).collect(Collectors.toList());
    }

    @Override
    public FuncionarioDetailsDTO buscarFuncionarioPorId(Long id){
        Funcionario funcionario = funcionarioRepository.findById(id).orElse(null);
        return funcionario != null ? new FuncionarioDetailsDTO(funcionario) : null;
    }

    @Override
    public FuncionarioDTO atualizarFuncionario(Long id, FuncionarioFormUpdateDTO form){
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(RuntimeException::new);
        funcionarioUpdater.atualizarFuncionario(funcionario, form);
        return new FuncionarioDTO(funcionarioRepository.save(funcionario));
    }

    @Override
    public void excluirFuncionario(Long id){
        funcionarioRepository.deleteById(id);
    }
}
