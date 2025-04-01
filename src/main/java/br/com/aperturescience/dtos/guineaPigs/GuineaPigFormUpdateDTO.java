package br.com.aperturescience.dtos.guineaPigs;

public record GuineaPigFormUpdateDTO(String name, Integer age, String cpf, String height, String bloodType, 
    Integer qi, Integer resistanceTestResult, Integer strengthTestResult, Integer speedTestResult, String academicBackground) {

}
