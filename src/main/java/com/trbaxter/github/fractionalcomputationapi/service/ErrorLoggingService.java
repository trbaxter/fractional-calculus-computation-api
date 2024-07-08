package com.trbaxter.github.fractionalcomputationapi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ErrorLoggingService {

  private static final Logger logger = LoggerFactory.getLogger(ErrorLoggingService.class);

  public void logWarning(String context, String message, Exception ex) {
    logger.warn("{}: {}", context, message, ex);
  }

  public void logError(String context, Exception ex) {
    logger.error("{}: ", context, ex);
  }
}
