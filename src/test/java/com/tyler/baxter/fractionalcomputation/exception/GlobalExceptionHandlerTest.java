package com.tyler.baxter.fractionalcomputation.exception;

import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.tyler.baxter.fractionalcomputation.GlobalExceptionHandler;

import static org.junit.Assert.assertEquals;

public class GlobalExceptionHandlerTest {

	private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

	@Test
	public void testHandleIllegalArgumentException() {
		IllegalArgumentException exception = new IllegalArgumentException("Test exception message");

		ResponseEntity<String> responseEntity = exceptionHandler.handleIllegalArgumentException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Input error - please review input expression and order value.", responseEntity.getBody());
	}

	@Test
	public void testHandleBadRequestException() {
		BadRequestException exception = new BadRequestException("Test exception message");

		ResponseEntity<String> responseEntity = exceptionHandler.handleBadRequestException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Input not recognized.", responseEntity.getBody());
	}

	@Test
	public void testHandleNumberFormatException() {
		NumberFormatException exception = new NumberFormatException("Test exception message");

		ResponseEntity<String> responseEntity = exceptionHandler.handleNumberFormatException(exception);

		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Input not recognized.", responseEntity.getBody());
	}
}
