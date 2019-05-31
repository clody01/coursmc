package com.clody.springboot.coursmc.services.excepetions;

public class DataIntegrityException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	
	public DataIntegrityException(String message) {
		super(message);
	}
	
	public DataIntegrityException(String message, Throwable cause) {
		super(message, cause);
	}
}
