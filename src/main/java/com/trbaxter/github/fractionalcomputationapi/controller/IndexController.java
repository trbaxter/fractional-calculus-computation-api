package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import com.trbaxter.github.fractionalcomputationapi.service.ComputationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculate")
public class IndexController {

	@Autowired
	private ComputationService computationService;

	@PostMapping("/derivative/caputo")
	public ControllerResponse caputoDerivative(@RequestBody ControllerRequest request) {
		String expression = computationService.caputoFractionalDerivative(
				request.getCoefficients(), request.getOrder());
		return new ControllerResponse(expression);
	}
}
