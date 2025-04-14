package br.com.aperturescience.infra.user;

public enum UserRole {

    DIRETOR("diretor"),

    CIENTISTA("cientista"); 

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
