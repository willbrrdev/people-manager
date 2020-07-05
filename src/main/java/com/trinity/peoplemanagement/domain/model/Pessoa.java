package com.trinity.peoplemanagement.domain.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity(name = "pessoas")
@ApiModel("Pessoa")
public class Pessoa {

	@ApiModelProperty(value = "Id da pessoa", example = "1")
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ApiModelProperty(value = "Nome da pessoa", example = "Marcone", required = true)
	@Column(nullable = false)
	private String nome;

	@ApiModelProperty(value = "Email da pessoa", example = "marcone@mail.com")
	@Column(unique = true)
	private String email;

	@ApiModelProperty(value = "Data de anivesário da pessoa", example = "1994-05-11", required = true)
	@JsonFormat(shape = Shape.STRING, pattern = "yyyy-MM-dd")
	@Column(name = "data_nascimento", nullable = false)
	private LocalDate dataNascimento;

	@ApiModelProperty(value = "Naturalidade da pessoa", example = "Recife")
	@Column
	private String naturalidade;

	@ApiModelProperty(value = "Nacionalidade da pessoa", example = "Brasileiro")
	@Column
	private String nacionalidade;

	@ApiModelProperty(value = "CPF da pessoa - Deve ser um cpf válido e não pode ser igual a um já cadastrado", example = "marcone@mail.com", required = true)
	@Column(unique = true)
	private String cpf;

	@ApiModelProperty(value = "Sexo da pessoa, Masculino, Feminino e Outros", example = "Masculino")
	@Column
	private String sexo;
	
	@Embedded
	private Endereco endereco;

}
