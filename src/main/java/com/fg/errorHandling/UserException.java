package com.fg.errorHandling;

import org.springframework.http.HttpStatus;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserException extends RuntimeException{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatusCode;

	private final String customCode;

	public UserException(HttpStatus httpStatusCode, String customCode,String message) {
		super(message);
		this.httpStatusCode = httpStatusCode;
		this.customCode = customCode;
	}

}
