package com.trbaxter.github.fractionalcomputationapi.service;

import java.util.List;

public interface TermProcessor {
    void fillOutLists(List<String> terms, List<Double> coefficients, List<Character> variables, List<Double> exponents);
}
