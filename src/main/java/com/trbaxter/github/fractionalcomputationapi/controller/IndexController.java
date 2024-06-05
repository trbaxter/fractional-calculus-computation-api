package com.trbaxter.github.fractionalcomputationapi.controller;

import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trbaxter.github.fractionalcomputationapi.service.ComputationService;

@RestController
@RequestMapping("/calculate")
public class IndexController {

	private final ComputationService computationService;

	@Autowired
	public IndexController (ComputationService computationService) {
		this.computationService = computationService;
	}

	@GetMapping("/derivative")
	public ResponseEntity<String> derivative(@RequestParam @NotBlank String expression, @RequestParam double order) {
		return ResponseEntity.ok(computationService.derivative(expression, order));
	}

	@GetMapping("/integral")
	public ResponseEntity<String> integral(@RequestParam @NotBlank String expression, @RequestParam double order) {
		return ResponseEntity.ok(computationService.integral(expression, order));
	}
}
