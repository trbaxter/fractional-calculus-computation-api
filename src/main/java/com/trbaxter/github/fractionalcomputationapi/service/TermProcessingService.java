package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.service.parser.ParsedTerm;
import com.trbaxter.github.fractionalcomputationapi.service.parser.TermParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TermProcessingService implements TermProcessor {

    private final TermParser termParser;

    @Autowired
    public TermProcessingService(TermParser termParser) {
        this.termParser = termParser;
    }

    @Override
    public void fillOutLists(List<String> terms, List<Double> coefficients, List<Character> variables,
                             List<Double> exponents) {
        for (String term : terms) {
            ParsedTerm parsedTerm = termParser.parseTerm(term);
            coefficients.add(parsedTerm.getCoefficient());
            variables.add(parsedTerm.getVariable());
            exponents.add(parsedTerm.getExponent());
        }
    }
}
