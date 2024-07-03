package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.Result;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import jakarta.validation.Valid;
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

  /**
   * Computes the Caputo derivative for the given request.
   *
   * @param request the controller request containing the polynomial expression, order, and precision.
   * @return a ResponseEntity containing the result.
   */
  @PostMapping("derivative/caputo")
  public ResponseEntity<Result> computeCaputoDerivative(@Valid @RequestBody ControllerRequest request) {
    return processRequest(request, caputoService);
  }

  /**
   * Computes the Riemann-Liouville derivative for the given request.
   *
   * @param request the controller request containing the polynomial expression, order, and precision.
   * @return a ResponseEntity containing the result.
   */
  @PostMapping("derivative/riemann-liouville")
  public ResponseEntity<Result> computeRiemannLiouvilleDerivative(@Valid @RequestBody ControllerRequest request) {
    return processRequest(request, riemannService);
  }

  /**
   * Computes the integral for the given request.
   *
   * @param request the controller request containing the polynomial expression, order, and precision.
   * @return a ResponseEntity containing the result.
   */
  @PostMapping("integral")
  public ResponseEntity<Result> computeCaputoIntegral(@Valid @RequestBody ControllerRequest request) {
    return processRequest(request, integrationService);
  }

  /**
   * Processes the request by evaluating the expression using the appropriate service.
   *
   * @param request the controller request.
   * @param service the service to use for evaluation.
   * @param <T> the type of the service.
   * @return a ResponseEntity containing the result.
   */
  private <T> ResponseEntity<Result> processRequest(ControllerRequest request, T service) {
    String result = evaluateExpression(
        service, request.getPolynomialExpression(), request.getOrder(), request.getPrecision());
    return new ResponseEntity<>(new Result(result), HttpStatus.OK);
  }

  /**
   * Evaluates the polynomial expression using the appropriate service.
   *
   * @param service the service to use for evaluation.
   * @param polynomialExpression the polynomial expression to evaluate.
   * @param order the order of the operation.
   * @param precision the precision of the result.
   * @param <T> the type of the service.
   * @return the result of the evaluation.
   * @throws IllegalArgumentException if the service type is unknown.
   */
  private <T> String evaluateExpression(T service, String polynomialExpression, double order, Integer precision) {
      return switch (service) {
          case CaputoService caputoService1 ->
              caputoService1.evaluateExpression(polynomialExpression, order, precision);
          case RiemannService riemannService1 ->
              riemannService1.evaluateExpression(polynomialExpression, order, precision);
          case IntegrationService integrationService1 ->
              integrationService1.evaluateExpression(polynomialExpression, order, precision);
          case null, default -> throw new IllegalArgumentException("Unknown service type");
      };
  }
}
