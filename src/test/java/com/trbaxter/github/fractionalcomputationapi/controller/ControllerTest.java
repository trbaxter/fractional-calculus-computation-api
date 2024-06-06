package com.trbaxter.github.fractionalcomputationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.dto.ControllerRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.trbaxter.github.fractionalcomputationapi.service.ComputationService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController.class)
public class ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ComputationService computationService;

	@Test
  	public void testDerivativeEndpoint() throws Exception {

    	when(computationService.derivative("2x+5", 1)).thenReturn("2");

		ControllerRequest request = new ControllerRequest();
		request.setExpression("2x+5");
		request.setOrder(1.0);

    	mockMvc.perform(post("/calculate/derivative")
		   	   .contentType(MediaType.APPLICATION_JSON)
			   .content(objectMapper.writeValueAsString(request)))
			   .andExpect(status().isOk())
			   .andExpect(content().string("2"));
   }
}
