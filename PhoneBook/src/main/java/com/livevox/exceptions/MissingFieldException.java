package com.livevox.exceptions;

import org.springframework.lang.Nullable;

public class MissingFieldException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MissingFieldException (@Nullable String message) {
		super (message);
	}
	
	public MissingFieldException(@Nullable String message, @Nullable Throwable throwable) {
		super(message, throwable);
	}

}
