package com.trbaxter.github.fractionalcomputationapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * MainTest is a test class for the Main class. It tests the main method to ensure that the Spring
 * application starts correctly.
 */
@SpringBootTest
public class MainTest {

  /**
   * Tests the main method of the Main class to verify that SpringApplication.run is called
   * correctly.
   */
  @Test
  public void testMainMethod() {
    try (var mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
      String[] args = {};
      Main.main(args);
      mockedSpringApplication.verify(() -> SpringApplication.run(Main.class, args));
    }
  }
}
