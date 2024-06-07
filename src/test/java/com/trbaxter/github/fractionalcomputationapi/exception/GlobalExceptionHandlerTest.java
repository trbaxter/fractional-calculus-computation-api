package com.trbaxter.github.fractionalcomputationapi.exception;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GlobalExceptionHandlerTest {

	private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

	@Test
	public void testHandleIllegalArgumentException() {
		IllegalArgumentException exception = new IllegalArgumentException("Test exception message");

		ResponseEntity<String> responseEntity = exceptionHandler.handleIllegalArgumentException(exception);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		Assertions.assertEquals("Input error - please review input expression and order value.",
				responseEntity.getBody());
	}

	@Test
	public void testHandleBadRequestException() {
		BadRequestException exception = new BadRequestException("Test exception message");

		ResponseEntity<String> responseEntity = exceptionHandler.handleBadRequestException(exception);
		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		Assertions.assertEquals("Input not recognized.", responseEntity.getBody());
	}

	@Test
	public void testHandleNumberFormatException() {
		NumberFormatException exception = new NumberFormatException("Test exception message");

		ResponseEntity<String> responseEntity = exceptionHandler.handleNumberFormatException(exception);

		Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		Assertions.assertEquals("Input not recognized.", responseEntity.getBody());
	}
}
