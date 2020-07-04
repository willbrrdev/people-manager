package com.trinity.peoplemanagement.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trinity.peoplemanagement.domain.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> { 
	
	Optional<Pessoa> findByCpf(String cpf);
	
}
