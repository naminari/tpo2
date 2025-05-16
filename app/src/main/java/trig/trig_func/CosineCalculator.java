package trig.trig_func;

import java.util.HashMap;
import java.util.Map;

import utils.AngleNormalizer;

public class CosineCalculator {
    public static final double DEFAULT_EPSILON = 1e-7;
    private final Map<Double, Double> stubs = new HashMap<>();
    
    public void addStub(double x, double value) {
        stubs.put(x, value);
    }
    
    public double calculate(double x, double epsilon) {

        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("Invalid input: NaN or Infinite");
        }

        x = AngleNormalizer.normalize(x);
        if (stubs.containsKey(x)) {
            return stubs.get(x);
        }

        double result = 0;
        double term = 1;
        int n = 0;

        while (Math.abs(term) >= epsilon) {
            result += term;
            n++;
            term *= -x * x / ((2 * n - 1) * (2 * n));
        }

        return result;
    }
    
    public double calculate(double x) {
        return calculate(x, DEFAULT_EPSILON);
    }
}