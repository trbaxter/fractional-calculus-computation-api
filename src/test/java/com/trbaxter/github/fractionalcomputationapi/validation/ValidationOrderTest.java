package com.trbaxter.github.fractionalcomputationapi.validation;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.lang.reflect.Constructor;
import org.junit.jupiter.api.Test;

class ValidationOrderTest {

  /** Tests that the constructor of the ValidationOrder class is private and not accessible. */
  @Test
  void testPrivateConstructor() throws NoSuchMethodException {
    Constructor<ValidationOrder> constructor = ValidationOrder.class.getDeclaredConstructor();
    assertFalse(constructor.canAccess(null), "Constructor should be private and not accessible.");
  }

  /**
   * Tests that an attempt to access the private constructor throws an InstantiationException or
   * IllegalAccessException.
   */
  @Test
  void testPrivateConstructorThrowsException() throws NoSuchMethodException {
    Constructor<ValidationOrder> constructor = ValidationOrder.class.getDeclaredConstructor();
    assertFalse(constructor.canAccess(null), "Constructor should be private and not accessible.");
    assertThrows(
        IllegalAccessException.class,
        constructor::newInstance,
        "Constructor should throw IllegalAccessException when accessed.");
  }
}
