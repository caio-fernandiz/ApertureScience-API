package br.com.aperturescience.dtos.tests;

import br.com.aperturescience.models.Test;

public record TestDTO(Long id, String currentChamber, String title, String testObjective, String testDescription, String notes,
Integer testQuantitys, String currentResult, Integer casualties) {

    public TestDTO(Test Test){
        this(Test.getId(), Test.getCurrentChamber(), Test.getTitle(), Test.getTestObjective(), Test.getTestDescription(),
        Test.getNotes(), Test.getTestQuantitys(), Test.getCurrentResult(), Test.getCasualties());
    }
}
