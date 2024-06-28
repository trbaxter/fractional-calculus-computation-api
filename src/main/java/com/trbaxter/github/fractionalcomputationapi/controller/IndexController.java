package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ComputationResponse;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo.CaputoDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville.RiemannLiouvilleDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegrationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * IndexController is a REST controller that handles requests for computing fractional calculus
 * operations including the Caputo derivative, Riemann-Liouville derivative, and the Caputo
 * integral.
 */
@RestController
@RequestMapping("fractional-calculus-computation-api/")
@Validated
public class IndexController {

  private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

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
  public ResponseEntity<ComputationResponse> computeCaputoDerivative(
      @Valid @RequestBody ControllerRequest request) {
    return processRequest(request, caputoDerivativeService);
  }

  @PostMapping("derivative/riemann-liouville")
  public ResponseEntity<ComputationResponse> computeRiemannLiouvilleDerivative(
      @Valid @RequestBody ControllerRequest request) {
    return processRequest(request, riemannLiouvilleDerivativeService);
  }

  @PostMapping("integral/caputo")
  public ResponseEntity<ComputationResponse> computeCaputoIntegral(
      @Valid @RequestBody ControllerRequest request) {
    return processRequest(request, caputoIntegrationService);
  }

  private <T> ResponseEntity<ComputationResponse> processRequest(
      ControllerRequest request, T service) {
    try {
      int precision = (request.getPrecision() != null && request.getPrecision() > 0)
          ? request.getPrecision()
          : 3;
      String result = evaluateExpression(service, request.getPolynomialExpression(), request.getOrder(), precision);
      return new ResponseEntity<>(new ComputationResponse(result), HttpStatus.OK);
    } catch (Exception e) {
      logger.error("Unhandled exception: ", e);
      return new ResponseEntity<>(
          new ComputationResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private <T> String evaluateExpression(T service, String polynomialExpression, double order, int precision) {
      return switch (service) {
          case CaputoDerivativeService derivativeService ->
              derivativeService.evaluateExpression(polynomialExpression, order, precision);
          case RiemannLiouvilleDerivativeService liouvilleDerivativeService ->
              liouvilleDerivativeService.evaluateExpression(polynomialExpression, order, precision);
          case CaputoIntegrationService integrationService ->
              integrationService.evaluateExpression(polynomialExpression, order, precision);
          case null, default -> throw new IllegalArgumentException("Unknown service type");
      };
  }
}
