package com.fg.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class LibraryBeansConfiguration {

	@Value("3000")
	private int connectionTimeout;

	@Value("3000")
	private int readTimeout;

	@Bean
	public RestTemplate idpServerRestTemplate() {

		return new RestTemplate(getClientHttpRequestFactory());
	}

	private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() {

		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setConnectTimeout(connectionTimeout);
		httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);

		return httpComponentsClientHttpRequestFactory;
	}

}
