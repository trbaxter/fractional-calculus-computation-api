package com.tyler.baxter.fractionalcomputation.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tyler.baxter.fractionalcomputation.service.ComputationService;

@RestController
@RequestMapping("/calculate")
public class ComputationController {

  private final ComputationService computationService;

  @Autowired
  public ComputationController(ComputationService computationService) {
    this.computationService = computationService;
  }

  @GetMapping("/derivative")
  public ResponseEntity<String> derivative(
      @RequestParam String expression, @RequestParam double order)
      throws UnsupportedEncodingException {
    return computationService.derivative(expression, order);
  }
}
