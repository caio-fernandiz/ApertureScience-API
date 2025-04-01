package br.com.aperturescience.dtos.guineaPigs;

import br.com.aperturescience.models.GuineaPig;

public record GuineaPigDetailsDTO(Long id, String name, Integer age, String cpf, String height, String bloodType, 
    Integer qi, Integer resistanceTestResult, Integer strengthTestResult, Integer speedTestResult, String academicBackground) {

    public GuineaPigDetailsDTO(GuineaPig guineaPig){
        this(guineaPig.getId(), guineaPig.getName(), guineaPig.getAge(), guineaPig.getCpf(), guineaPig.getHeight(), guineaPig.getBloodType(),
        guineaPig.getQi(), guineaPig.getResistanceTestResult(), guineaPig.getStrengthTestResult(),  guineaPig.getSpeedTestResult(), guineaPig.getAcademicBackground());
    }
}
