package com.tyler.baxter.fractionalcomputation.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ComputationService {

	public ResponseEntity<String> derivative(String expression, double order) {
		if (expression == null || expression.isEmpty()) {
			throw new IllegalArgumentException("Input error - please review input expression and order value.");
		}

		if (order < 0) {
			throw new IllegalArgumentException("Operation order must be greater than equal to zero.");
		}

		// Step 1: Parse the expression into individual terms
		List<String> terms = parseExpandedExpression(expression);

		// Step 2: Create ArrayLists for coefficients, variables, and exponents
		List<Double> coefficients = new ArrayList<>();
		List<Character> variables = new ArrayList<>();
		List<Double> exponents = new ArrayList<>();

		// Step 3: Fill out the coefficients, variables, and exponents ArrayLists
		fillOutLists(terms, coefficients, variables, exponents);

		// Step 4: Create new ArrayLists for modified coefficients and exponents
		List<Double> newCoefficients = new ArrayList<>();
		List<Double> newExponents = new ArrayList<>();

		// Step 5: Perform calculations for new coefficients and exponents
		performCalculations(coefficients, exponents, order, newCoefficients, newExponents);

		// Step 6: Reassemble the terms
		String finalExpression = reassembleExpandedExpression(newCoefficients, variables, newExponents);

		String encodedExpression = URLEncoder.encode(finalExpression, StandardCharsets.UTF_8);

		return ResponseEntity.ok(encodedExpression);
	}

	private List<String> parseExpandedExpression(String expression) {
		List<String> terms = new ArrayList<>();
		StringBuilder term = new StringBuilder();

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			if (c == '+' || c == '-') {
				terms.add(term.toString());
				term = new StringBuilder();
			} else {
				// Append the character to the current term
				term.append(c);
			}
		}

		// Add the last term
		terms.add(term.toString());

		return terms;
	}

	private void fillOutLists(List<String> terms, List<Double> coefficients, List<Character> variables,
			List<Double> exponents) {
		for (String term : terms) {
			double coefficient = 0.0; // Default coefficient is 0.0
			char variable = ' ';
			double exponent = 1.0; // Default exponent is 1.0 if no character variable is found

			// Loop through characters in the term
			StringBuilder coefficientBuilder = new StringBuilder();
			StringBuilder exponentBuilder = new StringBuilder();
			boolean variableFound = false; // Flag to track if variable is found
			boolean exponentFound = false; // Flag to track if exponent is found
			for (int i = 0; i < term.length(); i++) {
				char c = term.charAt(i);
				if ((Character.isDigit(c) || c == '.') && !variableFound) {
					// Collect coefficient
					coefficientBuilder.append(c);
				} else if (Character.isLetter(c)) {
					// Collect variable
					variable = c;
					variableFound = true;
				} else if (c == '^') {
					// Start collecting exponent
					exponentFound = true;
				} else if ((c == '+' || c == '-') && exponentFound) {
					// End collecting exponent if a '+' or '-' is encountered
					break;
				} else if (exponentFound) {
					// Collect exponent
					exponentBuilder.append(c);
				}
			}
			// Set coefficients, variables, and exponents
			if (!coefficientBuilder.isEmpty()) {
				coefficient = Double.parseDouble(coefficientBuilder.toString());
			}
			if (variable == ' ') {
				coefficient = 0.0; // Reassign coefficient to 0 if the term has an empty variable
			}
			if (!exponentBuilder.isEmpty()) {
				exponent = Double.parseDouble(exponentBuilder.toString());
			}

			// Add values to respective lists
			coefficients.add(coefficient);
			variables.add(variable);
			exponents.add(exponent);
		}
	}

	private void performCalculations(List<Double> coefficients, List<Double> exponents, double order,
			List<Double> newCoefficients, List<Double> newExponents) {
		for (int i = 0; i < coefficients.size(); i++) {
			double coefficient = coefficients.get(i);
			double exponent = exponents.get(i);

			// If variable is empty, set coefficient to 0
			if (coefficient == 0 && exponent == 0) {
				newCoefficients.add(0.0);
				newExponents.add(0.0);
			} else {
				// Check if order is a whole number
				if (order % 1 == 0) {
					// Calculate new coefficient using recursive gamma function
					double newCoefficient = coefficient * (gammaFunction((int) (exponent)))
							/ (gammaFunction((int) (exponent - order)));
					newCoefficients.add(newCoefficient);
				} else {
					throw new IllegalArgumentException("Order must be a whole number.");
					// TODO: Research on how to expand this to non-integer orders using
					// approximation methods
				}

				// Calculate new exponent
				double newExponent = exponent - order;
				newExponents.add(newExponent);
			}
		}
	}

	private long gammaFunction(int n) {
		if (n == 0) {
			return 1; // Gamma(1) = 0! = 1
		} else if (n == 1) {
			return 1; // Gamma(2) = 1! = 1
		} else if (n < 1) {
			return 0;
		} else {
			return n * gammaFunction(n - 1); // Gamma(n) = (n-1)! & gamma(n+1) = n!
		}
	}

	private String reassembleExpandedExpression(List<Double> coefficients, List<Character> variables,
			List<Double> exponents) {
		StringBuilder reassembledExpression = new StringBuilder();
		for (int i = 0; i < coefficients.size(); i++) {
			double coefficient = coefficients.get(i);
			char variable = variables.get(i);
			double exponent = exponents.get(i);

			// Append coefficient if not zero
			if (coefficient != 0) {
				if (!reassembledExpression.isEmpty()) {
					reassembledExpression.append(coefficient >= 0 ? "+" : "");
				}
				reassembledExpression.append(coefficient);
			}

			// Append variable
			if (variable != ' ') {
				reassembledExpression.append(variable);
			}

			// Append exponent if not 0
			if (exponent != 0 && exponent != 1) {
				reassembledExpression.append("^").append(exponent);
			}
		}
		return reassembledExpression.toString();
	}
}
