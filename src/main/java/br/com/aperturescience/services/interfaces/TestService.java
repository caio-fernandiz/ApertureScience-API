package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.Tests.TestDTO;
import br.com.aperturescience.dtos.Tests.TestDetailsDTO;
import br.com.aperturescience.dtos.Tests.TestFormDTO;
import br.com.aperturescience.dtos.Tests.TestFormUpdateDTO;

public interface TestService {

    TestDTO salvarTest(TestFormDTO form);

    List<TestDTO> listaTests();
    
    TestDetailsDTO buscarTestPorId(Long id);

    TestDTO atualizarTest(Long id, TestFormUpdateDTO form);
}
