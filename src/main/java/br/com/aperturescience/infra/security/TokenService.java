package br.com.aperturescience.infra.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.aperturescience.models.Funcionario;

@Service
public class TokenService {

    @Value("${api.security.token.segredo}")
    private String segredo;

    public String gerandoToken(Funcionario funcionario){
        try {
            Algorithm algorithm = Algorithm.HMAC256(segredo);
            String token = JWT.create()
                    .withIssuer("auth-aperture")
                    .withSubject(funcionario.getCodigoLogin())
                    .withExpiresAt(1000)
                    .sign(algorithm);
        } catch () {
        
        }
    }
}
