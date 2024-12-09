package br.com.aperturescience.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.aperturescience.repositories.FuncionarioRepository;

@Service
public class AutorizacaoServiceImpl implements UserDetailsService{

    @Autowired
    FuncionarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByCodigoLogin(username);
    }

}
