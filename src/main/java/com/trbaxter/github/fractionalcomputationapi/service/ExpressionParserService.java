package com.trbaxter.github.fractionalcomputationapi.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExpressionParserService {

    /**
     * Method responsible for parsing the math expression provided by <br/>
     * the user by breaking it up into a collection of individual terms.
     * <p>
     * Begins by looping through every character of the expression string.<br/>
     * Characters are appended to a term until a plus or minus sign is found.<br/>
     * </p>
	 * <p>
	 * Once the terms are collected, another loop begins to check if any term<br/>
	 * starts with an arithmetic operator. If that occurs, the operator is truncated.
	 * </p>
     */

    public List<String> parseExpression(String expression) {

		expression = expression.replaceAll("\\s+", "");

		List<String> terms = new ArrayList<>();
		StringBuilder term = new StringBuilder();

		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);

			if (c == '+' || c == '-') {
				terms.add(term.toString());
				term = new StringBuilder();
			}
			else {term.append(c);}
		}

		terms.add(term.toString());

		for (int i = 0; i < terms.size(); i++) {
			String t = terms.get(i);
			if (t.startsWith("+") || t.startsWith("-") || t.startsWith("*") || t.startsWith("/")) {
				terms.set(i, t.substring(1));
			}
		}

		return terms;
	}
}
