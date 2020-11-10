package com.fg.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ValidationError extends Error implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("jsonPath")
	private String jsonPath;
	
	@JsonProperty("pathParameter")
	private String pathParameter;
	
	@JsonProperty("queryParameter")
	private String queryParameter;
	
	

}
