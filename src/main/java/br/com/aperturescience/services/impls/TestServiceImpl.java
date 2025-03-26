package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.Tests.TestDTO;
import br.com.aperturescience.dtos.Tests.TestDetailsDTO;
import br.com.aperturescience.dtos.Tests.TestFormDTO;
import br.com.aperturescience.dtos.Tests.TestFormUpdateDTO;
import br.com.aperturescience.models.Test;
import br.com.aperturescience.repositories.TestRepository;
import br.com.aperturescience.services.interfaces.TestService;
import br.com.aperturescience.services.updaters.TestUpdater;

@Service
public class TestServiceImpl implements TestService{

    private final TestRepository TestRepository;
    private final TestUpdater TestUpdater;

    @Autowired
    public TestServiceImpl(TestRepository TestRepository, TestUpdater TestUpdater){
        this.TestRepository = TestRepository;
        this.TestUpdater = TestUpdater;
    }

    @Override
public TestDTO salvarTest(TestFormDTO form) {
    Test Test = form.toEntity();
    return new TestDTO(TestRepository.save(Test));
}

@Override
public List<TestDTO> listaTests() {
    return TestRepository.findAll().stream().map(TestDTO::new).collect(Collectors.toList());
}

@Override
public TestDetailsDTO buscarTestPorId(Long id) {
    Test Test = TestRepository.findById(id).orElse(null);
    return Test != null ? new TestDetailsDTO(Test) : null;
}

@Override
public TestDTO atualizarTest(Long id, TestFormUpdateDTO form) {
    Test Test = TestRepository.findById(id).orElseThrow(RuntimeException::new);
    TestUpdater.atualizarTest(Test, form);
    return new TestDTO(TestRepository.save(Test));
}

}
