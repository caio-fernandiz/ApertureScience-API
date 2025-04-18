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

import br.com.aperturescience.models.Employee;

@Service
public class TokenService {

    @Value("${api.aperture.token.secret}")
    private String secret;

    public String generateToken(Employee employee) {
        try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                String token = JWT.create()
                                    .withIssuer("aperture-api")
                                    .withSubject(employee.getLoginCode())
                                    .withExpiresAt(genExpirationDate())
                                    .sign(algorithm);

                return token;
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
            
        }
        
    }

    public String validateToken (String token){
        try {
                Algorithm algorithm = Algorithm.HMAC256(secret);
                return JWT.require(algorithm)
                            .withIssuer("aperture-api")
                            .build()
                            .verify(token)
                            .getSubject();
        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Instant genExpirationDate(){
            return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
