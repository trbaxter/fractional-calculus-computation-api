package com.trbaxter.github.fractionalcomputationapi.service.differentiation.caputo;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.trbaxter.github.fractionalcomputationapi.model.Term;
import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CaputoComputationServiceTest {

  @InjectMocks private CaputoComputationService service;

  @Test
  public void testComputeFractionalOrderDerivativeTerms() {
    List<Term> terms = List.of(new Term(BigDecimal.valueOf(1), BigDecimal.valueOf(2)));
    BigDecimal alpha = BigDecimal.valueOf(0.5);

    try (MockedStatic<MathUtils> mathUtilsMockedStatic = mockStatic(MathUtils.class)) {
      mathUtilsMockedStatic
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
          .thenReturn(BigDecimal.valueOf(2));
      mathUtilsMockedStatic
          .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
          .thenReturn(BigDecimal.valueOf(1.5));

      List<Term> computedTerms = service.computeTerms(terms, alpha);

      mathUtilsMockedStatic.verify(() -> MathUtils.gamma(BigDecimal.valueOf(3)), times(1));
      mathUtilsMockedStatic.verify(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)), times(1));

      assertFalse(computedTerms.isEmpty());
      assertEquals(1, computedTerms.size());
      assertEquals(
          new BigDecimal("1.333333333333333333333333333333333"),
          computedTerms.getFirst().coefficient());
      assertEquals(BigDecimal.valueOf(1.5), computedTerms.getFirst().power());
    }
  }
}
