package com.fg.service;

import com.fg.model.InlineObject2;
import com.fg.model.InlineResponse2002;

public interface TokensService {

	InlineResponse2002 validateAccessToken(InlineObject2 inlineObject,String x_api_key);
	
	

}
