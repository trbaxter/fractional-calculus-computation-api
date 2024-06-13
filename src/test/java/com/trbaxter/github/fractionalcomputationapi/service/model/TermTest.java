package com.trbaxter.github.fractionalcomputationapi.service.model;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TermTest {

    @Test
    public void testTermRecord() {
        BigDecimal coefficient = new BigDecimal("3.5");
        BigDecimal power = new BigDecimal("2.0");
        Term term = new Term(coefficient, power);

        assertEquals(coefficient, term.coefficient());
        assertEquals(power, term.power());
    }
}