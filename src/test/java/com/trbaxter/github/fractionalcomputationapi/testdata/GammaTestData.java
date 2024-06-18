package com.trbaxter.github.fractionalcomputationapi.testdata;

import com.trbaxter.github.fractionalcomputationapi.utils.MathUtils;
import java.math.BigDecimal;
import org.mockito.MockedStatic;

public final class GammaTestData {
  public static final String gamma_101 =
      "933262154439441526816992388562667004907159682643816"
          + "214685929638952175999932299156089414639761565182862"
          + "536979208272237582511852109168640000000000000000000"
          + "00000";
  public static final String gamma_11 = "3628800.000000000000000";
  public static final String gamma_6 = "120.000000000000000";
  public static final String gamma_4_point_5 = "11.631728396567448";
  public static final String gamma_4 = "6.000000000000000";
  public static final String gamma_3_point_9 = "5.299329733809704";
  public static final String gamma_3_point_8 = "4.694174205740423";
  public static final String gamma_3_point_7 = "4.170651783796603";
  public static final String gamma_3_point_6 = "3.717023853036791";
  public static final String gamma_3_point_5 = "3.323350970447842";
  public static final String gamma_3_point_4 = "2.981206426810332";
  public static final String gamma_3_point_3 = "2.683437381955768";
  public static final String gamma_3_point_2 = "2.423965479935368";
  public static final String gamma_3_point_1 = "2.197620278392477";
  public static final String gamma_3 = "2.000000000000000";
  public static final String gamma_2_point_9 = "1.827355080624036";
  public static final String gamma_2_point_8 = "1.676490787764436";
  public static final String gamma_2_point_7 = "1.544685845850593";
  public static final String gamma_2_point_6 = "1.429624558860304";
  public static final String gamma_2_point_5 = "1.329340388179137";
  public static final String gamma_2_point_4 = "1.242169344504305";
  public static final String gamma_2_point_3 = "1.166711905198160";
  public static final String gamma_2_point_2 = "1.101802490879712";
  public static final String gamma_2_point_1 = "1.046485846853560";
  public static final String gamma_2 = "1.000000000000000";
  public static final String gamma_1_point_9 = "0.961765831907387";
  public static final String gamma_1_point_8 = "0.931383770980242";
  public static final String gamma_1_point_7 = "0.908638732853290";
  public static final String gamma_1_point_6 = "0.893515349287690";
  public static final String gamma_1_point_5 = "0.886226925452758";
  public static final String gamma_1_point_4 = "0.887263817503075";
  public static final String gamma_1_point_3 = "0.897470696306277";
  public static final String gamma_1_point_2 = "0.918168742399760";
  public static final String gamma_1_point_1 = "0.951350769866873";
  public static final String gamma_1 = "1.000000000000000";
  public static final String gamma_0_point_9 = "1.068628702119319";
  public static final String gamma_0_point_8 = "1.164229713725303";
  public static final String gamma_0_point_7 = "1.298055332647557";
  public static final String gamma_0_point_6 = "1.489192248812817";
  public static final String gamma_0_point_5 = "1.772453850905516";
  public static final String gamma_0_point_4 = "2.218159543757688";
  public static final String gamma_0_point_3 = "2.991568987687590";
  public static final String gamma_0_point_2 = "4.590843711998803";
  public static final String gamma_0_point_1 = "9.513507698668731";
  public static final String neg_gamma_0_point_5 = "-3.54490770181103";

  public static void setupMathUtilsMock(MockedStatic<MathUtils> utilities) {
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(101)))
        .thenReturn(new BigDecimal(gamma_101));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(11)))
        .thenReturn(new BigDecimal(gamma_11));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(6)))
        .thenReturn(new BigDecimal(gamma_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(4.5)))
        .thenReturn(new BigDecimal(gamma_4_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(4)))
        .thenReturn(new BigDecimal(gamma_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.9)))
        .thenReturn(new BigDecimal(gamma_3_point_9));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.8)))
        .thenReturn(new BigDecimal(gamma_3_point_8));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.7)))
        .thenReturn(new BigDecimal(gamma_3_point_7));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.6)))
        .thenReturn(new BigDecimal(gamma_3_point_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.5)))
        .thenReturn(new BigDecimal(gamma_3_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.4)))
        .thenReturn(new BigDecimal(gamma_3_point_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.3)))
        .thenReturn(new BigDecimal(gamma_3_point_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.2)))
        .thenReturn(new BigDecimal(gamma_3_point_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3.1)))
        .thenReturn(new BigDecimal(gamma_3_point_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(3)))
        .thenReturn(new BigDecimal(gamma_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.9)))
        .thenReturn(new BigDecimal(gamma_2_point_9));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.8)))
        .thenReturn(new BigDecimal(gamma_2_point_8));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.7)))
        .thenReturn(new BigDecimal(gamma_2_point_7));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.6)))
        .thenReturn(new BigDecimal(gamma_2_point_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.5)))
        .thenReturn(new BigDecimal(gamma_2_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.4)))
        .thenReturn(new BigDecimal(gamma_2_point_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.3)))
        .thenReturn(new BigDecimal(gamma_2_point_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.2)))
        .thenReturn(new BigDecimal(gamma_2_point_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2.1)))
        .thenReturn(new BigDecimal(gamma_2_point_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(2)))
        .thenReturn(new BigDecimal(gamma_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.9)))
        .thenReturn(new BigDecimal(gamma_1_point_9));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.8)))
        .thenReturn(new BigDecimal(gamma_1_point_8));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.7)))
        .thenReturn(new BigDecimal(gamma_1_point_7));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.6)))
        .thenReturn(new BigDecimal(gamma_1_point_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.5)))
        .thenReturn(new BigDecimal(gamma_1_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.4)))
        .thenReturn(new BigDecimal(gamma_1_point_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.3)))
        .thenReturn(new BigDecimal(gamma_1_point_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.2)))
        .thenReturn(new BigDecimal(gamma_1_point_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1.1)))
        .thenReturn(new BigDecimal(gamma_1_point_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(1)))
        .thenReturn(new BigDecimal(gamma_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.9)))
        .thenReturn(new BigDecimal(gamma_0_point_9));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.8)))
        .thenReturn(new BigDecimal(gamma_0_point_8));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.7)))
        .thenReturn(new BigDecimal(gamma_0_point_7));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.6)))
        .thenReturn(new BigDecimal(gamma_0_point_6));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.5)))
        .thenReturn(new BigDecimal(gamma_0_point_5));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.4)))
        .thenReturn(new BigDecimal(gamma_0_point_4));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.3)))
        .thenReturn(new BigDecimal(gamma_0_point_3));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.2)))
        .thenReturn(new BigDecimal(gamma_0_point_2));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(0.1)))
        .thenReturn(new BigDecimal(gamma_0_point_1));
    utilities
        .when(() -> MathUtils.gamma(BigDecimal.valueOf(-0.5)))
        .thenReturn(new BigDecimal(neg_gamma_0_point_5));
  }
}
