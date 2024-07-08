package com.trbaxter.github.fractionalcomputationapi.exception;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.controller.IndexController;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.ControllerRequestProcessingService;
import com.trbaxter.github.fractionalcomputationapi.service.ErrorLoggingService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Import({
  GlobalExceptionHandler.class,
  IndexController.class,
  GlobalExceptionHandlerTest.MockController.class,
  ErrorLoggingService.class
})
@WebMvcTest(IndexController.class)
class GlobalExceptionHandlerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private CaputoService caputoService;
  @MockBean private RiemannService riemannService;
  @MockBean private IntegrationService integrationService;
  @MockBean private ControllerRequestProcessingService processorService;
  @Autowired private ObjectMapper objectMapper;

  /** Tests the handling of a generic exception. */
  @Test
  void testHandleException() throws Exception {
    mockMvc
        .perform(get("/trigger-exception").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError())
        .andExpect(
            content()
                .json(
                    "{\"message\": \"Internal Server Error\", \"details\": \"An unexpected error occurred\"}"));
  }

  /** Tests the handling of HttpMessageNotReadableException (malformed JSON). */
  @Test
  void testHandleHttpMessageNotReadableException() throws Exception {
    String malformedJson =
        "{ \"polynomialExpression\": \"3x^2 + 2x + 1\", \"order\": 0.5, \"precision\": ";

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/derivative/caputo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(malformedJson))
        .andExpect(status().isBadRequest())
        .andExpect(
            content()
                .json(
                    "{\"message\": \"Malformed JSON request body\", \"details\": \"Bad Request\"}"));
  }

  /** Tests the handling of BadRequestException. */
  @Test
  void testHandleBadRequestException() throws Exception {
    String invalidRequest =
        "{ \"polynomialExpression\": \"3*x^2 + 2x + 1\", \"order\": 0.5, \"precision\": 1 }";

    doThrow(new BadRequestException("Polynomial expression contains invalid characters."))
        .when(processorService)
        .processRequest(any(ControllerRequest.class), any(IntegrationService.class));

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/integral")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
        .andExpect(status().isBadRequest())
        .andExpect(
            jsonPath("$.message").value("Polynomial expression contains invalid characters."))
        .andExpect(jsonPath("$.details").value("Bad Request"));
  }

  /** Tests the handling of UndefinedGammaFunctionException. */
  @Test
  void testHandleUndefinedGammaFunctionException() throws Exception {
    String request =
        "{ \"polynomialExpression\": \"3*x^2 + 2x + 1\", \"order\": -0.5, \"precision\": 1 }";

    doThrow(new UndefinedGammaFunctionException("Gamma function is undefined for the given input."))
        .when(processorService)
        .processRequest(any(ControllerRequest.class), any(IntegrationService.class));

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/integral")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.message").value("Gamma function is undefined for the given input."))
        .andExpect(jsonPath("$.details").value("Bad Request"));
  }

  /** Mock controller to trigger exceptions for testing purposes. */
  @RestController
  static class MockController {
    @GetMapping("/trigger-exception")
    public String triggerException() {
      throw new RuntimeException("Test exception");
    }
  }
}
