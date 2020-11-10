package com.fg.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fg.constants.UserConstants;
import com.fg.errorHandling.UserException;
import com.fg.model.InlineObject2;
import com.fg.model.InlineResponse2002;
import com.fg.service.TokensService;
import com.fg.utils.TokensUtils;

@ExtendWith(MockitoExtension.class)
public class TokensControllerTest {

	@InjectMocks
	private TokensController tokensController;

	@Mock
	private TokensService tokensService;

	@Mock
	private HttpServletRequest request;

	private static final String X_API_KEY = "DEV_intUser_app";

	@BeforeEach
	public void setUp() {

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		when(request.getHeader("x-api-key")).thenReturn(X_API_KEY);
	}

	@Test
	void validateAccessTokenSuccessTest() {

		// Given
		InlineObject2 inlineObject2 = new InlineObject2();
		inlineObject2.setAccessToken("7fe8ffffffaaaaaaaaaccccc143");

		// When
		when(tokensService.validateAccessToken(inlineObject2, X_API_KEY)).thenReturn(TokensUtils.getTokenResponse());
		ResponseEntity<InlineResponse2002> validateAccessToken = tokensController.validateAccessToken(inlineObject2);

		// Then
		assertEquals(HttpStatus.OK, validateAccessToken.getStatusCode());
		assertNotNull(validateAccessToken);
		assertNotNull(validateAccessToken.getBody());

	}

	@Test
	void validateAccessTokenExceptionTest() {

		// Given
		InlineObject2 inlineObject2 = new InlineObject2();
		inlineObject2.setAccessToken("7fe8ffffffaaaaaaaaaccccc143");

		// When
		when(tokensService.validateAccessToken(inlineObject2, X_API_KEY))
				.thenThrow(new UserException(HttpStatus.BAD_REQUEST, "", "Invalid Access Token"));
		UserException userException = assertThrows(UserException.class,
				() -> tokensController.validateAccessToken(inlineObject2));
		// Then
		assertEquals("Invalid Access Token", userException.getMessage());
		assertEquals(HttpStatus.BAD_REQUEST, userException.getHttpStatusCode());
		assertNotNull(userException.getMessage());
	}
}
