package com.trbaxter.github.fractionalcomputationapi.controller;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.Result;
import com.trbaxter.github.fractionalcomputationapi.service.ControllerRequestProcessingService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
  private final ControllerRequestProcessingService processingService;

  @Autowired
  public IndexController(
      CaputoService caputoService,
      RiemannService riemannService,
      IntegrationService integrationService,
      ControllerRequestProcessingService processingService) {
    this.caputoService = caputoService;
    this.riemannService = riemannService;
    this.integrationService = integrationService;
    this.processingService = processingService;
  }

  /**
   * Computes the Caputo derivative for the given request.
   *
   * @param request the controller request containing the polynomial expression, order, and
   *     precision.
   * @return a ResponseEntity containing the result.
   */
  @PostMapping("derivative/caputo")
  public ResponseEntity<Result> computeCaputoDerivative(
      @Valid @RequestBody ControllerRequest request) {
    return processingService.processRequest(request, caputoService);
  }

  /**
   * Computes the Riemann-Liouville derivative for the given request.
   *
   * @param request the controller request containing the polynomial expression, order, and
   *     precision.
   * @return a ResponseEntity containing the result.
   */
  @PostMapping("derivative/riemann-liouville")
  public ResponseEntity<Result> computeRiemannLiouvilleDerivative(
      @Valid @RequestBody ControllerRequest request) {
    return processingService.processRequest(request, riemannService);
  }

  /**
   * Computes the integral for the given request.
   *
   * @param request the controller request containing the polynomial expression, order, and
   *     precision.
   * @return a ResponseEntity containing the result.
   */
  @PostMapping("integral")
  public ResponseEntity<Result> computeCaputoIntegral(
      @Valid @RequestBody ControllerRequest request) {
    return processingService.processRequest(request, integrationService);
  }
}
