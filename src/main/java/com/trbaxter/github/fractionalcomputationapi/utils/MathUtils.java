package com.trbaxter.github.fractionalcomputationapi.utils;

public class MathUtils {

    private static final double[] COEFFICIENTS = {
            676.5203681218851,
            -1259.1392167224028,
            771.32342877765313,
            -176.61502916214059,
            12.507343278686905,
            -0.13857109526572012,
            9.9843695780195716e-6,
            1.5056327351493116e-7
    };

    public static double gamma(double z) {
        if (z < 0.5) {
            // Use reflection formula: Gamma(z) = PI / (sin(PI * z) * Gamma(1 - z))
            return Math.PI / (Math.sin(Math.PI * z) * gamma(1 - z));
        } else {
            z -= 1;
            double x = 0.99999999999980993;
            for (int i = 0; i < COEFFICIENTS.length; i++) {
                x += COEFFICIENTS[i] / (z + i + 1);
            }
            double t = z + COEFFICIENTS.length - 0.5;
            return Math.sqrt(2 * Math.PI) * Math.pow(t, z + 0.5) * Math.exp(-t) * x;
        }
    }
}