package br.com.aperturescience.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.aperturescience.dtos.logins.AutenticacaoDTO;
import br.com.aperturescience.dtos.logins.RegisterDTO;
import br.com.aperturescience.models.Funcionario;
import br.com.aperturescience.repositories.FuncionarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FuncionarioRepository repository;

    @PostMapping("/login")
    public ResponseEntity login (@RequestBody @Valid AutenticacaoDTO data){
        var senhaUsuario = new UsernamePasswordAuthenticationToken(data.codigoLogin(), data.senha());
        var auth = this.authenticationManager.authenticate(senhaUsuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    public ResponseEntity register (@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByCodigoLogin(data.codigoLogin()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.senha());
        Funcionario novoFuncionario = new Funcionario(data.codigoLogin(), encryptedPassword, data.cargo());

        this.repository.save(novoFuncionario);

        return ResponseEntity.ok().build();
    }
}
