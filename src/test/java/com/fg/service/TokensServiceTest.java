package com.fg.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.MessageFormat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fg.constants.IdpConfigs;
import com.fg.constants.UserConstants;
import com.fg.errorHandling.UserException;
import com.fg.model.InlineObject2;
import com.fg.model.InlineResponse2002;
import com.fg.utils.TokensUtils;

@ExtendWith(MockitoExtension.class)
public class TokensServiceTest {

	@InjectMocks
	private TokensServiceImpl tokenService;

	@Mock
	private ExternalRestService externalRestService;

	@Mock
	private IdpConfigs idpConfigs;
	
	private static final String X_API_KEY = "DEV_intUser_app";

	@Test
	void validateAccessTokenSuccessTest() {

		// Given
		InlineObject2 inlineObject2 = new InlineObject2();
		inlineObject2.setAccessToken("7fe8ffffffaaaaaaaaaccccc143");
		String baseUrl = MessageFormat.format(idpConfigs.getIdpBaseUrl(), idpConfigs.getIdpFirmEnum().getValue());
		String validateAccessTokenEndpoint = baseUrl + UserConstants.VALIDATE_ACCESS_TOKEN;

		HttpEntity httpEntity = new HttpEntity<>(buildBody(inlineObject2), buildHttpHeaders());
		// When

		when(externalRestService.doExternalServiceCall(refEq(validateAccessTokenEndpoint), refEq(HttpMethod.POST),
				refEq(httpEntity), refEq(InlineResponse2002.class), refEq("")))
						.thenReturn(TokensUtils.getTokenResponse());
		// Then
		verify(externalRestService, times(1)).doExternalServiceCall(refEq(validateAccessTokenEndpoint),
				refEq(HttpMethod.POST), refEq(httpEntity), refEq(InlineResponse2002.class), refEq(""));
		InlineResponse2002 inlineResponse2002 = tokenService.validateAccessToken(inlineObject2, X_API_KEY);
		assertNotNull(inlineResponse2002);
		assertEquals(TokensUtils.getTokenResponse(), inlineResponse2002);

	}

	@Test
	void validateAccessTokenFailureTest() {
		String inValidAccessToken = "Invalid Access Token";

		// Given
		InlineObject2 inlineObject2 = new InlineObject2();
		inlineObject2.setAccessToken("7fe8ffffffaaaaaaaaaccccc143");
		String baseUrl = MessageFormat.format(idpConfigs.getIdpBaseUrl(), idpConfigs.getIdpFirmEnum().getValue());
		String validateAccessTokenEndpoint = baseUrl + UserConstants.VALIDATE_ACCESS_TOKEN;

		HttpEntity httpEntity = new HttpEntity<>(buildBody(inlineObject2), buildHttpHeaders());
		// When

		when(externalRestService.doExternalServiceCall(refEq(validateAccessTokenEndpoint), refEq(HttpMethod.POST),
				refEq(httpEntity), refEq(InlineResponse2002.class), refEq("")))
						.thenThrow(new UserException(HttpStatus.BAD_REQUEST, "", inValidAccessToken));
		// Then
		verify(externalRestService, times(1)).doExternalServiceCall(refEq(validateAccessTokenEndpoint),
				refEq(HttpMethod.POST), refEq(httpEntity), refEq(InlineResponse2002.class), refEq(""));
		UserException userException = assertThrows(UserException.class,
				() -> tokenService.validateAccessToken(inlineObject2, X_API_KEY));
		assertNotNull(userException.getMessage());
		assertEquals(inValidAccessToken, userException.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, userException.getHttpStatusCode());

	}

	private MultiValueMap<String, String> buildHttpHeaders() {

		HttpHeaders httpHeaders = new HttpHeaders();
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
