package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.funcionarios.FuncionarioDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioDetailsDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioFormDTO;
import br.com.aperturescience.dtos.funcionarios.FuncionarioFormUpdateDTO;

public interface FuncionarioService {

    FuncionarioDTO salvarFuncionario(FuncionarioFormDTO form);

    List<FuncionarioDTO> listaFuncionarios();

    FuncionarioDetailsDTO buscarFuncionarioPorId(Long id);

    FuncionarioDTO atualizarFuncionario(Long id, FuncionarioFormUpdateDTO form);

    void excluirFuncionario(Long id);
}
