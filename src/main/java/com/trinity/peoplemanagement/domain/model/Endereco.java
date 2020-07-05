package com.trinity.peoplemanagement.domain.model;

import javax.persistence.Embeddable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ApiModel("Endereco")
@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Endereco {

	@ApiModelProperty(value = "Rua onde a pessoa mora", example = "Rua das Ninfas")
	private String rua;
	
	@ApiModelProperty(value = "Número da sua residência", example = "57")
	private String numero;
	
	@ApiModelProperty(value = "Bairro onde reside", example = "Graças")
	private String bairro;
	
	@ApiModelProperty(value = "Cidade onde reside", example = "Recife")
	private String cidade;
	
	@ApiModelProperty(value = "Estado onde reside", example = "PE")
	private String uf;
	
	@ApiModelProperty(value = "CEP do seu endereço", example = "54730000")
	private String cep;
	
	@ApiModelProperty(value = "Informações complementares", example = "Em frente ao Shopping Recife, Apartamento 15, Andar 7")
	private String complemento;

}
