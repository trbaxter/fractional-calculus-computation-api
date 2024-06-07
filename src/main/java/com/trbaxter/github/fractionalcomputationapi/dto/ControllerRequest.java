package com.trbaxter.github.fractionalcomputationapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


/**
 * Represents the POST request object sent to one of the IndexController endpoints.<br/>
 */
@Setter
@Getter
public class ControllerRequest {

    @NotBlank
    private String expression;

    @NotNull
    private Double order;

}
