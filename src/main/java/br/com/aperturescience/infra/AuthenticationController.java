package br.com.aperturescience.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aperturescience.infra.security.TokenService;
import br.com.aperturescience.models.Employee;
import jakarta.validation.Valid;

@RestController
@RequestMapping("as/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.loginCode(), data.psswrd());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((Employee) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLoginCode(data.loginCode()) !=null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.psswrd());
        Employee newEmployee = new Employee(data.loginCode(), encryptedPassword, data.role());

        this.repository.save(newEmployee);
        return ResponseEntity.ok().build();
    }
}