package com.tyler.baxter.fractionalcomputation.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.tyler.baxter.fractionalcomputation.service.ComputationService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(ComputationController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ComputationService computationService;

	@Test
  public void testDerivativeEndpoint() throws Exception {

    when(computationService.derivative("2x+5", 1)).thenReturn(ResponseEntity.ok("2"));

    mockMvc
        .perform(MockMvcRequestBuilders.get("/calculate/derivative?expression=2x+5&order=1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(content().string("2"));
  }
}
