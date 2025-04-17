package br.com.aperturescience.infra;

import br.com.aperturescience.infra.user.UserRole;

public record RegisterDTO( String name, Integer age, String cpf, String email, 
String telephone, UserRole role, Integer accessLevel, String psswrd, String loginCode) {

}
