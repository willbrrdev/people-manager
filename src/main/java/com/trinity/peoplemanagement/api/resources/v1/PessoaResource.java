package com.trinity.peoplemanagement.api.resources.v1;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.trinity.peoplemanagement.api.dto.v1.NewPessoaDTO;
import com.trinity.peoplemanagement.api.dto.v1.ResponsePessoaDTO;
import com.trinity.peoplemanagement.api.dto.v1.UpdatePessoaDTO;
import com.trinity.peoplemanagement.domain.exception.EntidadeEmUsoException;
import com.trinity.peoplemanagement.domain.exception.EntidadeNaoEncontradaException;
import com.trinity.peoplemanagement.domain.model.Pessoa;
import com.trinity.peoplemanagement.domain.repository.PessoaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin("*")
@Api(value = "Pessoas", tags = "Pessoas")
@RestController
@RequestMapping("api/v1/pessoas")
public class PessoaResource {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private com.trinity.peoplemanagement.domain.service.PessoaService pessoaService;

	@ApiOperation(value = "Retorna uma lista de pessoas")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Retorna a lista de pessoa"),
			@ApiResponse(code = 500, message = "Falha no servidor"), })
	
	@GetMapping(produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public List<ResponsePessoaDTO> listar() {
		List<Pessoa> pessoas = pessoaRepository.findAll();

		List<ResponsePessoaDTO> response = pessoas.stream()
				.map(pessoa -> new ResponsePessoaDTO(pessoa))
				.collect(Collectors.toList());
		
		return response;
	}

	@ApiOperation(value = "Retorna a pessoa do id especificado")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "Retorna a pessoa do id especificado"),
			@ApiResponse(code = 404, message = "Nenhum registro encontrado"),
			@ApiResponse(code = 500, message = "Falha no servidor"), })
	
	@GetMapping(value = "/{pessoaId}", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<ResponsePessoaDTO> buscar(
			@ApiParam(value = "O id da pessoa que deseja realizar a busca")
			@PathVariable Long pessoaId) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(pessoaId);

		if (pessoa.isPresent()) {
			return ResponseEntity.ok(new ResponsePessoaDTO(pessoa.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra uma nova pessoa")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cadastra uma nova pessoa"),
			@ApiResponse(code = 400, message = "Requisição inválida"),
			@ApiResponse(code = 500, message = "Falha no servidor"), })

	@PostMapping(produces = "application/json", consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<?> adicionar(
			@Valid @ApiParam("Dados da pessoa que deseja cadastrar") @RequestBody NewPessoaDTO pessoaDTO) {
		try {
			Pessoa pessoa = pessoaService.salvar(pessoaDTO.toPessoa());
			
			ResponsePessoaDTO response = new ResponsePessoaDTO(pessoa);

			return ResponseEntity.status(HttpStatus.CREATED).body(response);
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
			@ApiParam("Dados da pessoa que deseja atualizar") @RequestBody UpdatePessoaDTO pessoa) {
		try {
			Optional<Pessoa> pessoaOpt = pessoaRepository.findById(pessoaId);

			if (pessoaOpt.isPresent()) {
				Pessoa pessoaAtual = pessoaOpt.get();
				BeanUtils.copyProperties(pessoa, pessoaAtual, "id", "cpf");

				pessoaAtual = pessoaRepository.save(pessoaAtual);
				
				ResponsePessoaDTO response = new ResponsePessoaDTO(pessoaAtual);
				
				return ResponseEntity.ok(response);
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
