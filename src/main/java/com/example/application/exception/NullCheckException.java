package com.example.application.exception;

public class NullCheckException extends NullPointerException{

	private static final long serialVersionUID = 1L;

	public NullCheckException() {
		super();
	}

	public NullCheckException(String s) {
		super(s);
	}
	

}
