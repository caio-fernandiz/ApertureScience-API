package br.com.aperturescience.dtos.Tests;

import br.com.aperturescience.models.Test;

public record TestFormDTO(String currentChamber, String title, String testObjective, String testDescription, String notes,
                            Integer testQuantity, String currentResult, Integer downs) {

    public Test toEntity(){
        return new Test(null, currentChamber, title, testObjective, testDescription, 
        notes, testQuantity, currentResult, downs);
    }
}
