package br.com.aperturescience.dtos.Tests;

import br.com.aperturescience.models.Test;

public record TestDetailsDTO(String currentChamber, String title, String testObjective, String testDescription, String notes,
Integer testQuantity, String currentResult, Integer downs) {

     public TestDetailsDTO(Test Test){
        this(Test.getCurrentChamber(), Test.getTitle(), Test.getTestObjective(), Test.getTestDescription(),
        Test.getNotes(), Test.getTestQuantity(), Test.getCurrentResult(), Test.getDowns());
    }

}
