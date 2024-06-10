package com.trbaxter.github.fractionalcomputationapi.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TermTest {

    @Test
    public void testTermRecord() {
        double coefficient = 3.5;
        double power = 2.0;
        Term term = new Term(coefficient, power);

        assertEquals(coefficient, term.coefficient());
        assertEquals(power, term.power());
    }
}
