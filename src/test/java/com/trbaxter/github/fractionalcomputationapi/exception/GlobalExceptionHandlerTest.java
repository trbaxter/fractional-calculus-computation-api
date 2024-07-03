package com.trbaxter.github.fractionalcomputationapi.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.controller.IndexController;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Import({GlobalExceptionHandler.class, IndexController.class})
@WebMvcTest(IndexController.class)
class GlobalExceptionHandlerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private CaputoService caputoService;
  @MockBean private RiemannService riemannService;
  @MockBean private IntegrationService integrationService;
  @Autowired private ObjectMapper objectMapper;

  private ListAppender<ILoggingEvent> listAppender;

  @BeforeEach
  public void setUp() {
    Logger logger = (Logger) LoggerFactory.getLogger(GlobalExceptionHandler.class);
    listAppender = new ListAppender<>();
    listAppender.start();
    logger.addAppender(listAppender);
  }

  /** Tests the handling of a generic exception. */
  @Test
  void testHandleException() throws Exception {
    mockMvc
        .perform(get("/trigger-exception").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError())
        .andExpect(content().json("{\"expression\": \"Internal Server Error\"}"));
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
            content().json("{\"expression\": \"Bad Request: Malformed JSON request body\"}"));
  }

  /** Tests the handling of BadRequestException. */
  @Test
  void testHandleBadRequestException() throws Exception {
    String invalidRequest =
        "{ \"polynomialExpression\": \"3*x^2 + 2x + 1\", \"order\": 0.5, \"precision\": 1 }";

    doThrow(new BadRequestException("Polynomial expression contains invalid characters."))
        .when(integrationService)
        .evaluateExpression(any(String.class), any(double.class), any(Integer.class));

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/integral")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequest))
        .andExpect(status().isBadRequest())
        .andExpect(
            jsonPath("$.expression")
                .value("Bad Request: Polynomial expression contains invalid characters."));

    List<ILoggingEvent> logEvents = listAppender.list;
    assertEquals(1, logEvents.size(), "Number of log events");
    assertEquals(
        "Bad request: Polynomial expression contains invalid characters.",
        logEvents.getFirst().getFormattedMessage(),
        "Log message");
    assertEquals(ch.qos.logback.classic.Level.WARN, logEvents.getFirst().getLevel(), "Log level");
  }

  /** Tests the handling of UndefinedGammaFunctionException. */
  @Test
  void testHandleUndefinedGammaFunctionException() throws Exception {
    String request =
        "{ \"polynomialExpression\": \"3*x^2 + 2x + 1\", \"order\": -0.5, \"precision\": 1 }";

    doThrow(new UndefinedGammaFunctionException("Gamma function is undefined for the given input."))
        .when(integrationService)
        .evaluateExpression(any(String.class), any(double.class), any(Integer.class));

    mockMvc
        .perform(
            post("/fractional-calculus-computation-api/integral")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request))
        .andExpect(status().isBadRequest())
        .andExpect(
            jsonPath("$.expression")
                .value("Bad Request: Gamma function is undefined for the given input."));

    List<ILoggingEvent> logEvents = listAppender.list;
    assertEquals(1, logEvents.size(), "Number of log events");
    assertEquals(
        "Undefined gamma function input: Gamma function is undefined for the given input.",
        logEvents.getFirst().getFormattedMessage(),
        "Log message");
    assertEquals(ch.qos.logback.classic.Level.WARN, logEvents.getFirst().getLevel(), "Log level");
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
