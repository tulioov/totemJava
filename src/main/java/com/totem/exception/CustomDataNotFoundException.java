package com.totem.exception;

public class CustomDataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomDataNotFoundException() {
        super();
    }

    public CustomDataNotFoundException(String message) {
        super(message);
    }
}