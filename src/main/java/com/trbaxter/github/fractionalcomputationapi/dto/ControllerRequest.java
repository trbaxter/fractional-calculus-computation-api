package com.trbaxter.github.fractionalcomputationapi.dto;

import com.trbaxter.github.fractionalcomputationapi.validation.ValidExpression;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


/**
 * Represents the POST request object sent to one of the IndexController endpoints.<br/>
 */
@Getter
@Setter
public class ControllerRequest {

    @NotBlank
    @ValidExpression
    private String expression;

    @NotNull
    @Min(0)
    private Double order;

}
