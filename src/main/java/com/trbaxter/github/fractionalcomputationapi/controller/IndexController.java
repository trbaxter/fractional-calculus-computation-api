package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerResponse;
import com.trbaxter.github.fractionalcomputationapi.service.RiemannLiouvilleDerivativeService;
import org.springframework.beans.factory.annotation.Autowired;
import com.trbaxter.github.fractionalcomputationapi.service.CaputoDerivativeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fractional-calculus-computation-api/derivative")
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
	public String computeCaputoDerivative(@RequestBody ControllerRequest request) {
		return caputoDerivativeService.computeDerivative(request.getCoefficients(), request.getOrder());
	}

	@PostMapping("/riemann-liouville")
	public String computeRiemannLiouvilleDerivative(@RequestBody ControllerRequest request) {
		return riemannLiouvilleDerivativeService.computeDerivative(request.getCoefficients(), request.getOrder());
	}
}
