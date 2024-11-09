package br.com.aperturescience.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aperturescience.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

}
