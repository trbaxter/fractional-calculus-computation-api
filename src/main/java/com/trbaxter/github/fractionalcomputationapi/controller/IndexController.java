package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.Result;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
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
 * operations including the Caputo & Riemann-Liouville derivatives and integration.
 */
@RestController
@RequestMapping("fractional-calculus-computation-api/")
@Validated
public class IndexController {

  private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

  private final CaputoService caputoService;
  private final IntegrationService integrationService;
  private final RiemannService riemannService;

  @Autowired
  public IndexController(CaputoService caputoService,
                         RiemannService riemannService,
                         IntegrationService integrationService) {
    this.caputoService = caputoService;
    this.riemannService = riemannService;
    this.integrationService = integrationService;
  }

  @PostMapping("derivative/caputo")
  public ResponseEntity<Result> computeCaputoDerivative(@Valid @RequestBody ControllerRequest request) {
    return processRequest(request, caputoService);
  }

  @PostMapping("derivative/riemann-liouville")
  public ResponseEntity<Result> computeRiemannLiouvilleDerivative(@Valid @RequestBody ControllerRequest request) {
    return processRequest(request, riemannService);
  }

  @PostMapping("integral")
  public ResponseEntity<Result> computeCaputoIntegral(@Valid @RequestBody ControllerRequest request) {
    return processRequest(request, integrationService);
  }

  private <T> ResponseEntity<Result> processRequest(ControllerRequest request, T service) {
    try {
      String result = evaluateExpression(
          service, request.getPolynomialExpression(), request.getOrder(), request.getPrecision());
      return new ResponseEntity<>(new Result(result), HttpStatus.OK);
    } catch (BadRequestException e) {
      throw e;
    } catch (Exception e) {
      logger.error("Unhandled exception: ", e);
      return new ResponseEntity<>(
          new Result("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private <T> String evaluateExpression(T service, String polynomialExpression, double order, Integer precision) {
    return switch (service) {
      case CaputoService derivativeService ->
          derivativeService.evaluateExpression(polynomialExpression, order, precision);
      case RiemannService liouvilleDerivativeService ->
          liouvilleDerivativeService.evaluateExpression(polynomialExpression, order, precision);
      case IntegrationService integrationSvc ->
          integrationSvc.evaluateExpression(polynomialExpression, order, precision);
      case null, default -> throw new IllegalArgumentException("Unknown service type");
    };
  }
}
