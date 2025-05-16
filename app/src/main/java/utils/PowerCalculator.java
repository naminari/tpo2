package utils;

import java.util.HashMap;
import java.util.Map;

public class PowerCalculator {
    private final Map<Double, Map<Integer, Integer>> stubs = new HashMap<>();
    
    public void addStub(double base, int exponent, int value) {
        stubs.computeIfAbsent(base, k -> new HashMap<>()).put(exponent, value);
    }

    public double calculate(double base, int exponent) {
        if (exponent < 0) throw new IllegalArgumentException("Negative exponent not supported");
        if (stubs.containsKey(base) && stubs.get(base).containsKey(exponent)) {
            return stubs.get(base).get(exponent);
        }

        double result = 1.0;
        double current = base;

        while (exponent > 0) {
            if ((exponent & 1) == 1) {
                result *= current;
            }
            current *= current;
            exponent >>= 1;
        }

        return result;
    }

}
