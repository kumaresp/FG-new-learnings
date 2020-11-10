package com.fg.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InlineObject2 {

	@JsonProperty("access_token")
	private String accessToken;

}
