package com.fg.model;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString

public class Error implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("errorName")
	private String errorName;
	
	@JsonProperty("message")
	private String message;
	
	
	public Error  errorName(String errorName) {
		this.errorName=errorName;
		return this;
	}
	
	public Error  message(String message) {
		this.message=message;
		return this;
	}
	
	

}
