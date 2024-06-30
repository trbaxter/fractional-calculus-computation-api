package com.trbaxter.github.fractionalcomputationapi.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * IndexControllerTest is a test class for the IndexController.<br>
 * It uses MockMvc to test endpoints as defined in that class.
 */
@WebMvcTest(IndexController.class)
@ExtendWith(SpringExtension.class)
class IndexControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private CaputoService caputoService;
  @MockBean private RiemannService riemannService;
  @MockBean private IntegrationService integrationService;
  @Autowired private ObjectMapper objectMapper;

  private String polynomial;
  private double alpha;
  private Integer precision;
  private ControllerRequest userRequest;

  @BeforeEach
  public void setUp() {
    polynomial = "3x^2 + 2x + 1";
    alpha = 0.5;
    precision = 3;
    userRequest = createRequest(polynomial, alpha, precision);
  }

  private ControllerRequest createRequest(String polynomial, double alpha, Integer precision) {
    ControllerRequest controllerRequest = new ControllerRequest();
    controllerRequest.setPolynomialExpression(polynomial);
    controllerRequest.setOrder(alpha);
    controllerRequest.setPrecision(precision);
    return controllerRequest;
  }

  private ResultActions performPostRequest(String url, ControllerRequest request) throws Exception {
    return mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));
  }

  @Test
  void testComputeCaputoDerivative() throws Exception {
    when(caputoService.evaluateExpression(
            userRequest.getPolynomialExpression(),
            userRequest.getOrder(),
            userRequest.getPrecision()))
        .thenReturn("4.514x^1.5 + 2.257x^0.5");

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", userRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("4.514x^1.5 + 2.257x^0.5"));
  }

  @Test
  void testComputeRiemannLiouvilleDerivative() throws Exception {
    when(riemannService.evaluateExpression(
            userRequest.getPolynomialExpression(),
            userRequest.getOrder(),
            userRequest.getPrecision()))
        .thenReturn("4.514x^1.5 + 2.257x^0.5 + 0.564x^-0.5");

    performPostRequest(
            "/fractional-calculus-computation-api/derivative/riemann-liouville", userRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("4.514x^1.5 + 2.257x^0.5 + 0.564x^-0.5"));
  }

  @Test
  void testComputeCaputoIntegral() throws Exception {
    when(integrationService.evaluateExpression(
            userRequest.getPolynomialExpression(),
            userRequest.getOrder(),
            userRequest.getPrecision()))
        .thenReturn("1.805x^2.5 + 1.505x^1.5 + 1.128x^0.5 + C");

    performPostRequest("/fractional-calculus-computation-api/integral", userRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("1.805x^2.5 + 1.505x^1.5 + 1.128x^0.5 + C"));
  }

  @Test
  void testInvalidControllerRequest() throws Exception {
    ControllerRequest request = createRequest(null, 0.5, 3);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  @Test
  void testPrecisionMissing() throws Exception {
    ControllerRequest request = createRequest(polynomial, alpha, null);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  @Test
  void testPrecisionZero() throws Exception {
    ControllerRequest request = createRequest(polynomial, alpha, 0);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  @Test
  void testPrecisionNegative() throws Exception {
    ControllerRequest request = createRequest(polynomial, alpha, -1);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  @Test
  void testCaputoDerivativeInternalServerError() throws Exception {
    when(caputoService.evaluateExpression(
            userRequest.getPolynomialExpression(),
            userRequest.getOrder(),
            userRequest.getPrecision()))
        .thenThrow(new RuntimeException("Internal Server Error"));

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", userRequest)
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }

  @Test
  void testRiemannLiouvilleDerivativeInternalServerError() throws Exception {
    when(riemannService.evaluateExpression(
            userRequest.getPolynomialExpression(),
            userRequest.getOrder(),
            userRequest.getPrecision()))
        .thenThrow(new RuntimeException("Internal Server Error"));

    performPostRequest(
            "/fractional-calculus-computation-api/derivative/riemann-liouville", userRequest)
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }

  @Test
  void testCaputoIntegralInternalServerError() throws Exception {
    when(integrationService.evaluateExpression(
            userRequest.getPolynomialExpression(),
            userRequest.getOrder(),
            userRequest.getPrecision()))
        .thenThrow(new RuntimeException("Internal Server Error"));

    performPostRequest("/fractional-calculus-computation-api/integral", userRequest)
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }

  @Test
  void testUnknownServiceType() throws Exception {
    IndexController controller =
        new IndexController(caputoService, riemannService, integrationService);

    Method method =
        IndexController.class.getDeclaredMethod(
            "evaluateExpression", Object.class, String.class, double.class, Integer.class);
    method.setAccessible(true);

    try {
      method.invoke(controller, new UnknownService(), polynomial, alpha, precision);
      fail("Expected IllegalArgumentException to be thrown");
    } catch (InvocationTargetException e) {
      Throwable cause = e.getCause();
      assertInstanceOf(IllegalArgumentException.class, cause);
      assertEquals("Unknown service type", cause.getMessage());
    }
  }

  private static class UnknownService {}
}
