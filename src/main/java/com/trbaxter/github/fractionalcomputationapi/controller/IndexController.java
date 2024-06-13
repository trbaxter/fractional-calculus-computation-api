package com.trbaxter.github.fractionalcomputationapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.CaputoDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.RiemannLiouvilleDerivativeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("fractional-calculus-computation-api/derivative")
@Validated
public class IndexController {

	private final CaputoDerivativeService caputoDerivativeService;
	private final RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService;

	@Autowired
	public IndexController(CaputoDerivativeService caputoDerivativeService,
			RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService) {
		this.caputoDerivativeService = caputoDerivativeService;
		this.riemannLiouvilleDerivativeService = riemannLiouvilleDerivativeService;
	}

	@PostMapping("/caputo")
	public ResponseEntity<String> computeCaputoDerivative(@Valid @RequestBody ControllerRequest request) {
		try {
			String result = caputoDerivativeService.computeDerivative(request.getCoefficients(), request.getOrder());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/riemann-liouville")
	public ResponseEntity<String> computeRiemannLiouvilleDerivative(@Valid @RequestBody ControllerRequest request) {
		try {
			String result = riemannLiouvilleDerivativeService.computeDerivative(request.getCoefficients(),
					request.getOrder());
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
