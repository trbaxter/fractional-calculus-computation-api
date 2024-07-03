package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(
    validatedBy = com.trbaxter.github.fractionalcomputationapi.validation.NotNaNValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNaN {
  String message() default "Order must not be NaN";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
