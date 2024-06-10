package com.trbaxter.github.fractionalcomputationapi.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ControllerRequest {
    private double[] coefficients;
    private double order;
}
