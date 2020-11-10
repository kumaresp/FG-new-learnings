package com.fg.errorHandling;

import static com.fg.constants.ErrorConstants.INVALID_VALUE_ERROR;
import static com.fg.constants.ErrorConstants.JSON_PATH_ERROR;

import java.lang.annotation.Annotation;
import java.util.Arrays;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import com.fg.constants.ErrorConstants;
import com.fg.constants.UserErrorUtils;
import com.fg.model.Error;
import com.fg.model.Errors;
import com.fg.model.ValidationError;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<Error> handleMethodArgumentTypeMissmatchException(MethodArgumentTypeMismatchException ex) {

		
		ValidationError validationError=new ValidationError();
		validationError.setErrorName(UserErrorUtils.ErrorName.FIELD_HAS_INVALID_VALUE.getValue());
		String parameterName = ex.getParameter().getParameterName();
		Annotation[] parameterAnnotations = ex.getParameter().getParameterAnnotations();
		final String message=String.format(INVALID_VALUE_ERROR, parameterName);
		boolean isPath = Arrays.stream(parameterAnnotations).anyMatch(annotation->annotation instanceof PathVariable);
		validationError.setJsonPath(String.format(JSON_PATH_ERROR, parameterName));
		validationError.setMessage(message);
		
		if(isPath) {
			validationError.setPathParameter(parameterName);
		}else {
			validationError.setQueryParameter(parameterName);
		}
		
		return getErrorResponseEntity(validationError,ErrorConstants.BODY_DOES_NOT_MATCH_SCHEMA_ERROR);
	}

	private ResponseEntity<Error> getErrorResponseEntity(ValidationError validationError,
			String message) {
	
		return ResponseEntity.badRequest()
				             .body(new Errors().validationErrors(Arrays.asList(validationError))
				             .message(message)
				             .errorName(UserErrorUtils.ErrorName.BODY_DOES_NOT_MATCH_SCHEMA.getValue()));
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception){
		
		Error error=new Error();
		error.setErrorName(ErrorConstants.MEDIA_TYPE_NOT_SUPPORTED);
		error.setMessage(error.getMessage());
		
		return new ResponseEntity<Error>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
