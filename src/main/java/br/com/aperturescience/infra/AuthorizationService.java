package br.com.aperturescience.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService{

<<<<<<< HEAD
     @Autowired
     UserRepository repository;
=======
    @Autowired
    UserRepository repository;
>>>>>>> 4e0a8f8594fab7c2a714a323580e8f47844ac4a8

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByLoginCode(username);
    }

}
