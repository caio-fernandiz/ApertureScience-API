package br.com.aperturescience.dtos.logins;

import br.com.aperturescience.infra.security.UserRole;

public record RegisterDTO (String codigoLogin, String senha, UserRole cargo){

}
