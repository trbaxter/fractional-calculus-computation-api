package com.trbaxter.github.fractionalcomputationapi;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MainTest {

  @Test
  public void testMainMethod() {
    try (var mockedSpringApplication = Mockito.mockStatic(SpringApplication.class)) {
      String[] args = {};
      Main.main(args);
      mockedSpringApplication.verify(() -> SpringApplication.run(Main.class, args));
    }
  }
}
