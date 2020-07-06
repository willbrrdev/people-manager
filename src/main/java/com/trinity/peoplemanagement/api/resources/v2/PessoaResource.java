package com.trinity.peoplemanagement.api.resources.v2;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.trinity.peoplemanagement.api.dto.v2.NewPessoaV2DTO;
import com.trinity.peoplemanagement.api.dto.v2.UpdatePessoaV2DTO;
import com.trinity.peoplemanagement.domain.exception.EntidadeEmUsoException;
import com.trinity.peoplemanagement.domain.exception.EntidadeNaoEncontradaException;
import com.trinity.peoplemanagement.domain.model.Pessoa;
import com.trinity.peoplemanagement.domain.repository.PessoaRepository;
import com.trinity.peoplemanagement.domain.service.PessoaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@Api(value = "Pessoas", tags = "Pessoas")
@RestController(value = "pessoaResourceV2")
@RequestMapping("api/v2/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService pessoaService;

	@ApiOperation(value = "Retorna uma lista de pessoas")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Retorna a lista de pessoa"),
			@ApiResponse(code = 500, message = "Falha no servidor"), })
	
	@GetMapping(produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}

	@ApiOperation(value = "Retorna a pessoa do id especificado")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Retorna a pessoa do id especificado"),
			@ApiResponse(code = 404, message = "Nenhum registro encontrado"),
			@ApiResponse(code = 500, message = "Falha no servidor"), })
	
	@GetMapping(value = "/{pessoaId}", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Pessoa> buscar(
			@ApiParam(value = "O id da pessoa que deseja realizar a busca")
			@PathVariable Long pessoaId) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

		if (pessoa.isPresent()) {
			return ResponseEntity.ok(pessoa.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra uma nova pessoa")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Cadastra uma nova pessoa", response = Pessoa.class),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 500, message = "Falha no servidor"), })
	
	@PostMapping(produces = "application/json", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(
			@Valid 
			@ApiParam("Dados da pessoa que deseja cadastrar")
			@RequestBody NewPessoaV2DTO pessoaDTO) {
		
		try {
			Pessoa pessoa = pessoaService.salvar(pessoaDTO.toPessoa());

			return ResponseEntity.status(HttpStatus.CREATED).body(pessoa);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Atualiza os dados de uma pessoa")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Atualiza os dados de uma pessoa"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 404, message = "Regristro não encontrado"),
			@ApiResponse(code = 500, message = "Falha no servidor"), })
	@PutMapping(value = "/{pessoaId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<?> atualizar(
			@ApiParam(value = "Id da pessoa que deseja atualizar os dados") 
			@PathVariable Long pessoaId, 
			@ApiParam("Dados da pessoa que deseja atualizar") @RequestBody UpdatePessoaV2DTO pessoa) {
		try {
			Optional<Pessoa> pessoaOpt = pessoaRepository.findById(pessoaId);

			if (pessoaOpt.isPresent()) {
				Pessoa pessoaAtual = pessoaOpt.get();
				
				if (pessoa.getEndereco() != null) {
					String cepSemMascara = pessoa.getEndereco().getCep().replaceAll("[-]", "");
					pessoa.getEndereco().setCep(cepSemMascara);
				}
				
				BeanUtils.copyProperties(pessoa, pessoaAtual, "id", "cpf");

				pessoaAtual = pessoaRepository.save(pessoaAtual);
				
				return ResponseEntity.ok(pessoaAtual);
			}

			return ResponseEntity.notFound().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@ApiOperation(value = "Remove os registro de uma pessoa")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Remove os registro de uma pessoa"),
			@ApiResponse(code = 404, message = "Regristro não encontrado"),
			@ApiResponse(code = 409, message = "Conflito"), @ApiResponse(code = 500, message = "Falha no servidor"), })
	
	@DeleteMapping(value = "/{pessoaId}", produces = "application/json")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> remover(
			@ApiParam(value = "ID da pessoa que deseja excluir")
			@PathVariable Long pessoaId) {
		try {
			pessoaService.excluir(pessoaId);
			return ResponseEntity.noContent().build();

		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();

		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

}
