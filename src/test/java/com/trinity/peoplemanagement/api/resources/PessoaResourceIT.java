package com.trinity.peoplemanagement.api.resources;

import java.time.LocalDate;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.trinity.peoplemanagement.domain.model.Pessoa;
import com.trinity.peoplemanagement.domain.repository.PessoaRepository;
import com.trinity.peoplemanagement.util.DatabaseCleaner;
import com.trinity.peoplemanagement.util.PessoaTest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class PessoaResourceIT {
	
	private static final String BASE_URL = "/api/v1/pessoas";
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = BASE_URL;
		
		databaseCleaner.clearTables();
		prepararDados();
	}
	
	@Test
	public void deveRetornarStatus200_QuandoConsultarPessoas() {
		
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveConter2Pessoas_QuandoConsultarPessoas() {
		RestAssured.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", Matchers.hasSize(2));
	}
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarPessoa() {
		RestAssured.given()
			.body(new Gson().toJson(getPessoa()))
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarPessoaExistente() {
		RestAssured.given()
			.pathParam("pessoaId", 2)
			.accept(ContentType.JSON)
		.when()
			.get("/{pessoaId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("nome", Matchers.equalTo("Viviane"));
	}
	
	private void prepararDados() {
		Pessoa williams = new Pessoa();
		williams.setNome("Williams");
		williams.setCpf("11077161018");
		williams.setDataNascimento(LocalDate.now());
		williams.setEmail("williams@test.com");
		williams.setNacionalidade("Brasileiro");
		williams.setNaturalidade("Recife");
		williams.setSexo("M");
		pessoaRepository.save(williams);
		
		Pessoa viviane = new Pessoa();
		viviane.setNome("Viviane");
		viviane.setCpf("34472482037");
		viviane.setDataNascimento(LocalDate.now());
		viviane.setEmail("viviane@test.com");
		viviane.setNacionalidade("Brasileira");
		viviane.setNaturalidade("Recife");
		viviane.setSexo("F");
		pessoaRepository.save(viviane);
	}
	
	private PessoaTest getPessoa() {
		PessoaTest pessoa = new PessoaTest();
		pessoa.setNome("Maria");
		pessoa.setCpf("22998760066");
		pessoa.setDataNascimento("1994-05-11");
		pessoa.setEmail("maria@test.com");
		pessoa.setNacionalidade("Brasileira");
		pessoa.setNaturalidade("Recife");
		pessoa.setSexo("F");
		
		
		return pessoa;
	}

}
