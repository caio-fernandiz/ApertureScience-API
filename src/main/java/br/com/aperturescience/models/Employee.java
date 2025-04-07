package br.com.aperturescience.models;

import br.com.aperturescience.util.GeradorDeLogin;
import br.com.aperturescience.util.GeradorDepsswrd;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private String cpf;
    private String email;
    private String telephone;
    private String role;
    private Integer accessLevel;
    private String psswrd;
    private String loginCode;
    
    @PrePersist
    protected void onCreate(){
        this.loginCode = GeradorDeLogin.gerarCodigoAleatorio();
        this.psswrd = GeradorDepsswrd.gerarpsswrd();
    }
}
