package com.trbaxter.github.fractionalcomputationapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TermProcessingServiceTest {

    @Autowired
    private TermProcessingService termProcessingService;

    @Test
    public void testFillOutLists() {
        List<String> terms = List.of("3x^2", "2x", "-5");
        List<Double> coefficients = new ArrayList<>();
        List<Character> variables = new ArrayList<>();
        List<Double> exponents = new ArrayList<>();

        termProcessingService.fillOutLists(terms, coefficients, variables, exponents);

        assertEquals(3, coefficients.size());
        assertEquals(3.0, coefficients.get(0));
        assertEquals(2.0, coefficients.get(1));
        assertEquals(0.0, coefficients.get(2));  // Should probably change the service so this reads -5.0

        assertEquals(3, variables.size());
        assertEquals('x', variables.get(0));
        assertEquals('x', variables.get(1));
        assertEquals(' ', variables.get(2));

        assertEquals(3, exponents.size());
        assertEquals(2.0, exponents.get(0));
        assertEquals(1.0, exponents.get(1));
        assertEquals(0.0, exponents.get(2));
    }
}
