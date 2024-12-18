package br.com.aperturescience.infra.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public enum UserRole {
    
    SEGURANCA("Segurança"),

    CIENTISTA("Cientista"),
    
    DIRETOR("Diretor");

    private String cargo;

    UserRole(String cargo){
        this.cargo = cargo;
    }

    public String getCargo(){
        return cargo;
    }
}
