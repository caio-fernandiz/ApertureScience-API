package br.com.aperturescience.dtos.tests;

import br.com.aperturescience.models.Test;

public record TestFormDTO(String currentChamber, String title, String testObjective, String testDescription, String notes,
                            Integer testQuantitys, String currentResult, Integer casualties) {

    public Test toEntity(){
        return new Test(null, currentChamber, title, testObjective, testDescription, 
        notes, testQuantitys, currentResult, casualties);
    }
}
