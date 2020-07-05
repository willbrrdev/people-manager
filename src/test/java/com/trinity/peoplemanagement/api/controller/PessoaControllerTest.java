package com.trinity.peoplemanagement.api.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trinity.peoplemanagement.api.dto.v1.NewPessoaDTO;
import com.trinity.peoplemanagement.domain.model.Endereco;
import com.trinity.peoplemanagement.domain.model.Pessoa;
import com.trinity.peoplemanagement.domain.repository.PessoaRepository;
import com.trinity.peoplemanagement.domain.service.PessoaService;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PessoaControllerTest {

	private static final String EMAIL = "test@mail.com";

	private static final String CPF = "81994099089";

	private static final String NOME = "Fulano de Tal";

	private static final LocalDate DATA = LocalDate.now();
	
	private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");


	@Autowired
	private MockMvc mvc;

	@MockBean
	private PessoaService pessoaService;

	@MockBean
	private PessoaRepository pessoaRepository;
	
	private static final String URL_BASE = "/api/v1/pessoas";
	
	@Test
	public void deveCadastrarPessoa() throws Exception {
		Pessoa pessoa = getPessoa();
		BDDMockito.given(this.pessoaService.salvar(Mockito.any(Pessoa.class))).willReturn(pessoa);

		mvc.perform(MockMvcRequestBuilders.post(URL_BASE)
				.content(this.obterJsonRequisicaoPost())
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.nome").value(NOME))
				.andExpect(jsonPath("$.cpf").value(CPF))
				.andExpect(jsonPath("$.email").value(EMAIL));
	}

	private String obterJsonRequisicaoPost() throws JsonProcessingException {
		NewPessoaDTO dto = new NewPessoaDTO(getPessoa());
		
		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(dto);
		return writeValueAsString;
	}

	private Pessoa getPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(NOME);
		pessoa.setCpf(CPF);
		pessoa.setDataNascimento(DATA);
		pessoa.setEmail(EMAIL);
		pessoa.setNacionalidade("Brasileira");
		pessoa.setNaturalidade("Recife");
		pessoa.setSexo("M");
		pessoa.setEndereco(new Endereco());
		return pessoa;
	}

}
