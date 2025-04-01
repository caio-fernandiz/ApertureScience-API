package br.com.aperturescience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aperturescience.models.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
