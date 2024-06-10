package com.trbaxter.github.fractionalcomputationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerResponse;
import com.trbaxter.github.fractionalcomputationapi.service.ComputationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class IndexControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ComputationService computationService;

    @InjectMocks
    private IndexController indexController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(indexController).build();
    }

    @Test
    public void testCaputoDerivative() throws Exception {
        // Arrange: Set up the request object and expected response
        ControllerRequest request = new ControllerRequest();
        request.setCoefficients(new double[]{1.0, 2.0, 3.0});
        request.setOrder(1.0);

        // Mock the computation service
        String expectedExpression = "some derivative expression";
        when(computationService.caputoFractionalDerivative(any(double[].class), any(double.class)))
                .thenReturn(expectedExpression);

        // Act: Perform the POST request
        mockMvc.perform(post("/calculate/derivative/caputo")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(request)))
               .andExpect(status().isOk()) // Assert: Verify the status is OK
               .andExpect(content().json(objectMapper.writeValueAsString(new ControllerResponse(expectedExpression)))); // Verify the response content
    }
}
