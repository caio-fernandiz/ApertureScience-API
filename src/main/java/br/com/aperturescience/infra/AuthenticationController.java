package br.com.aperturescience.infra;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("as/auth")
public class AuthenticationController {

    @PostMapping("/login")
}
