package br.com.aperturescience.services.interfaces;

import java.util.List;

import br.com.aperturescience.dtos.tests.TestDTO;
import br.com.aperturescience.dtos.tests.TestDetailsDTO;
import br.com.aperturescience.dtos.tests.TestFormDTO;
import br.com.aperturescience.dtos.tests.TestFormUpdateDTO;

public interface TestService {

    TestDTO saveTest(TestFormDTO form);

    List<TestDTO> listTests();
    
    TestDetailsDTO findTestById(Long id);

    TestDTO updateTest(Long id, TestFormUpdateDTO form);
}
