package br.com.aperturescience.infra;

import br.com.aperturescience.infra.user.UserRole;

public record RegisterDTO(String loginCode, String psswrd, UserRole role) {

}
