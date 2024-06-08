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
		validateInput(expression, order);

		// Derivative of order 0 should just return the function back to user
		if (order == 0) {
			return expression;
		}

		// Parse through the expression and break into terms that can be evaluated
		List<String> terms = expressionParserService.parseExpression(expression);

		List<Double> coefficients = new ArrayList<>();
		List<Character> variables = new ArrayList<>();
		List<Double> exponents = new ArrayList<>();

		// Add the coefficients, variables, and exponents from terms into the arraylists
		termProcessingService.fillOutLists(terms, coefficients, variables, exponents);

		List<Double> newCoefficients = new ArrayList<>();
		List<Double> newExponents = new ArrayList<>();

		// Perform the derivative operation and use it to fill out the new arraylists to create new terms
        mathOperationsService.performCalculations(coefficients, exponents, order, newCoefficients, newExponents);

		// Reassemble all the new terms back into a single expression
		return mathOperationsService.reassembleExpandedExpression(newCoefficients, variables, newExponents);
	}

	// Checks for empty/null expressions or negative orders that can't be handled yet
	private void validateInput(String expression, double order) {
		if (expression == null || expression.isEmpty()) {
			throw new IllegalArgumentException("Input error - please review input expression and order value.");
		}
		if (!expression.matches("[\\d+xX^+\\-*/().\\s]+")) {
			throw new IllegalArgumentException("Invalid mathematical expression format.");
		}
		if (order < 0) {
			throw new IllegalArgumentException("Operation order must be greater than equal to zero.");
			// TODO: Expand this functionality in the future.
		}
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



