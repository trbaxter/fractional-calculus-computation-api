package com.tyler.baxter.fractionalcomputation.controller;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyler.baxter.fractionalcomputation.service.ComputationService;

@RestController
@RequestMapping("/calculator")
public class ComputationController {

	private final ComputationService computationService;

	@Autowired
	public ComputationController(ComputationService computationService) {
		this.computationService = computationService;
	}

	@GetMapping("/add")
	public ResponseEntity<Double> add(@RequestParam Double x, @RequestParam Double y) throws BadRequestException {
		return computationService.add(x, y);
	}

	@GetMapping("/subtract")
	public ResponseEntity<Double> subtract(@RequestParam Double x, @RequestParam Double y) throws BadRequestException {
		return computationService.subtract(x, y);
	}

	@GetMapping("/multiply")
	public ResponseEntity<Double> multiply(@RequestParam Double x, @RequestParam Double y) throws BadRequestException {
		return computationService.multiply(x, y);
	}

	@GetMapping("/divide")
	public ResponseEntity<Double> divide(@RequestParam Double x, @RequestParam Double y) throws BadRequestException {
		return computationService.divide(x, y);
	}

	@GetMapping("/power")
	public ResponseEntity<Double> power(@RequestParam Double x, @RequestParam Double y) throws BadRequestException {
		return computationService.power(x, y);
	}

	@GetMapping("/sqrt")
	public ResponseEntity<Double> squareRoot(@RequestParam Double x) throws BadRequestException {
		return computationService.sqrt(x);
	}
}
