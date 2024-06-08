package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.Payload;

public @interface ValidExpression {
    String message() default "Invalid mathematical expression.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default{};
}
