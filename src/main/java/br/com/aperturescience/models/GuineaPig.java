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
public class GuineaPig {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String cpf;
    private String height;
    private String bloodType;
    private Integer qi;
    private Integer resistanceTestResult;
    private Integer strengthTestResult;
    private Integer speedRestResult;
    private String academicBackground;
}
