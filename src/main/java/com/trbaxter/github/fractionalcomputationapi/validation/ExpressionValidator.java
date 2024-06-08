package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Stack;

public class ExpressionValidator implements ConstraintValidator<ValidExpression, String> {

    @Override
    public boolean isValid(String expression, ConstraintValidatorContext context) {
        if (expression == null || expression.isEmpty()) {
            return false;
        }
        return isExpressionValid(expression);
    }

    private boolean isExpressionValid(String expression) {
        Stack<Character> bracketsStack = new Stack<>();
        boolean lastCharWasOperator = false;
        boolean lastCharWasExponent = false;

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isLetter(c) || Character.isDigit(c) || Character.isWhitespace(c)) {
                lastCharWasOperator = false;
                lastCharWasExponent = false;
                continue; // Allow all letters (English & Greek), digits, and whitespace
            }
            if (isMathOperator(c) || c == '^') {
                if (lastCharWasOperator) {
                    return false; // Consecutive math operators found
                }
                if (c == '^') {
                    if (i == 0 || !(Character.isLetterOrDigit(expression.charAt(i - 1)))) {
                        return false; // Invalid position of ^
                    }
                    lastCharWasExponent = true;
                } else {
                    if (lastCharWasExponent) {
                        return false; // An operator cannot directly follow an exponent symbol
                    }
                    lastCharWasOperator = true;
                }
                continue; // Allow math operators and exponents
            }
            if (isBracket(c)) {
                lastCharWasOperator = false;
                lastCharWasExponent = false;
                if (c == '(' || c == '[') {
                    bracketsStack.push(c);
                } else if (c == ')' || c == ']') {
                    if (bracketsStack.isEmpty() || !isMatchingBracket(bracketsStack.pop(), c)) {
                        return false; // Mismatched brackets
                    }
                }
                continue;
            }
            return false; // Invalid character found
        }
        return bracketsStack.isEmpty(); // Ensure all brackets are properly closed
    }

    private boolean isMathOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private boolean isBracket(char c) {
        return c == '(' || c == ')' || c == '[' || c == ']';
    }

    private boolean isMatchingBracket(char open, char close) {
        return (open == '(' && close == ')') || (open == '[' && close == ']');
    }
}