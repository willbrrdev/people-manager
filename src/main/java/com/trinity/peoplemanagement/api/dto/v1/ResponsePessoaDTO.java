package com.trinity.peoplemanagement.api.dto.v1;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.trinity.peoplemanagement.domain.model.Pessoa;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponsePessoaDTO {

	@ApiModelProperty(value = "Id da pessoa", example = "1")
	private Long id;

	@ApiModelProperty(value = "Nome da pessoa", example = "Marcone", required = true)
	private String nome;

	@ApiModelProperty(value = "Email da pessoa", example = "marcone@mail.com")
	private String email;

	@ApiModelProperty(value = "Data de anivesário da pessoa", example = "1994-05-11", required = true)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@ApiModelProperty(value = "Naturalidade da pessoa", example = "Recife")
	private String naturalidade;

	@ApiModelProperty(value = "Nacionalidade da pessoa", example = "Brasileiro")
	private String nacionalidade;

	@ApiModelProperty(value = "CPF da pessoa - Deve ser um cpf válido e não pode ser igual a um já cadastrado", example = "marcone@mail.com", required = true)
	private String cpf;

	@ApiModelProperty(value = "Sexo da pessoa, Masculino, Feminino e Outros", example = "Masculino")
	private String sexo;
	
	public ResponsePessoaDTO(Pessoa pessoa) {
		this.init(pessoa);
	}
	
	private void init(Pessoa pessoa) {
		this.id = pessoa.getId();
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento();
		this.email = pessoa.getEmail();
		this.nacionalidade = pessoa.getNacionalidade();
		this.naturalidade = pessoa.getNaturalidade();
		this.nome = pessoa.getNome();
		this.sexo = pessoa.getSexo();
	}

}
