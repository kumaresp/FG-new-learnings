package com.fg.errorHandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.fg.model.Error;
import com.fg.model.Errors;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

	@InjectMocks
	GlobalExceptionHandler globalExceptionHandler;

	@Test
	void handleMethodArgumentTypeMissmatchExceptionPathVariableErrorTest() {

		// Given
		MethodArgumentTypeMismatchException methodMock = mock(MethodArgumentTypeMismatchException.class);
		MethodParameter parameterMock = mock(MethodParameter.class);
		Annotation[] annotations = { mock(PathVariable.class) };

		// When
		when(methodMock.getParameter()).thenReturn(parameterMock);
		when(parameterMock.getParameterName()).thenReturn("ultimateParentId");
		when(parameterMock.getParameterAnnotations()).thenReturn(annotations);

		ResponseEntity<Error> errorResponseEntity = globalExceptionHandler
				.handleMethodArgumentTypeMissmatchException(methodMock);

		// Then
		assertNotNull(errorResponseEntity);
		assertNotNull(errorResponseEntity.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, errorResponseEntity.getStatusCode());

		Errors errors = (Errors) errorResponseEntity.getBody();
		assertEquals(1, errors.getValidationErrors().size());
	//	assertEquals(1, errors.getValidationErrors().get(0).getPathParameter());

	}

	@Test
	void handleMethodArgumentTypeMissmatchExceptionQueryParameterErrorTest() {

		// Given
		MethodArgumentTypeMismatchException methodMock = mock(MethodArgumentTypeMismatchException.class);
		MethodParameter parameterMock = mock(MethodParameter.class);
		Annotation[] annotations = { mock(PathVariable.class) };

		// When
		when(methodMock.getParameter()).thenReturn(parameterMock);
		when(parameterMock.getParameterName()).thenReturn("ultimateParentId");
		when(parameterMock.getParameterAnnotations()).thenReturn(annotations);

		ResponseEntity<Error> errorResponseEntity = globalExceptionHandler
				.handleMethodArgumentTypeMissmatchException(methodMock);

		// Then
		assertNotNull(errorResponseEntity);
		assertNotNull(errorResponseEntity.getBody());
		assertEquals(HttpStatus.BAD_REQUEST, errorResponseEntity.getStatusCode());

		Errors errors = (Errors) errorResponseEntity.getBody();
		assertEquals(1, errors.getValidationErrors().size());
		assertEquals("ultimateParentId", errors.getValidationErrors().get(0).getQueryParameter());

	}

	@Test
	void handleHttpMediaTypeNotSupportedExceptionTest() {

		// Given
		HttpMediaTypeNotSupportedException mock = mock(HttpMediaTypeNotSupportedException.class);

		// When
		when(mock.getMessage()).thenReturn("Unsupported Media Type");
		ResponseEntity<Error> errorResponseEntity = globalExceptionHandler
				.handleHttpMediaTypeNotSupportedException(mock);

		// Then
		assertNotNull(errorResponseEntity);
		assertNotNull(errorResponseEntity.getBody() != null);
		assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, errorResponseEntity.getStatusCode());
		assertEquals("Unsupported Media Type", errorResponseEntity.getBody().getErrorName());

	}

}
