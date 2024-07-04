package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code NotNaN} annotation is used to validate that a given numeric value is not NaN (Not a
 * Number).
 *
 * <p>This annotation can be applied to fields, methods, parameters, and annotation types.
 *
 * <p>Usage example:
 *
 * <pre>{@code
 * @NotNaN
 * private Double order;
 * }</pre>
 *
 * @see com.trbaxter.github.fractionalcomputationapi.validation.NotNaNValidator
 */
@Constraint(validatedBy = NotNaNValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNaN {

  /**
   * The default validation message.
   *
   * @return the error message
   */
  String message() default "Order must not be NaN";

  /**
   * The groups the constraint belongs to.
   *
   * @return the groups
   */
  Class<?>[] groups() default {};

  /**
   * Payload for clients to specify additional information about the validation error.
   *
   * @return the payload
   */
  Class<? extends Payload>[] payload() default {};
}
