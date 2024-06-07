package com.trbaxter.github.fractionalcomputationapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpressionParserService {

    /**
     * Method responsible for parsing the math expression provided by <br/>
     * the user by breaking it up into a collection of individual terms.
     */

    public List<String> parseExpression(String expression) {
		expression = removeWhitespace(expression);

		List<String> terms = extractTerms(expression);

		return cleanTerms(terms);
	}

	// Extracts the individual terms from the provided expression
	private List<String> extractTerms(String expression) {
		List<String> terms = new ArrayList<>();
		StringBuilder term = new StringBuilder();
		int bracketLevel = 0;

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (isOpeningBracket(c)) {
				bracketLevel++;
				term.append(c);
			} else if (isClosingBracket(c)) {
				bracketLevel--;
				term.append(c);
			} else if (bracketLevel == 0 && (c == '+' || c == '-')) {
				terms.add(term.toString());
				term = new StringBuilder();
				term.append(c);
			} else {
				term.append(c);
			}
		}

		terms.add(term.toString());
		return terms;
	}

	// Removes all whitespaces from expression
	private String removeWhitespace(String expression) {
		return expression.replaceAll("\\s+", "");
	}

	// Cleans terms by removing leading arithmetic operators
	private List<String> cleanTerms(List<String> terms) {
		for (int i = 0; i < terms.size(); i++) {
			String t = terms.get(i);
			if (t.startsWith("+") || t.startsWith("-") || t.startsWith("*") || t.startsWith("/")) {
				terms.set(i, t.substring(1));
			}
		}
		return terms;
	}

	// Checks if the  character is an opening bracket
	private boolean isOpeningBracket(char c) {
		return c == '(' || c == '[';
	}

	// Checks if the inspected character is a closing bracket
	private boolean isClosingBracket(char c) {
		return c == ')' || c == ']';
	}
}
