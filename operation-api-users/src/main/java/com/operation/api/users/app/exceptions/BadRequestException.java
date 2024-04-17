package com.operation.api.users.app.exceptions;

public class BadRequestException extends GlobalApiException {

	private static final long serialVersionUID = 1L;

	public BadRequestException() {
		super("Body is required");
	}

	public BadRequestException(String msg) {
		super(msg);
	}
	
}
