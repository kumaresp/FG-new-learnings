package com.fg.constants;

public enum IdpFirmEnum {
	
	WDS("wds"),
	POC("poc");
	
	private String value;
	private IdpFirmEnum(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}

}
