package br.com.aperturescience.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.aperturescience.models.Employee;

public interface UserRepository extends JpaRepository<Employee, Long> {
 UserDetails findByLoginCode(String loginCode);
}
