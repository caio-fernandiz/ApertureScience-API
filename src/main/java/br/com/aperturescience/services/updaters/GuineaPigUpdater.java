package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.guineaPigs.GuineaPigFormUpdateDTO;
import br.com.aperturescience.models.GuineaPig;

@Component
public class GuineaPigUpdater {

    public void updateGuineaPig(GuineaPig guineaPig, GuineaPigFormUpdateDTO form) {
        if (form.name() != null)
            guineaPig.setName(form.name());
        if (form.age() != null)
            guineaPig.setAge(form.age());
        if (form.cpf() != null)
            guineaPig.setCpf(form.cpf());
        if (form.height() != null)
            guineaPig.setHeight(form.height());
        if (form.bloodType() != null)
            guineaPig.setBloodType(form.bloodType());
        if (form.qi() != null)
            guineaPig.setQi(form.qi());
        if (form.resistanceTestResult() != null)
            guineaPig.setResistanceTestResult(form.resistanceTestResult());
        if (form.strengthTestResult() != null)
            guineaPig.setStrengthTestResult(form.strengthTestResult());
        if (form.speedTestResult() != null)
            guineaPig.setSpeedTestResult(form.speedTestResult());
        if (form.academicBackground() != null)
            guineaPig.setAcademicBackground(form.academicBackground());
    }
}
