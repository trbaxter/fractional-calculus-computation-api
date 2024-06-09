package com.trbaxter.github.fractionalcomputationapi.service.parser;

public class ParsedTerm {
    private final double coefficient;
    private final char variable;
    private final double exponent;

    public ParsedTerm(double coefficient, char variable, double exponent) {
        this.coefficient = coefficient;
        this.variable = variable;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public char getVariable() {
        return variable;
    }

    public double getExponent() {
        return exponent;
    }
}
