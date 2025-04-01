package br.com.aperturescience.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String currentChamber;
    private String title;
    private String testObjective;
    private String testDescription;
    private String notes;
    private Integer testQuantitys;
    private String currentResult;
    private Integer casualties;

}
