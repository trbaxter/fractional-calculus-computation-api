package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.caputo.CaputoDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.derivation.riemann_liouville.RiemannLiouvilleDerivativeService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.caputo.CaputoIntegrationService;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import jakarta.validation.Valid;
import java.util.List;
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
  public ResponseEntity<String> computeCaputoDerivative(
      @Valid @RequestBody ControllerRequest request) {
    try {
      List<Term> terms = ExpressionParser.parse(request.getPolynomialExpression());
      String result = caputoDerivativeService.evaluateExpression(terms, request.getOrder());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Computes the Riemann-Liouville derivative of a given polynomial expression.
   *
   * @param request request containing the polynomial coefficients and the derivative order.
   * @return result of the Riemann-Liouville derivative computation or an error message.
   */
  @PostMapping("derivative/riemann-liouville")
  public ResponseEntity<String> computeRiemannLiouvilleDerivative(
      @Valid @RequestBody ControllerRequest request) {
    try {
      List<Term> terms = ExpressionParser.parse(request.getPolynomialExpression());
      String result =
          riemannLiouvilleDerivativeService.evaluateExpression(terms, request.getOrder());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  /**
   * Computes the Caputo integral of a given polynomial expression.
   *
   * @param request request containing the polynomial coefficients and the integral order.
   * @return result of the Caputo integral computation or an error message.
   */
  @PostMapping("integral/caputo")
  public ResponseEntity<String> computeCaputoIntegral(
      @Valid @RequestBody ControllerRequest request) {
    try {
      List<Term> terms = ExpressionParser.parse(request.getPolynomialExpression());
      String result = caputoIntegrationService.evaluateExpression(terms, request.getOrder());
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
