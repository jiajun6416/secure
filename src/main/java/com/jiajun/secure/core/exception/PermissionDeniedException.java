package com.jiajun.secure.core.exception;


public class PermissionDeniedException extends RuntimeException{
	private static final long serialVersionUID = 745374682977021463L;
	private String message;
	public PermissionDeniedException(String message) {
		this.message = message;
	}
	@Override
	public String getMessage() {
		return message;
	}
}