package com.trbaxter.github.fractionalcomputationapi.service;

import org.springframework.stereotype.Service;

@Service
public class GammaFunctionServiceImpl implements GammaFunctionService{

    @Override
    public long calculateGammaFunction(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
