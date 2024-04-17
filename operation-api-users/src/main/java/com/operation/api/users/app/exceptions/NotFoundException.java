package com.operation.api.users.app.exceptions;

public class NotFoundException extends GlobalApiException {

	private static final long serialVersionUID = 1L;

	public NotFoundException(String msg) {
		super(msg);
	}
	
}
