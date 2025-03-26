package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.Tests.TestFormUpdateDTO;
import br.com.aperturescience.models.Test;

@Component
public class TestUpdater {

    public void atualizarTest(Test Test, TestFormUpdateDTO form) {
        if (form.currentChamber() != null)
            Test.setCurrentChamber(form.currentChamber());
        if(form.title() != null)
            Test.setTitle(form.title());
        if (form.testObjective() != null)
            Test.setTestObjective(form.testObjective());
        if (form.testDescription() != null)
            Test.setTestDescription(form.testDescription());
        if (form.notes() != null)
            Test.setNotes(form.notes());
        if (form.testQuantity() != null)
            Test.setTestQuantity(form.testQuantity());
        if (form.currentResult() != null)
            Test.setCurrentResult(form.currentResult());
        if (form.downs() != null)
            Test.setDowns(form.downs());
    }
}
