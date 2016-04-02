package com.lei.shiro;

import org.apache.shiro.authc.AuthenticationException;

public class IncorrectCapchaException extends AuthenticationException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3263094066433160567L;
	
	
	public IncorrectCapchaException(){
		super();
	}
	public IncorrectCapchaException(String message,Throwable cause){
		super(message,cause);
		
	}
	public IncorrectCapchaException(String message){
		super(message);
	}
	public IncorrectCapchaException(Throwable cause){
		super(cause);
	}
	

}
