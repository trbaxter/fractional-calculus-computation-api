package com.trbaxter.github.fractionalcomputationapi.utils.expressionparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import com.trbaxter.github.fractionalcomputationapi.exception.BadRequestException;
import com.trbaxter.github.fractionalcomputationapi.utils.ExpressionParser;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;

class ExpressionParserLoggingTest {

  private ListAppender<ILoggingEvent> listAppender;

  @BeforeEach
  public void setUp() {
    Logger logger = (Logger) LoggerFactory.getLogger(ExpressionParser.class);
    listAppender = new ListAppender<>();
    listAppender.start();
    logger.addAppender(listAppender);
  }

  @Test
  void givenNullPolynomial_whenParsed_thenExceptionThrownAndLogged() {
    BadRequestException exception =
        assertThrows(BadRequestException.class, () -> ExpressionParser.parse(null));
    assertEquals("Polynomial expression cannot be null or empty.", exception.getMessage());

    List<ILoggingEvent> logsList = listAppender.list;
    assertEquals(1, logsList.size());
    assertEquals("Polynomial expression is null or empty.", logsList.getFirst().getMessage());
    assertEquals(Level.ERROR, logsList.getFirst().getLevel());
  }

  @Test
  void givenEmptyPolynomial_whenParsed_thenExceptionThrownAndLogged() {
    BadRequestException exception =
        assertThrows(BadRequestException.class, () -> ExpressionParser.parse(""));
    assertEquals("Polynomial expression cannot be null or empty.", exception.getMessage());

    List<ILoggingEvent> logsList = listAppender.list;
    assertEquals(1, logsList.size());
    assertEquals("Polynomial expression is null or empty.", logsList.getFirst().getMessage());
    assertEquals(Level.ERROR, logsList.getFirst().getLevel());
  }
}
