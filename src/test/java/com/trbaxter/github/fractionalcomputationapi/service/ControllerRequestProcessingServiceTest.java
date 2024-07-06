package com.trbaxter.github.fractionalcomputationapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.Result;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ControllerRequestProcessingServiceTest {

  @Mock private CaputoService caputoService;

  @Mock private RiemannService riemannService;

  @Mock private IntegrationService integrationService;

  @InjectMocks private ControllerRequestProcessingService processingService;

  private ControllerRequest request;

  @BeforeEach
  void setUp() {
    request = new ControllerRequest();
    request.setPolynomialExpression("3x^2 + 2x + 1");
    request.setOrder(0.5);
    request.setPrecision(3);
  }

  @Test
  void testEvaluateExpressionWithCaputoService() {
    when(caputoService.evaluateExpression(any(String.class), any(double.class), any(Integer.class)))
        .thenReturn("result");

    String result = processingService.evaluateExpression(caputoService, "3x^2 + 2x + 1", 0.5, 3);

    assertEquals("result", result);
    verify(caputoService, times(1)).evaluateExpression(eq("3x^2 + 2x + 1"), eq(0.5), eq(3));
  }

  @Test
  void testEvaluateExpressionWithRiemannService() {
    when(riemannService.evaluateExpression(
            any(String.class), any(double.class), any(Integer.class)))
        .thenReturn("result");

    String result = processingService.evaluateExpression(riemannService, "3x^2 + 2x + 1", 0.5, 3);

    assertEquals("result", result);
    verify(riemannService, times(1)).evaluateExpression(eq("3x^2 + 2x + 1"), eq(0.5), eq(3));
  }

  @Test
  void testEvaluateExpressionWithIntegrationService() {
    when(integrationService.evaluateExpression(
            any(String.class), any(double.class), any(Integer.class)))
        .thenReturn("result");

    String result =
        processingService.evaluateExpression(integrationService, "3x^2 + 2x + 1", 0.5, 3);

    assertEquals("result", result);
    verify(integrationService, times(1)).evaluateExpression(eq("3x^2 + 2x + 1"), eq(0.5), eq(3));
  }

  @Test
  void testEvaluateExpressionWithUnknownService() {
    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () -> processingService.evaluateExpression(new Object(), "3x^2 + 2x + 1", 0.5, 3));

    assertEquals("Unknown service type", exception.getMessage());
  }

  @Test
  void testProcessRequestWithCaputoService() {
    when(caputoService.evaluateExpression(any(String.class), any(double.class), any(Integer.class)))
        .thenReturn("result");

    ResponseEntity<Result> response = processingService.processRequest(request, caputoService);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("result", Objects.requireNonNull(response.getBody()).expression());
    verify(caputoService, times(1)).evaluateExpression(eq("3x^2 + 2x + 1"), eq(0.5), eq(3));
  }

  @Test
  void testProcessRequestWithRiemannService() {
    when(riemannService.evaluateExpression(
            any(String.class), any(double.class), any(Integer.class)))
        .thenReturn("result");

    ResponseEntity<Result> response = processingService.processRequest(request, riemannService);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("result", Objects.requireNonNull(response.getBody()).expression());
    verify(riemannService, times(1)).evaluateExpression(eq("3x^2 + 2x + 1"), eq(0.5), eq(3));
  }

  @Test
  void testProcessRequestWithIntegrationService() {
    when(integrationService.evaluateExpression(
            any(String.class), any(double.class), any(Integer.class)))
        .thenReturn("result");

    ResponseEntity<Result> response = processingService.processRequest(request, integrationService);

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("result", Objects.requireNonNull(response.getBody()).expression());
    verify(integrationService, times(1)).evaluateExpression(eq("3x^2 + 2x + 1"), eq(0.5), eq(3));
  }
}
