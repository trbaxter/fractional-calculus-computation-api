package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.dto.ControllerRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import com.trbaxter.github.fractionalcomputationapi.service.ComputationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class IndexController {

	private final ComputationService computationService;

	@Autowired
	public IndexController (ComputationService computationService) {
		this.computationService = computationService;
	}

	@PostMapping("/derivative")
	public ResponseEntity<String> derivative(@RequestBody @Valid ControllerRequest request) {
		return ResponseEntity.ok(computationService.derivative(request.getExpression(), request.getOrder()));
	}

	@PostMapping("/integral")
	public ResponseEntity<String> integral(@RequestParam @Valid ControllerRequest request) {
    return ResponseEntity.ok(computationService.integral(request.getExpression(), request.getOrder()));
	}
}
