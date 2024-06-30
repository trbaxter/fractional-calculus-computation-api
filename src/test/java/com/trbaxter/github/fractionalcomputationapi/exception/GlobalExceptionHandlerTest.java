package com.trbaxter.github.fractionalcomputationapi.exception;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Import(GlobalExceptionHandler.class)
@WebMvcTest(GlobalExceptionHandlerTest.MockController.class)
class GlobalExceptionHandlerTest {

  @Autowired private MockMvc mockMvc;

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

  @RestController
  static class MockController {
    @GetMapping("/trigger-exception")
    public String triggerException() {
      throw new RuntimeException("Test exception");
    }
  }
}
