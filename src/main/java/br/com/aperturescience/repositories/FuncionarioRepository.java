package br.com.aperturescience.repositories;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.aperturescience.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{
    
    UserDetails findByCodigoLogin(String codigoLogin);
}
