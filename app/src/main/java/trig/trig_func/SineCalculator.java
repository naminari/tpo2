package trig.trig_func;

import java.util.HashMap;
import java.util.Map;

import utils.AngleNormalizer;

public class SineCalculator {
    public static final double DEFAULT_EPSILON = 1e-10;
    private final Map<Double, Double> stubs = new HashMap<>();
    
    public void addStub(double x, double value) {
        stubs.put(x, value);
    }
    
    public double calculate(double x, double epsilon) {
        x = AngleNormalizer.normalize(x);
        if (stubs.containsKey(x)) {
            return stubs.get(x);
        }

        double term = x;
        double sum = term;
        int n = 1;

        while (Math.abs(term) >= epsilon) {
            term = -term * x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }

        return sum;
    }
    
    public double calculate(double x) {
        return calculate(x, DEFAULT_EPSILON);
    }
}