/* (C)2024 */
package com.tyler.baxter.fractionalcomputation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/calculator")
public class Controller {

	@GetMapping("/add")
	public double add(@RequestParam double a, @RequestParam double b) {
		return a + b;
	}

	@GetMapping("/subtract")
	public double subtract(@RequestParam double a, @RequestParam double b) {
		return a - b;
	}

	@GetMapping("/multiply")
	public double multiply(@RequestParam double a, @RequestParam double b) {
		return a * b;
	}

	@GetMapping("/divide")
	public double divide(@RequestParam double a, @RequestParam double b) {
		if (b == 0) {
			throw new IllegalArgumentException();
		}
		return a / b;
	}

	@GetMapping("/power")
	public double power(@RequestParam double base, @RequestParam double exponent) {
		return Math.pow(base, exponent);
	}

	@GetMapping("/sqrt")
	public double squareRoot(@RequestParam double number) {
		if (number < 0) {
			throw new IllegalArgumentException("Square root of negative number is undefined.");
		}
		return Math.sqrt(number);
	}
}
