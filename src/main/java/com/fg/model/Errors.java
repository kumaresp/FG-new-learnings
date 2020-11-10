package com.fg.model;

import java.util.List;

import lombok.Getter;

@Getter
public class Errors extends Error {


	private static final long serialVersionUID = 1L;
	
	private List<ValidationError> validationErrors=null;
	
	public Errors validationErrors(List<ValidationError> validationErrors) {
		this.validationErrors=validationErrors;
		
		return this;
	}

}
