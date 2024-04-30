package com.tyler.baxter.fractionalcomputation.service;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ComputationService {

	public ResponseEntity<Double> add(Double x, Double y) throws BadRequestException {

		if (x == null || y == null) {
			return ResponseEntity.badRequest().body(null);
		}

		try {
			Double result = x + y;
			return ResponseEntity.ok(result);
		} catch (NumberFormatException e) {
			throw new BadRequestException(); // Handle any non-numerical inputs
		}
	}

	public ResponseEntity<Double> subtract(Double x, Double y) throws BadRequestException {

		if (x == null || y == null) {
			return ResponseEntity.badRequest().body(null);
		}

		try {
			Double result = x - y;
			return ResponseEntity.ok(result);
		} catch (NumberFormatException e) {
			throw new BadRequestException(); // Handle any non-numerical inputs
		}
	}

	public ResponseEntity<Double> multiply(Double x, Double y) throws BadRequestException {

		if (x == null || y == null) {
			return ResponseEntity.badRequest().body(null);
		}

		try {
			Double result = x * y;
			return ResponseEntity.ok(result);
		} catch (NumberFormatException e) {
			throw new BadRequestException(); // Handle any non-numerical inputs
		}
	}

	public ResponseEntity<Double> divide(Double x, Double y) throws BadRequestException {

		if (x == null || y == null) {
			return ResponseEntity.badRequest().body(null);
		} else if (y == 0) {
			throw new IllegalArgumentException();
		}

		try {
			Double result = x / y;
			return ResponseEntity.ok(result);
		} catch (NumberFormatException e) {
			throw new BadRequestException(); // Handle any non-numerical inputs
		}
	}

	public ResponseEntity<Double> power(Double x, Double y) throws BadRequestException {

		if (x == null || y == null) {
			return ResponseEntity.badRequest().body(null);
		}

		try {
			Double result = Math.pow(x, y);
			return ResponseEntity.ok(result);
		} catch (NumberFormatException e) {
			throw new BadRequestException(); // Handle any non-numerical inputs
		}
	}

	public ResponseEntity<Double> sqrt(Double x) throws BadRequestException {
		if (x < 0) {
			throw new IllegalArgumentException("Square root of negative number is undefined.");
		}

		try {
			Double result = Math.sqrt(x);
			return ResponseEntity.ok(result);
		} catch (NumberFormatException e) {
			throw new BadRequestException(); // Handle any non-numerical inputs
		}
	}
}
