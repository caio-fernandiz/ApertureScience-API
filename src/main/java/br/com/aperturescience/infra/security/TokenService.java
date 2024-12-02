package br.com.aperturescience.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import br.com.aperturescience.models.Funcionario;

@Service
public class TokenService {
    @Value("${api.security.token.segredo}")
    private String segredo;
    
    public String gerandoToken(Funcionario funcionario){
        try {
            Algorithm algoritimo = Algorithm.HMAC256(segredo);

            String token = JWT.create()
                .withIssuer("apeturescience-api")
                .withSubject(funcionario.getCodigoLogin())
                .withExpiresAt(this.gerandoExpiração())
                .sign(algoritimo);
                return token;
        } catch (JWTCreationException exception) {
           throw new RuntimeException("Erro de autenticação");
        }
    }

    public String validandoToken(String token){
        try {

            Algorithm algoritimo = Algorithm.HMAC256(segredo);

            return JWT.require(algoritimo)
            .withIssuer("apeturescience-api")
            .build()
            .verify(token)
            .getSubject();
        } catch (JWTVerificationException e) {
            return null;
        }
    }

    private Instant gerandoExpiração(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));   
    }
}
