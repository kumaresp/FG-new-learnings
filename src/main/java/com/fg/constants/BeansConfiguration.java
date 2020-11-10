package com.fg.constants;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Configuration
public class BeansConfiguration {
	
	@Bean
	public RestTemplate getRestTemplate() {
		
		return new RestTemplate();
	}

}
