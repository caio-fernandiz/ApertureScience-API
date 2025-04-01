package br.com.aperturescience.services.impls;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.aperturescience.dtos.tests.TestDTO;
import br.com.aperturescience.dtos.tests.TestDetailsDTO;
import br.com.aperturescience.dtos.tests.TestFormDTO;
import br.com.aperturescience.dtos.tests.TestFormUpdateDTO;
import br.com.aperturescience.models.Test;
import br.com.aperturescience.repositories.TestRepository;
import br.com.aperturescience.services.interfaces.TestService;
import br.com.aperturescience.services.updaters.TestUpdater;

@Service
public class TestServiceImpl implements TestService{

    private final TestRepository testRepository;
    private final TestUpdater testUpdater;

    @Autowired
    public TestServiceImpl(TestRepository TestRepository, TestUpdater TestUpdater){
        this.testRepository = TestRepository;
        this.testUpdater = TestUpdater;
    }

    @Override
public TestDTO saveTest(TestFormDTO form) {
    Test Test = form.toEntity();
    return new TestDTO(testRepository.save(Test));
}

@Override
public List<TestDTO> listTests() {
    return testRepository.findAll().stream().map(TestDTO::new).collect(Collectors.toList());
}

@Override
public TestDetailsDTO findTestById(Long id) {
    Test Test = testRepository.findById(id).orElse(null);
    return Test != null ? new TestDetailsDTO(Test) : null;
}

@Override
public TestDTO updateTest(Long id, TestFormUpdateDTO form) {
    Test Test = testRepository.findById(id).orElseThrow(RuntimeException::new);
    testUpdater.updateTest(Test, form);
    return new TestDTO(testRepository.save(Test));
}



}
