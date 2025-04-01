package br.com.aperturescience.dtos.guineaPigs;

import br.com.aperturescience.models.GuineaPig;

public record GuineaPigFormDTO(String name, Integer age, String cpf, String height, String bloodType, 
    Integer qi, Integer resistanceTestResult, Integer strengthTestResult, Integer speedRestResult, String academicBackground) {

    public GuineaPig toEntity(){
        return new GuineaPig(null, name, age, cpf, height,bloodType,
        qi, resistanceTestResult, strengthTestResult, speedRestResult, academicBackground);
    }
}
