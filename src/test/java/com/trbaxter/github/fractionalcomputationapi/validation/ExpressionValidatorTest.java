package com.trbaxter.github.fractionalcomputationapi.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ExpressionValidatorTest {
    private final ExpressionValidator validator = new ExpressionValidator();

    @Test
    public void testValidExpressions() {
        assertTrue(validator.isValid("3x^2+5x-2", null));
        assertTrue(validator.isValid("3x^2 + 5x - 2", null));
        assertTrue(validator.isValid("3α^2 + 5β - γ", null));
        assertTrue(validator.isValid("-3x^2 + 5x - 2", null));
        assertTrue(validator.isValid("(3x^2) + [5x] - 2", null));
    }

    @Test
    public void testInvalidExpressions() {
        assertFalse(validator.isValid("3x^2 + 5x - 2$", null)); // Invalid character $
        assertFalse(validator.isValid("3x^2 ++ 5x -- 2", null)); // Invalid multiple operators
        assertFalse(validator.isValid("3x^2 + 5x - 2 + ^3", null)); // Invalid position of ^
        assertFalse(validator.isValid("3x^+2 +5x - 2", null));
    }

    @Test
    public void testParentheses() {
        assertTrue(validator.isValid("(3x^2) + (5x) - (2)", null));
        assertTrue(validator.isValid("[3α^2] + [5β] - (γ)", null));
        assertFalse(validator.isValid("(3x^2 + 5x - 2", null)); // Unmatched parenthesis
        assertFalse(validator.isValid("(3x^2 + [5x - 2]", null)); // Unmatched brackets
        assertFalse(validator.isValid("(3x^2] + 5x - 2", null));
    }

    @Test
    public void testEmptyExpression() {
        assertFalse(validator.isValid("", null));
    }

    @Test
    public void testNullExpression() {
        assertFalse(validator.isValid(null, null));
    }
}