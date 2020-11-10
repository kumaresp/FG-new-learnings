package com.fg.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
public class IdpConfigs {
	
	@Value("${idp.base.url}")
	private String idpBaseUrl;
	
	@Value("DEV_intUser_app")
	private String idpAccessToken;
	
	@Value("DEV_USERS_service")
	private String idpAPIkey;
	
	@Value("POC")
	private IdpFirmEnum idpFirmEnum;

}
