package br.com.aperturescience.dtos.tests;

public record TestFormUpdateDTO(String currentChamber, String title, String testObjective, String testDescription, String notes,
Integer testQuantitys, String currentResult, Integer casualties) {

}
