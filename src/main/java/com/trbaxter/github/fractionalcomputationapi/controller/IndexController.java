package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo.CaputoDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville.RiemannLiouvilleDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fractional-calculus-computation-api/")
@Validated
public class IndexController {

  private final CaputoDerivativeService caputoDerivativeService;
  private final CaputoIntegrationService caputoIntegrationService;
  private final RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService;

  @Autowired
  public IndexController(
      CaputoDerivativeService caputoDerivativeService,
      RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService,
      CaputoIntegrationService caputoIntegrationService) {
    this.caputoDerivativeService = caputoDerivativeService;
    this.riemannLiouvilleDerivativeService = riemannLiouvilleDerivativeService;
    this.caputoIntegrationService = caputoIntegrationService;
  }

  @PostMapping("derivative/caputo")
  public ResponseEntity<String> computeCaputoDerivative(
      @Valid @RequestBody ControllerRequest request) {
    try {
      String result =
          caputoDerivativeService.evaluateExpression(request.getCoefficients(), request.getOrder());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("derivative/riemann-liouville")
  public ResponseEntity<String> computeRiemannLiouvilleDerivative(
      @Valid @RequestBody ControllerRequest request) {
    try {
      String result =
          riemannLiouvilleDerivativeService.evaluateExpression(
              request.getCoefficients(), request.getOrder());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping("integral/caputo")
  public ResponseEntity<String> computeCaputoIntegral(
      @Valid @RequestBody ControllerRequest request) {
    try {
      String result =
          caputoIntegrationService.evaluateExpression(
              request.getCoefficients(), request.getOrder());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
