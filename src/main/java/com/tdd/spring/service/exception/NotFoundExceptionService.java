package com.tdd.spring.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class NotFoundExceptionService extends RuntimeException{
	private static final long serialVersionUID = -7925190019347949917L;

	public NotFoundExceptionService(String message) {
		super(message);
	}
}
