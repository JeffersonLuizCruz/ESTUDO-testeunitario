package com.tdd.spring.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class BadRequestExceptionService extends RuntimeException{
	private static final long serialVersionUID = 2057442903036726536L;

	public BadRequestExceptionService(String message) {
		super(message);
	}
}
