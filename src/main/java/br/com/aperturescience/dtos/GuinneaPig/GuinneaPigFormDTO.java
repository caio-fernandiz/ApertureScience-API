package br.com.aperturescience.dtos.GuinneaPig;

import br.com.aperturescience.models.GuinneaPig;

public record GuinneaPigFormDTO(String nameC, Integer age, String cpf, String height, String bloodType, 
    Integer qi, Integer resistanceTestResult, Integer forceTestResult, Integer speedTestResult, String academicBackground) {

    public GuinneaPig toEntity(){
        return new GuinneaPig(null, nameC, age, cpf, height,bloodType,
        qi, resistanceTestResult, forceTestResult, speedTestResult, academicBackground);
    }
}
