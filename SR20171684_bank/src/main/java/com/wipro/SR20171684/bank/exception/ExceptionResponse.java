package com.wipro.SR20171684.bank.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class ExceptionResponse {
	private String errorMessage;
	private String httpMethod;
	private String requestURI;
}
