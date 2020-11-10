package com.fg.errorHandling;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HeaderValidationFailedExcpetion extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private final String headerName;
	private final boolean invalid;

	public HeaderValidationFailedExcpetion(String headerName, boolean invalid) {

		this.headerName=headerName;
		this.invalid=invalid;
	}

}
