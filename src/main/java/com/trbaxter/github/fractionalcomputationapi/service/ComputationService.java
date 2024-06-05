package com.trbaxter.github.fractionalcomputationapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ComputationService {

	/**
	 * Calculates the derivative of a given expression.
	 * @param expression The expression the user wants to take a derivative of.
	 * @param order The order of the derivative operator.
	 * @return The derivative of the expression parameter as a string.
	 */

	public String derivative(String expression, double order) {
		if (expression == null || expression.isEmpty()) {
			throw new IllegalArgumentException(
			"Input error - please review input expression and order value.");
		}

		if (order < 0) {
			throw new IllegalArgumentException(
			"Operation order must be greater than equal to zero.");
			// TODO: Expand this functionality in the future.
		}

		if (order == 0) {
			return expression;
		}

		List<String> terms = parseExpression(expression);

		List<Double> coefficients = new ArrayList<>();
		List<Character> variables = new ArrayList<>();
		List<Double> exponents = new ArrayList<>();

		fillOutLists(terms, coefficients, variables, exponents);

		List<Double> newCoefficients = new ArrayList<>();
		List<Double> newExponents = new ArrayList<>();

		performCalculations(coefficients, exponents, order, newCoefficients, newExponents);

        return reassembleExpandedExpression(newCoefficients, variables, newExponents);
	}

	private List<String> parseExpression(String expression) {
		List<String> terms = new ArrayList<>();
		StringBuilder term = new StringBuilder();

		for (int i = 0; i < expression.length(); i++) {    // Loops through every character of the expression parameter
			char c = expression.charAt(i);

			if (c == '+' || c == '-') {                    // Stop counting term length when hitting a + or -
				terms.add(term.toString());
				term = new StringBuilder();
			}

			else {term.append(c);}                           // Append the character to the current term
		}

		terms.add(term.toString());                        // Add the last term
		return terms;
	}

	private void fillOutLists(List<String> terms, List<Double> coefficients,
							  List<Character> variables, List<Double> exponents) {
		for (String term : terms) {
			double coefficient = 1.0; // Default coefficient is 0.0
			char variable = ' ';
			double exponent = 0.0; // Default exponent is 0.0 if no character variable is found

			// Loop through characters in the term
			StringBuilder coefficientBuilder = new StringBuilder();
			StringBuilder exponentBuilder = new StringBuilder();
			boolean variableFound = false;
			boolean exponentFound = false;
			for (int i = 0; i < term.length(); i++) {
				char c = term.charAt(i);
				if ((Character.isDigit(c) || c == '.') && !variableFound) {
					// Collect coefficient
					coefficientBuilder.append(c); // Collects the full double coefficient before a variable
				}
				else if (Character.isLetter(c)) {
					// Collect variable
					variable = c;

					if (!exponentFound) {
						exponentBuilder.append(1.0);
						exponent = Double.parseDouble(exponentBuilder.toString());
					}
					variableFound = true;
				}
				else if (c == '^') {
					// Start collecting exponent
					exponentFound = true;
				}
				else if ((c == '+' || c == '-') && exponentFound) {
					// End collecting exponent if a '+' or '-' is encountered
					break;
				}
				else if (exponentFound) {
					// Collect exponent
					exponentBuilder.replace(0, 1, String.valueOf(c));
				}
			}
			// Set coefficients, variables, and exponents
			if (!coefficientBuilder.isEmpty()) {
				coefficient = Double.parseDouble(coefficientBuilder.toString());
			}
			if (variable == ' ') {
				coefficient = 0.0; // Reassign coefficient to 0 if variable empty, since d/dx(constant) = 0
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

		if (order % 1 == 0) {

			int numOfTerms = coefficients.size();
			int orderIndex = 0;

			while (orderIndex < order) {

				// Running first time?
				if (orderIndex == 0) {
					for (int i = 0; i < numOfTerms; i++) {
						double coefficient = coefficients.get(i);
						double exponent = exponents.get(i);
						double newExponent = exponent - 1;

						if (newExponent == 0) {
							newCoefficients.add(coefficient);
							newExponents.add(newExponent);
							// break;
						}
						else if (newExponent < 0) {
							newCoefficients.add(0.0);
							newExponents.add(0.0);
							// break;
						}
						else {
							// Calculate new coefficient using recursive gamma function
							double newCoefficient = coefficient * (gammaFunction((int) (exponent)))
									/ (gammaFunction((int) (newExponent)));
							newCoefficients.add(newCoefficient);
							newExponents.add(newExponent);
						}
						// newExponents.add(newExponent);
					}
				}

				// Running again?
				if (orderIndex > 0 & orderIndex < order) {
					for (int i = 0; i < numOfTerms; i++) {
						double coefficient = newCoefficients.get(i);
						double exponent = newExponents.get(i);
						double newExponent = exponent - 1;

						if (newExponent == 0) {
							newCoefficients.set(i, coefficient);
							newExponents.set(i, newExponent);
							// break;
						}
						else if (newExponent < 0) {
							newCoefficients.set(i, 0.0);
							newExponents.set(i, 0.0);
							// break;
						}
						else {
							// Calculate new coefficient from old coefficient using recursive gamma function
							double newCoefficient = coefficient * (gammaFunction((int) (exponent)))
									/ (gammaFunction((int) (newExponent)));
							newCoefficients.set(i, newCoefficient);
							newExponents.set(i, newExponent);
						}
						// newExponents.set(i, newExponent);
					}
				}

				orderIndex++;
			}

		}
		else {
			throw new IllegalArgumentException("Only integer operations are allowed right now.");
			// TODO: Research on how to expand this to non-integer orders using other
			// techniques.
		}
	}

	private long gammaFunction(int n) {
		if (n == 1) {
			return 1;
		}
		else {
			return n * gammaFunction(n - 1);
		}
	}

	private String reassembleExpandedExpression(List<Double> coefficients,
												List<Character> variables,
												List<Double> exponents) {
		StringBuilder reassembledExpression = new StringBuilder();
		for (int i = 0; i < coefficients.size(); i++) {
			double coefficient = coefficients.get(i);
			char variable = variables.get(i);
			double exponent = exponents.get(i);

			// Append coefficient if not zero
			if (coefficient != 0.0) {

				if (!reassembledExpression.isEmpty()) {
					reassembledExpression.append(coefficient >= 0 ? " + " : "");
				}
				if (coefficient % 1 == 0) {
					int intCoefficientValue = (int) Math.round(coefficient);
					reassembledExpression.append(intCoefficientValue);
				}
				else {
					reassembledExpression.append(coefficient);
				}
			}

			// Append variable
			if (variable != ' ' & exponent > 0) {
				reassembledExpression.append(variable);
			}

			// Append exponent if not 0
			if (exponent != 0 && exponent != 1) {
				if (exponent % 1 == 0) {
					int intExponentValue = (int) Math.round(exponent);
					reassembledExpression.append("^").append(intExponentValue);
				}
				else {
					reassembledExpression.append("^").append(exponent);
				}
			}
		}
		if (reassembledExpression.toString().isEmpty()) {reassembledExpression = new StringBuilder("0");}
		return reassembledExpression.toString();
	}

	/**
	 * Calculates the integral of a given expression.
	 * @param expression The expression the user wants to take an integral of.
	 * @param order The order of the integral operator.
	 * @return The integral of the expression parameter as a string.
	 */
	public String integral(String expression, double order) {
		if (expression == null || expression.isEmpty()) {
			throw new IllegalArgumentException(
			"Input error - please review input expression and order value.");
		}
		if (order < 0) {
			throw new IllegalArgumentException("Operation order must be greater than equal to zero.");
		    // TODO: Expand this functionality in the future.
		}
		if (order == 0) {
			return expression;
		}

        return null;
    }
}



