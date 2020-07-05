package com.trinity.peoplemanagement.api.dto.v2;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.trinity.peoplemanagement.domain.model.Endereco;
import com.trinity.peoplemanagement.domain.model.Pessoa;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel("UpdatePessoaV2DTO")
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePessoaV2DTO {

	@ApiModelProperty(value = "Nome da pessoa", example = "Marcone", required = true)
	@NotNull(message = "O Nome não pode ser Nulo")
	@NotEmpty(message = "O Nome não pode ser vazio ")
	private String nome;

	@ApiModelProperty(value = "Email da pessoa", example = "marcone@mail.com")
	@Email(message = "Formato de email inválido")
	private String email;

	@ApiModelProperty(value = "Data de anivesário da pessoa", example = "1994-05-11", required = true)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@ApiModelProperty(value = "Naturalidade da pessoa", example = "Recife")
	private String naturalidade;

	@ApiModelProperty(value = "Nacionalidade da pessoa", example = "Brasileiro")
	private String nacionalidade;

	@ApiModelProperty(value = "Sexo da pessoa, Masculino, Feminino e Outros", example = "Masculino")
	private String sexo;
	
	@ApiModelProperty(value = "Objeto contendo informações de endereço de uma pessoa")
	@NotNull(message = "O objeto endereço de ser informado")
	private Endereco endereco;

	public Pessoa toPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setDataNascimento(this.dataNascimento);
		pessoa.setEmail(this.email);
		pessoa.setNacionalidade(this.nacionalidade);
		pessoa.setNaturalidade(this.naturalidade);
		pessoa.setNome(this.nome);
		pessoa.setSexo(this.sexo);

		return pessoa;
	}

}
