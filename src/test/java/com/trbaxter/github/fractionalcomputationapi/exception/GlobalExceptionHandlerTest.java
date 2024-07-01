package com.trbaxter.github.fractionalcomputationapi.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.controller.IndexController;
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

@Import({GlobalExceptionHandler.class, IndexController.class})
@WebMvcTest(IndexController.class)
class GlobalExceptionHandlerTest {

  @Autowired private MockMvc mockMvc;
  @MockBean private CaputoService caputoService;
  @MockBean private RiemannService riemannService;
  @MockBean private IntegrationService integrationService;
  @Autowired private ObjectMapper objectMapper;

  @Test
  void testHandleException() throws Exception {
    mockMvc
        .perform(get("/trigger-exception").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isInternalServerError())
        .andExpect(
            content()
                .json(
                    "{\"expression\": \"Internal Server Error: No static resource trigger-exception.\"}"));
  }

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

  @RestController
  static class MockController {
    @GetMapping("/trigger-exception")
    public String triggerException() {
      throw new RuntimeException("Test exception");
    }
  }
}
