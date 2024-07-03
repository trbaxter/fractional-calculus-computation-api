package com.trbaxter.github.fractionalcomputationapi.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({ValidationOrder.First.class, ValidationOrder.Second.class, Default.class})
public interface DefaultGroupSequence {}
