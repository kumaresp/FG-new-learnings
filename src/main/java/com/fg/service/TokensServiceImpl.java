package com.fg.service;

import java.text.MessageFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fg.constants.IdpConfigs;
import com.fg.constants.IdpFirmEnum;
import com.fg.constants.UserConstants;
import com.fg.model.InlineObject2;
import com.fg.model.InlineResponse2002;

@Service
public class TokensServiceImpl implements TokensService {

	@Autowired
	private ExternalRestService externalRestService;

	@Autowired
	private IdpConfigs idpConfigs;

	@Override
	public InlineResponse2002 validateAccessToken(InlineObject2 inlineObject,String x_api_key) {

		String baseUrl = MessageFormat.format(idpConfigs.getIdpBaseUrl(), idpConfigs.getIdpFirmEnum().getValue());
		String validateAccessTokenEndpoint = baseUrl + UserConstants.VALIDATE_ACCESS_TOKEN;
		
		
		return externalRestService.doExternalServiceCall(validateAccessTokenEndpoint, HttpMethod.POST, new HttpEntity<>(buildBody(inlineObject),buildHttpHeaders()) , InlineResponse2002.class, "");
	}
	private MultiValueMap<String, String> buildHttpHeaders() {

		HttpHeaders httpHeaders=new HttpHeaders();
		httpHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		httpHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE);
		httpHeaders.set(UserConstants.X_SUN_GARD_IDP_API_KEY, idpConfigs.getIdpAPIkey());


		return httpHeaders;
	}

	private MultiValueMap<String, String> buildBody(InlineObject2 inlineObject) {

		MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
		body.add("access_token", inlineObject.getAccessToken());

		return body;
	}

}
