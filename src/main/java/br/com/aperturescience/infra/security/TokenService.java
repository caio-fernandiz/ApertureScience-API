package br.com.aperturescience.infra.security;

import org.springframework.stereotype.Service;

import br.com.aperturescience.models.Employee;

@Service
public class TokenService {

    public String generateToken(Employee employee) {
        return employee.getLoginCode();
        
    }

}
