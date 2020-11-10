package com.fg.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
@Setter
@Getter
@NoArgsConstructor
public class ExternalRestServiceImpl implements ExternalRestService {

	@Autowired
	private RestTemplate idpRestTemplate;


	Logger logger = LoggerFactory.getLogger(ExternalRestServiceImpl.class);

	@Override
	public <T> T doExternalServiceCall(String path, HttpMethod method, HttpEntity httpEntity, Class<T> clazzType,
			String correlationId) {
 
		try {
			ResponseEntity<T> exchange = idpRestTemplate.exchange(path, method, httpEntity, clazzType);

			return exchange.getBody();
		} catch (HttpStatusCodeException ex) {

			logger.info("Response Headers"+ex.getResponseHeaders());
		}

		return null;
	}

}
