package com.trinity.peoplemanagement.domain.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.undefined.hemoapp.api.domain.exception.ErrorItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StandardErrorSpring implements Serializable {

	private static final long serialVersionUID = -2166865923471144622L;

	private Long timestamp;
	private Integer status;
	private String error;
	private List<ErrorItem> validations = new ArrayList<>();
	private String message;
	private String path;

}
