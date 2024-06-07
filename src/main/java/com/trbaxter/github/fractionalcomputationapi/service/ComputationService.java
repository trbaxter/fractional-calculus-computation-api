package com.trbaxter.github.fractionalcomputationapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComputationService {

	@Autowired
	private ExpressionParserService expressionParserService;

	@Autowired
	private TermProcessingService termProcessingService;

	@Autowired
	private MathOperationsService mathOperationsService;

	public String derivative(String expression, double order) {

		if (expression == null || expression.isEmpty()) {
			throw new IllegalArgumentException(
			"Input error - please review input expression and order value.");
		}
		else if (order < 0) {
			throw new IllegalArgumentException(
			"Operation order must be greater than equal to zero.");
			// TODO: Expand this functionality in the future.
		}
		else if (order == 0) {
			return expression;
		}

		List<String> terms = expressionParserService.parseExpression(expression);

		List<Double> coefficients = new ArrayList<>();
		List<Character> variables = new ArrayList<>();
		List<Double> exponents = new ArrayList<>();

		termProcessingService.fillOutLists(terms, coefficients, variables, exponents);

		List<Double> newCoefficients = new ArrayList<>();
		List<Double> newExponents = new ArrayList<>();

		mathOperationsService.performCalculations(coefficients, exponents, order, newCoefficients, newExponents);

        return mathOperationsService.reassembleExpandedExpression(newCoefficients, variables, newExponents);
	}

//  TODO: Uncomment this when ready
//	public String integral(String expression, double order) {
//		if (expression == null || expression.isEmpty()) {
//			throw new IllegalArgumentException(
//			"Input error - please review input expression and order value.");
//		}
//		if (order < 0) {
//			throw new IllegalArgumentException("Operation order must be greater than equal to zero.");
//		    // TODO: Expand this functionality in the future.
//		}
//		if (order == 0) {
//			return expression;
//		}
//
//        return null;
//    }
}



