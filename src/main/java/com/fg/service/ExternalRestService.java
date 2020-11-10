package com.fg.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;

public interface ExternalRestService {
	
	<T> T doExternalServiceCall(String path,HttpMethod method,HttpEntity httpEntity,Class<T> clazzType,String correlationId);

}
