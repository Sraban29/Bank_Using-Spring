package com.wipro.SR20171684.bank.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlerControllerAdvice {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody ExceptionResponse handleNotFound(ResourceNotFoundException e,
			HttpServletRequest request) {
		return new ExceptionResponse(
				e.getMessage(),
				request.getMethod(),
				request.getRequestURI()
		);
	}
	
	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody ExceptionResponse handleBadRequest(BadRequestException e,
			HttpServletRequest request) {
		return new ExceptionResponse(
				e.getMessage(),
				request.getMethod(),
				request.getRequestURI()
		);
	}
	
	@ExceptionHandler(InsufficientFundsException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody ExceptionResponse handleInsufficientFunds(InsufficientFundsException e,
			HttpServletRequest request) {
		return new ExceptionResponse(
				e.getMessage(),
				request.getMethod(),
				request.getRequestURI()
		);
	}
	
	@ExceptionHandler(NameNotMatchException.class)
	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody ExceptionResponse handleNameNotMatch(NameNotMatchException e,
			HttpServletRequest request) {
		return new ExceptionResponse(
				e.getMessage(),
				request.getMethod(),
				request.getRequestURI()
		);
	}

}
