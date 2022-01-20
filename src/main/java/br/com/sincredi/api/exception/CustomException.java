package br.com.sincredi.api.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private HttpStatus httpStatus;

	public CustomException(String message) {
		super(message);
	}
	
	public CustomException(String message, HttpStatus httpStatus) {
		this(message);
		this.httpStatus = httpStatus;
	}

}
