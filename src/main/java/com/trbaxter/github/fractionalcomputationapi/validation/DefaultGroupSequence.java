package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

/**
 * Defines the default group sequence for validation.<br>
 * This sequence ensures that the validation groups {@link ValidationOrder.First} and {@link
 * ValidationOrder.Second} are validated in the specified order before the {@link Default} group.
 */
@GroupSequence({ValidationOrder.First.class, ValidationOrder.Second.class, Default.class})
public interface DefaultGroupSequence {}
