package com.trbaxter.github.fractionalcomputationapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trbaxter.github.fractionalcomputationapi.service.ComputationService;

@RestController
@RequestMapping("/calculate")
public class ComputationController {

	private final ComputationService computationService;

	@Autowired
	public ComputationController(ComputationService computationService) {
		this.computationService = computationService;
	}

	@GetMapping("/derivative")
	public ResponseEntity<String> derivative(@RequestParam String expression, @RequestParam double order) {
		return computationService.derivative(expression, order);
	}
}
