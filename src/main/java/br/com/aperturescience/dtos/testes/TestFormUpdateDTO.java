package br.com.aperturescience.dtos.Tests;

public record TestFormUpdateDTO(String currentChamber, String title, String testObjective, String testDescription, String notes,
Integer testQuantity, String currentResult, Integer downs) {

}
