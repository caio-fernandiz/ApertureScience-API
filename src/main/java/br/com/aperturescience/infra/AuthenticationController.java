package br.com.aperturescience.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/as/auth")
=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aperturescience.models.Employee;
import jakarta.validation.Valid;

@RestController
@RequestMapping("as/auth")
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

<<<<<<< HEAD
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
=======
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data){
        
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.loginCode(), data.psswrd());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        return ResponseEntity.ok().build();
    }
<<<<<<< HEAD
=======

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByLoginCode(data.loginCode()) !=null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.psswrd());
        Employee newEmployee = new Employee(data.loginCode(), encryptedPassword, data.role());

        this.repository.save(newEmployee);
        return ResponseEntity.ok().build();
    }
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8
}
