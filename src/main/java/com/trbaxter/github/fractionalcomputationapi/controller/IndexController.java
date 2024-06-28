package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ComputationResponse;
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

/**
 * IndexController is a REST controller that handles requests for computing fractional calculus
 * operations including the Caputo derivative, Riemann-Liouville derivative, and the Caputo
 * integral.
 */
@RestController
@RequestMapping("fractional-calculus-computation-api/")
@Validated
public class IndexController {

  private final CaputoDerivativeService caputoDerivativeService;
  private final CaputoIntegrationService caputoIntegrationService;
  private final RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService;

  /**
   * Constructs an IndexController with the specified services.
   *
   * @param caputoDerivativeService service for computing Caputo derivatives.
   * @param riemannLiouvilleDerivativeService service for computing Riemann-Liouville derivatives.
   * @param caputoIntegrationService service for computing Caputo integrals.
   */
  @Autowired
  public IndexController(
      CaputoDerivativeService caputoDerivativeService,
      RiemannLiouvilleDerivativeService riemannLiouvilleDerivativeService,
      CaputoIntegrationService caputoIntegrationService) {
    this.caputoDerivativeService = caputoDerivativeService;
    this.riemannLiouvilleDerivativeService = riemannLiouvilleDerivativeService;
    this.caputoIntegrationService = caputoIntegrationService;
  }

  /**
   * Computes the Caputo derivative of a given polynomial expression.
   *
   * @param request request containing the polynomial coefficients and the derivative order.
   * @return result of the Caputo derivative computation or an error message.
   */
  @PostMapping("derivative/caputo")
  public ResponseEntity<ComputationResponse> computeCaputoDerivative(
      @Valid @RequestBody ControllerRequest request) {
    try {
      int precision =
          (request.getPrecision() != null && request.getPrecision() > 0)
              ? request.getPrecision()
              : 3;
      String result =
          caputoDerivativeService.evaluateExpression(
              request.getPolynomialExpression(), request.getOrder(), precision);
      return new ResponseEntity<>(new ComputationResponse(result), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ComputationResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Computes the Riemann-Liouville derivative of a given polynomial expression.
   *
   * @param request request containing the polynomial coefficients and the derivative order.
   * @return result of the Riemann-Liouville derivative computation or an error message.
   */
  @PostMapping("derivative/riemann-liouville")
  public ResponseEntity<ComputationResponse> computeRiemannLiouvilleDerivative(
      @Valid @RequestBody ControllerRequest request) {
    try {
      int precision =
          (request.getPrecision() != null && request.getPrecision() > 0)
              ? request.getPrecision()
              : 3;
      String result =
          riemannLiouvilleDerivativeService.evaluateExpression(
              request.getPolynomialExpression(), request.getOrder(), precision);
      return new ResponseEntity<>(new ComputationResponse(result), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ComputationResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Computes the Caputo integral of a given polynomial expression.
   *
   * @param request request containing the polynomial coefficients and the integral order.
   * @return result of the Caputo integral computation or an error message.
   */
  @PostMapping("integral/caputo")
  public ResponseEntity<ComputationResponse> computeCaputoIntegral(
      @Valid @RequestBody ControllerRequest request) {
    try {
      int precision =
          (request.getPrecision() != null && request.getPrecision() > 0)
              ? request.getPrecision()
              : 3;
      String result =
          caputoIntegrationService.evaluateExpression(
              request.getPolynomialExpression(), request.getOrder(), precision);
      return new ResponseEntity<>(new ComputationResponse(result), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(
          new ComputationResponse("Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
