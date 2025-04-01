package br.com.aperturescience.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.aperturescience.models.Test;

public interface TestRepository extends JpaRepository<Test, Long>{

}
