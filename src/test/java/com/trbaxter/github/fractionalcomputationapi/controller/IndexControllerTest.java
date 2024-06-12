package com.trbaxter.github.fractionalcomputationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.CaputoDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.RiemannLiouvilleDerivativeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class IndexControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private CaputoDerivativeService caputoDerivativeService;

    @MockBean
    private RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testComputeCaputoDerivative() throws Exception {
        double[] coefficients = {3.0, 2.0, 1.0};
        double alpha = 0.5;
        ControllerRequest request = new ControllerRequest();
        request.setCoefficients(coefficients);
        request.setOrder(alpha);

        when(caputoDerivativeService.computeDerivative(coefficients, alpha))
                .thenReturn("4.514x^1.500 + 2.257x^0.500");

        mockMvc.perform(post("/api/derivative/caputo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("4.514x^1.500 + 2.257x^0.500"));
    }

    @Test
    public void testComputeRiemannLiouvilleDerivative() throws Exception {
        double[] coefficients = {3.0, 2.0, 1.0};
        double alpha = 0.5;
        ControllerRequest request = new ControllerRequest();
        request.setCoefficients(coefficients);
        request.setOrder(alpha);

        when(riemannLiouvilleDerivativeService.computeDerivative(coefficients, alpha))
                .thenReturn("3.786x^1.500 + 1.893x^0.500");

        mockMvc.perform(post("/api/derivative/riemann-liouville")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(content().string("3.786x^1.500 + 1.893x^0.500"));
    }
}