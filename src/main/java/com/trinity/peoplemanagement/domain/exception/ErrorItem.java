package com.trinity.peoplemanagement.domain.exception;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Classe Responsavel por obter os fields e mensagens de erro dos bean
 * validations.
 * 
 * @author <a href="mailto:williamsgomes45@gmail.com">Williams Gomes</a>
 * 
 * @since 10 de Outubro de 2019
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ErrorItem implements Serializable {

	private static final long serialVersionUID = 2742619349592940845L;

	@EqualsAndHashCode.Include
	private String fieldName;
	@EqualsAndHashCode.Include
	private String message;

}