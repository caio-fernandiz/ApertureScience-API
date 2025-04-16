package br.com.aperturescience.infra.security;

<<<<<<< HEAD
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
=======
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
<<<<<<< HEAD
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
=======
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
<<<<<<< HEAD

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/as/guineapigs").hasRole("DIRETOR")
=======
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session ->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/as/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/as/auth/register").permitAll()
                        .requestMatchers(HttpMethod.POST, "/as/tests").hasRole("DIRETOR")
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8
                        .anyRequest().authenticated()
                        )
                .build();
    }
<<<<<<< HEAD

    public AuthenticationManager authenticationConfiguration
=======
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

 
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8
}
