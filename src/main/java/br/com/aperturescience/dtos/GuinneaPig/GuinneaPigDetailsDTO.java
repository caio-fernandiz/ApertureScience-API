package br.com.aperturescience.dtos.GuinneaPig;

import br.com.aperturescience.models.GuinneaPig;

public record GuinneaPigDetailsDTO(Long id, String nameC, Integer age, String cpf, String height, String bloodType, 
    Integer qi, Integer resistanceTestResult, Integer forceTestResult, Integer speedTestResult, String academicBackground) {

    public GuinneaPigDetailsDTO(GuinneaPig GuinneaPig){
        this(GuinneaPig.getId(), GuinneaPig.getNameC(), GuinneaPig.getAge(), GuinneaPig.getCpf(), GuinneaPig.getHeight(), GuinneaPig.getBloodType(),
        GuinneaPig.getQi(), GuinneaPig.getResistanceTestResult(), GuinneaPig.getForceTestResult(),  GuinneaPig.getSpeedTestResult(), GuinneaPig.getAcademicBackground());
    }
}
