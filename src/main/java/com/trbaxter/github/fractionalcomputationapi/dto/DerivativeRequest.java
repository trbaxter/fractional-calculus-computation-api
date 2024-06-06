package com.trbaxter.github.fractionalcomputationapi.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DerivativeRequest {

    @NotBlank
    private String expression;

    @NotBlank
    private double order;

}
