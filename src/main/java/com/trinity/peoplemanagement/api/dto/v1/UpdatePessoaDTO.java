package com.trinity.peoplemanagement.api.dto.v1;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.trinity.peoplemanagement.domain.model.Pessoa;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UpdatePessoaDTO {

	@NotNull(message = "O Nome não pode ser Nulo")
	@NotEmpty(message = "O Nome não pode ser vazio ")
	private String nome;

	@Email(message = "Formato de email inválido")
	private String email;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	private String naturalidade;

	private String nacionalidade;

	private String sexo;

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
