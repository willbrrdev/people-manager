package com.trinity.peoplemanagement.api.dto.v1;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.trinity.peoplemanagement.domain.model.Pessoa;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class NewPessoaDTO {

	@NotNull(message = "O Nome não pode ser Nulo")
	@NotEmpty(message = "O Nome não pode ser vazio ")
	private String nome;

	@Email(message = "Formato de email inválido")
	private String email;

	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate dataNascimento;

	private String naturalidade;

	private String nacionalidade;

	@CPF(message = "CPF informado é inválido")
	private String cpf;

	private String sexo;
	
	public NewPessoaDTO(Pessoa pessoa) {
		this.init(pessoa);
	}

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
	
	private void init(Pessoa pessoa) {
		this.cpf = pessoa.getCpf();
		this.dataNascimento = pessoa.getDataNascimento();
		this.email = pessoa.getEmail();
		this.nacionalidade = pessoa.getNacionalidade();
		this.naturalidade = pessoa.getNaturalidade();
		this.nome = pessoa.getNome();
		this.sexo = pessoa.getSexo();
	}

}
