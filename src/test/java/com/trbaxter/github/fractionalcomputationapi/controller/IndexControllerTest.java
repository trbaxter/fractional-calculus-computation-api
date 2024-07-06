package com.trbaxter.github.fractionalcomputationapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.Result;
import com.trbaxter.github.fractionalcomputationapi.service.ControllerRequestProcessingService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

/**
 * IndexControllerTest is a test class for the IndexController. It uses MockMvc to test endpoints as
 * defined in that class.
 */
@WebMvcTest(IndexController.class)
@ExtendWith(SpringExtension.class)
class IndexControllerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private CaputoService caputoService;
  @MockBean private RiemannService riemannService;
  @MockBean private IntegrationService integrationService;
  @MockBean private ControllerRequestProcessingService processingService;
  @Autowired private ObjectMapper objectMapper;

  private String polynomial;
  private double alpha;
  private ControllerRequest userRequest;

  /** Sets up the test data before each test. */
  @BeforeEach
  public void setUp() {
    polynomial = "3x^2 + 2x + 1";
    alpha = 0.5;
    Integer precision = 3;
    userRequest = createRequest(polynomial, alpha, precision);
  }

  /**
   * Creates a ControllerRequest with the given parameters.
   *
   * @param polynomial the polynomial expression
   * @param alpha the order of the operation
   * @param precision the precision of the result
   * @return the created ControllerRequest
   */
  private ControllerRequest createRequest(String polynomial, double alpha, Integer precision) {
    ControllerRequest controllerRequest = new ControllerRequest();
    controllerRequest.setPolynomialExpression(polynomial);
    controllerRequest.setOrder(alpha);
    controllerRequest.setPrecision(precision);
    return controllerRequest;
  }

  /**
   * Performs a POST request with the given URL and request body.
   *
   * @param url the URL to send the request to
   * @param request the request body
   * @return the ResultActions to assert the response
   * @throws Exception if an error occurs during the request
   */
  private ResultActions performPostRequest(String url, ControllerRequest request) throws Exception {
    return mockMvc.perform(
        post(url)
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)));
  }

  /** Tests the Caputo derivative computation. */
  @Test
  void testComputeCaputoDerivative() throws Exception {
    when(processingService.processRequest(any(ControllerRequest.class), eq(caputoService)))
        .thenReturn(new ResponseEntity<>(new Result("4.514x^1.5 + 2.257x^0.5"), HttpStatus.OK));

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", userRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("4.514x^1.5 + 2.257x^0.5"));
  }

  /** Tests the Riemann-Liouville derivative computation. */
  @Test
  void testComputeRiemannLiouvilleDerivative() throws Exception {
    when(processingService.processRequest(any(ControllerRequest.class), eq(riemannService)))
        .thenReturn(
            new ResponseEntity<>(
                new Result("4.514x^1.5 + 2.257x^0.5 + 0.564x^-0.5"), HttpStatus.OK));

    performPostRequest(
            "/fractional-calculus-computation-api/derivative/riemann-liouville", userRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("4.514x^1.5 + 2.257x^0.5 + 0.564x^-0.5"));
  }

  /** Tests the Caputo integral computation. */
  @Test
  void testComputeCaputoIntegral() throws Exception {
    when(processingService.processRequest(any(ControllerRequest.class), eq(integrationService)))
        .thenReturn(
            new ResponseEntity<>(
                new Result("1.805x^2.5 + 1.505x^1.5 + 1.128x^0.5 + C"), HttpStatus.OK));

    performPostRequest("/fractional-calculus-computation-api/integral", userRequest)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.expression").value("1.805x^2.5 + 1.505x^1.5 + 1.128x^0.5 + C"));
  }

  /** Tests handling of invalid ControllerRequest. */
  @Test
  void testInvalidControllerRequest() throws Exception {
    ControllerRequest request = createRequest(null, 0.5, 3);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  /** Tests handling when precision is missing in ControllerRequest. */
  @Test
  void testPrecisionMissing() throws Exception {
    ControllerRequest request = createRequest(polynomial, alpha, null);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  /** Tests handling when precision is zero in ControllerRequest. */
  @Test
  void testPrecisionZero() throws Exception {
    ControllerRequest request = createRequest(polynomial, alpha, 0);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  /** Tests handling when precision is negative in ControllerRequest. */
  @Test
  void testPrecisionNegative() throws Exception {
    ControllerRequest request = createRequest(polynomial, alpha, -1);

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", request)
        .andExpect(status().isBadRequest());
  }

  /** Tests handling of internal server error during Caputo derivative computation. */
  @Test
  void testCaputoDerivativeInternalServerError() throws Exception {
    when(processingService.processRequest(any(ControllerRequest.class), eq(caputoService)))
        .thenThrow(new RuntimeException("Internal Server Error"));

    performPostRequest("/fractional-calculus-computation-api/derivative/caputo", userRequest)
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }

  /** Tests handling of internal server error during Riemann-Liouville derivative computation. */
  @Test
  void testRiemannLiouvilleDerivativeInternalServerError() throws Exception {
    when(processingService.processRequest(any(ControllerRequest.class), eq(riemannService)))
        .thenThrow(new RuntimeException("Internal Server Error"));

    performPostRequest(
            "/fractional-calculus-computation-api/derivative/riemann-liouville", userRequest)
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }

  /** Tests handling of internal server error during Caputo integral computation. */
  @Test
  void testCaputoIntegralInternalServerError() throws Exception {
    when(processingService.processRequest(any(ControllerRequest.class), eq(integrationService)))
        .thenThrow(new RuntimeException("Internal Server Error"));

    performPostRequest("/fractional-calculus-computation-api/integral", userRequest)
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
  }
}
