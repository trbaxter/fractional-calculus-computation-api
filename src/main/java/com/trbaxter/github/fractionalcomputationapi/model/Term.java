package com.trbaxter.github.fractionalcomputationapi.model;

import java.math.BigDecimal;

public record Term (BigDecimal coefficient, BigDecimal power) {}