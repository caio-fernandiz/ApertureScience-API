package br.com.aperturescience.services.updaters;

import org.springframework.stereotype.Component;

import br.com.aperturescience.dtos.GuinneaPig.GuinneaPigFormUpdateDTO;
import br.com.aperturescience.models.GuinneaPig;

@Component
public class GuinneaPigUpdater {

    public void atualizarGuinneaPig(GuinneaPig GuinneaPig, GuinneaPigFormUpdateDTO form) {
        if (form.nameC() != null)
            GuinneaPig.setNameC(form.nameC());
        if (form.age() != null)
            GuinneaPig.setAge(form.age());
        if (form.cpf() != null)
            GuinneaPig.setCpf(form.cpf());
        if (form.height() != null)
            GuinneaPig.setHeight(form.height());
        if (form.bloodType() != null)
            GuinneaPig.setBloodType(form.bloodType());
        if (form.qi() != null)
            GuinneaPig.setQi(form.qi());
        if (form.resistanceTestResult() != null)
            GuinneaPig.setResistanceTestResult(form.resistanceTestResult());
        if (form.forceTestResult() != null)
            GuinneaPig.setForceTestResult(form.forceTestResult());
        if (form.speedTestResult() != null)
            GuinneaPig.setSpeedTestResult(form.speedTestResult());
        if (form.academicBackground() != null)
            GuinneaPig.setAcademicBackground(form.academicBackground());
    }
}
