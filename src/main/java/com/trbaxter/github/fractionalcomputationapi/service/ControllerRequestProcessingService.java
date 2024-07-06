package com.trbaxter.github.fractionalcomputationapi.service;

import com.trbaxter.github.fractionalcomputationapi.model.ControllerRequest;
import com.trbaxter.github.fractionalcomputationapi.model.Result;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo.CaputoService;
import com.trbaxter.github.fractionalcomputationapi.service.differentiation.riemann_liouville.RiemannService;
import com.trbaxter.github.fractionalcomputationapi.service.integration.IntegrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ControllerRequestProcessingService {

    public String evaluateExpression(Object service, String polynomialExpression, double order, Integer precision) {
        return switch (service) {
            case CaputoService caputoService ->
                caputoService.evaluateExpression(polynomialExpression, order, precision);
            case RiemannService riemannService ->
                riemannService.evaluateExpression(polynomialExpression, order, precision);
            case IntegrationService integrationService ->
                integrationService.evaluateExpression(polynomialExpression, order, precision);
            case null, default -> throw new IllegalArgumentException("Unknown service type");
        };
    }

    public <T> ResponseEntity<Result> processRequest(ControllerRequest request, T service) {
        String result = evaluateExpression(service, request.getPolynomialExpression(), request.getOrder(), request.getPrecision());
        return new ResponseEntity<>(new Result(result), HttpStatus.OK);
    }
}
