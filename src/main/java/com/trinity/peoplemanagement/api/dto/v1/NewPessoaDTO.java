package com.trinity.peoplemanagement.api.dto.v1;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.trinity.peoplemanagement.domain.model.Pessoa;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewPessoaDTO {

	@ApiModelProperty(value = "Nome da pessoa", example = "Marcone", required = true)
	@NotNull(message = "O Nome não pode ser Nulo")
	@NotEmpty(message = "O Nome não pode ser vazio ")
	private String nome;

	@ApiModelProperty(value = "Nome da pessoa", example = "Marcone", required = true)
	@Email(message = "Formato de email inválido")
	private String email;

	@ApiModelProperty(value = "Data de anivesário da pessoa", example = "1994-05-11", required = true)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	@ApiModelProperty(value = "Naturalidade da pessoa", example = "Recife")
	private String naturalidade;

	@ApiModelProperty(value = "Nacionalidade da pessoa", example = "Brasileiro")
	private String nacionalidade;

	@ApiModelProperty(value = "CPF da pessoa - Deve ser um cpf válido e não pode ser igual a um já cadastrado", example = "marcone@mail.com", required = true)
	@CPF(message = "CPF informado é inválido")
	private String cpf;

	@ApiModelProperty(value = "Sexo da pessoa, Masculino, Feminino e Outros", example = "Masculino")
	private String sexo;

	public Pessoa toPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(this.cpf);
		pessoa.setDataNascimento(this.dataNascimento);
		pessoa.setEmail(this.email);
		pessoa.setNacionalidade(this.nacionalidade);
		pessoa.setNaturalidade(this.naturalidade);
		pessoa.setNome(this.nome);
		pessoa.setSexo(this.sexo);

		return pessoa;
	}

}
