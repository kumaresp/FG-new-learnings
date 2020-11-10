package com.fg.constants;

import java.util.EnumMap;

import org.springframework.http.HttpStatus;

import com.fg.model.Error;

import lombok.Getter;

public class UserErrorUtils {

	private static final EnumMap<HttpStatus, Error> errorMessages;

	static {

		errorMessages = new EnumMap<>(HttpStatus.class);
		errorMessages.put(HttpStatus.BAD_REQUEST,
				createError(ErrorConstants.BAD_REQUEST, UserErrorUtils.ErrorName.BADREQUEST.getValue()));

	}

	private static Error createError(String errorMessage, String errorCode) {

		Error error = new Error();
		error.setErrorName(errorCode);
		error.setMessage(errorMessage);

		return error;

	}

	@Getter
	public enum ErrorName {

		BADREQUEST("badRequest"),
		FIELD_HAS_INVALID_VALUE("fieldHasInvalidValue"),
		BODY_DOES_NOT_MATCH_SCHEMA("bodyDoesNotMatchSchema");

		private final String value;

		ErrorName(String value) {
			this.value = value;
		}

	}

}
