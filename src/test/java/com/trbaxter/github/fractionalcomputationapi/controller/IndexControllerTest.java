package com.trbaxter.github.fractionalcomputationapi.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo.CaputoDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville.RiemannLiouvilleDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

/**
 * IndexControllerTest is a test class for the IndexController.<br>
 * It uses MockMvc to test endpoints as defined in that class.
 */
@WebMvcTest(IndexController.class)
public class IndexControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private CaputoDerivativeService caputoDerivativeService;

  @MockBean private RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService;

  @MockBean private CaputoIntegrationService caputoIntegrationService;

  @Autowired private ObjectMapper objectMapper;

  /** Sets up the test environment before each test. */
  @BeforeEach
  public void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  /**
   * Tests the computeCaputoDerivative endpoint with valid input.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void testComputeCaputoDerivative() throws Exception {
    String polynomialExpression = "3x^2 + 2x + 1";
    double alpha = 0.5;
    Integer precision = 3;

    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(alpha);
    request.setPrecision(precision);

    when(caputoDerivativeService.evaluateExpression(polynomialExpression, alpha, precision))
        .thenReturn("4.514x^1.5 + 2.257x^0.5");

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/derivative/caputo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("4.514x^1.5 + 2.257x^0.5"));
  }

  /**
   * Tests the computeRiemannLiouvilleDerivative endpoint with valid input.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void testComputeRiemannLiouvilleDerivative() throws Exception {
    String polynomialExpression = "3x^2 + 2x + 1";
    double alpha = 0.5;
    Integer precision = 3;

    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(alpha);
    request.setPrecision(precision);

    when(riemannLiouvilleDerivativeService.evaluateExpression(
            polynomialExpression, alpha, precision))
        .thenReturn("4.514x^1.5 + 2.257x^0.5 + 0.564x^-0.5");

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/derivative/riemann-liouville")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("4.514x^1.5 + 2.257x^0.5 + 0.564x^-0.5"));
  }

  /**
   * Tests the computeCaputoIntegral endpoint with valid input.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void testComputeCaputoIntegral() throws Exception {
    String polynomialExpression = "3x^2 + 2x + 1";
    double alpha = 0.5;
    Integer precision = 3;

    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(alpha);
    request.setPrecision(precision);

    when(caputoIntegrationService.evaluateExpression(polynomialExpression, alpha, precision))
        .thenReturn("1.805x^2.5 + 1.505x^1.5 + 1.128x^0.5 + C");

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/integral/caputo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("1.805x^2.5 + 1.505x^1.5 + 1.128x^0.5 + C"));
  }

  /**
   * Tests the computeCaputoDerivative endpoint with invalid input.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void testInvalidControllerRequest() throws Exception {
    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(null);
    request.setOrder(0.5);
    request.setPrecision(3);

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/derivative/caputo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

  /**
   * Tests the computeCaputoDerivative endpoint when an internal server error occurs.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void testCaputoDerivativeInternalServerError() throws Exception {
    String polynomialExpression = "3x^2 + 2x + 1";
    double alpha = 0.5;
    Integer precision = 3;

    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(alpha);
    request.setPrecision(precision);

    when(caputoDerivativeService.evaluateExpression(polynomialExpression, alpha, precision))
        .thenThrow(new RuntimeException("Internal Server Error"));

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/derivative/caputo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }

  /**
   * Tests the computeRiemannLiouvilleDerivative endpoint when an internal server error occurs.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void testRiemannLiouvilleDerivativeInternalServerError() throws Exception {
    String polynomialExpression = "3x^2 + 2x + 1";
    double alpha = 0.5;
    Integer precision = 3;
    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(alpha);
    request.setPrecision(3);

    when(riemannLiouvilleDerivativeService.evaluateExpression(
            polynomialExpression, alpha, precision))
        .thenThrow(new RuntimeException("Internal Server Error"));

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/derivative/riemann-liouville")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }

  /**
   * Tests the computeCaputoIntegral endpoint when an internal server error occurs.
   *
   * @throws Exception if an error occurs during the test.
   */
  @Test
  public void testCaputoIntegralInternalServerError() throws Exception {
    String polynomialExpression = "3x^2 + 2x + 1";
    double alpha = 0.5;
    Integer precision = 3;
    ControllerRequest request = new ControllerRequest();
    request.setPolynomialExpression(polynomialExpression);
    request.setOrder(alpha);
    request.setPrecision(3);

    when(caputoIntegrationService.evaluateExpression(polynomialExpression, alpha, precision))
        .thenThrow(new RuntimeException("Internal Server Error"));

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/integral/caputo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }
}