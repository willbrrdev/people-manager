package com.trinity.peoplemanagement.domain.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.trinity.peoplemanagement.domain.model.Endereco;
import com.trinity.peoplemanagement.domain.model.Pessoa;

@SpringBootTest
@ActiveProfiles("test")
public class PessoaRepositoryIT {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@BeforeEach
	public void setUp() throws Exception {
		this.pessoaRepository.save(getPessoa("81994099089", "test@mail.com"));
	}
	
	@AfterEach
    public final void tearDown() { 
		this.pessoaRepository.deleteAll();
	}

	@Test
	public void deveListarTodasAsPessoas() {
		this.pessoaRepository.save(getPessoa("70224040030", "test2@mail.com"));
		List<Pessoa> pessoas = pessoaRepository.findAll();

		assertEquals(2, pessoas.size());
	}

	@Test
	public void deveRetornarUmaPessoaPorId() {
		Pessoa p = this.pessoaRepository.save(getPessoa("70224040030", "testando@mail.com"));
		Optional<Pessoa> pessoa = pessoaRepository.findById(p.getId());

		assertEquals("70224040030", pessoa.get().getCpf());
		assertEquals("testando@mail.com", pessoa.get().getEmail());
	}
	
	@Test
	public void deveRetornarUmaPessoaPorCPF() {
		Pessoa p = this.pessoaRepository.save(getPessoa("70224040030", "test2@mail.com"));
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(p.getCpf());

		assertEquals("70224040030", pessoa.get().getCpf());
	}
	
	@Test
	public void deveAtualizarUmaPessoa() {
		Pessoa p = this.pessoaRepository.save(getPessoa("70224040030", "test2@mail.com"));
		Optional<Pessoa> pessoa = pessoaRepository.findByCpf(p.getCpf());
		
		p = pessoa.get();
		
		p.setNome("Maria José");
		p.setSexo("F");
		
		p = pessoaRepository.save(p);

		assertEquals("70224040030", p.getCpf());
		assertEquals("test2@mail.com", p.getEmail());
		assertEquals("Maria José", p.getNome());
		assertEquals("F", p.getSexo());
		
		assertNotEquals(p.getNome(), "Fulano de Tal");
		assertNotEquals(p.getSexo(), "M");
	}

	private Pessoa getPessoa(String cpf, String email) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome("Fulano de Tal");
		pessoa.setCpf(cpf);
		pessoa.setDataNascimento(LocalDate.now());
		pessoa.setEmail(email);
		pessoa.setNacionalidade("Brasileira");
		pessoa.setNaturalidade("Recife");
		pessoa.setSexo("M");
		pessoa.setEndereco(new Endereco());
		return pessoa;
	}

}
