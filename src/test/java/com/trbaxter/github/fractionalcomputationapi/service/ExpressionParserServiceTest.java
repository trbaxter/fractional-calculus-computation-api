package com.trbaxter.github.fractionalcomputationapi.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ExpressionParserServiceTest {

    @Autowired
    private ExpressionParserService expressionParserService;

    @Test
    public void testParseExpression() {
        String expression1 = "3x^2+2x-5";
        String expression2 = "3x^2 + 5x + 1";
        String expression3 = "3x^2      + 5x          +1";

        List<String> terms1 = expressionParserService.parseExpression(expression1);
        List<String> terms2 = expressionParserService.parseExpression(expression2);
        List<String> terms3 = expressionParserService.parseExpression(expression3);

        List<String> expectedTerms = List.of("3x^2", "+5x", "+1");
    }
}
