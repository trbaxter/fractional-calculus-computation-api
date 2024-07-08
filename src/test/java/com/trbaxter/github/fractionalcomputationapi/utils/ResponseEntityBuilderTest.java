package com.trbaxter.github.fractionalcomputationapi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.trbaxter.github.fractionalcomputationapi.dto.ErrorResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class ResponseEntityBuilderTest {

  @Test
  void testPrivateConstructor() throws Exception {
    Constructor<ResponseEntityBuilder> testConstructor =
        ResponseEntityBuilder.class.getDeclaredConstructor();
    testConstructor.setAccessible(true);

    assertThrows(InvocationTargetException.class, testConstructor::newInstance);
  }

  @Test
  void testBuildBadRequestResponse() {
    String message = "Bad Request Message";
    ResponseEntity<ErrorResponse> response = ResponseEntityBuilder.buildBadRequestResponse(message);

    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals(message, Objects.requireNonNull(response.getBody()).message());
    assertEquals("Bad Request", response.getBody().details());
  }

  @Test
  void testBuildInternalServerErrorResponse() {
    ResponseEntity<ErrorResponse> response =
        ResponseEntityBuilder.buildInternalServerErrorResponse();

    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    assertEquals("Internal Server Error", Objects.requireNonNull(response.getBody()).message());
    assertEquals("An unexpected error occurred", response.getBody().details());
  }
}
