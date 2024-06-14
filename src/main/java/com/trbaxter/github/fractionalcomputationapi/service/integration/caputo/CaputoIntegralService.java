package com.trbaxter.github.fractionalcomputationapi.service.integration.caputo;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.service.FractionalCalculusService;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaputoIntegralService implements FractionalCalculusService {
	private final CaputoIntegralTermComputationService termComputationService;
	private final CaputoIntegralTermFormattingService termFormattingService;
	private static final Logger logger = Logger.getLogger(CaputoIntegralService.class.getName());

	@Autowired
	public CaputoIntegralService(CaputoIntegralTermComputationService termComputationService,
			CaputoIntegralTermFormattingService termFormattingService) {
		this.termComputationService = termComputationService;
		this.termFormattingService = termFormattingService;
	}

	@Override
	public String evaluateExpression(double[] coefficients, double alpha) {
		List<Term> terms = termComputationService.computeTerms(coefficients, BigDecimal.valueOf(alpha));
		return termFormattingService.formatTerms(terms, alpha);
	}
}
