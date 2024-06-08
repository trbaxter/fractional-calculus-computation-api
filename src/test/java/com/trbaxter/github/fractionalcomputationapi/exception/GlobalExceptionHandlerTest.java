package com.trbaxter.github.fractionalcomputationapi.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.controller.IndexController;
import com.trbaxter.github.fractionalcomputationapi.dto.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.ComputationService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = IndexController.class)
public class GlobalExceptionHandlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ComputationService computationService;

	@Test
	public void testHandleIllegalArgumentException() {
		IllegalArgumentException exception = new IllegalArgumentException("Test exception message");

		GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
		ResponseEntity<String> responseEntity = exceptionHandler.handleIllegalArgumentException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Input error - please review input expression and order value.", responseEntity.getBody());
	}

	@Test
	public void testHandleBadRequestException() {
		BadRequestException exception = new BadRequestException("Test exception message");

		GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
		ResponseEntity<String> responseEntity = exceptionHandler.handleBadRequestException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Input not recognized.", responseEntity.getBody());
	}

	@Test
	public void testHandleNumberFormatException() {
		NumberFormatException exception = new NumberFormatException("Test exception message");

		GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
		ResponseEntity<String> responseEntity = exceptionHandler.handleNumberFormatException(exception);
		assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
		assertEquals("Input not recognized.", responseEntity.getBody());
	}

	@Test
	public void whenInvalidInput_thenReturns400_andValidationErrors() throws Exception {

		ControllerRequest invalidRequest = new ControllerRequest();
		invalidRequest.setExpression("");
		invalidRequest.setOrder(-1.0);

		mockMvc.perform(post("/calculate/derivative")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(invalidRequest)))
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.expression").value("must not be blank"))
				.andExpect(jsonPath("$.order").value("must be greater than or equal to 0"));
	}
}
