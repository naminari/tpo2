package trig.trig_func;
import utils.AngleNormalizer;

import java.util.HashMap;
import java.util.Map;

import java.util.HashMap;
import java.util.Map;

public class SineCalculator {
    public static final double DEFAULT_EPSILON = 1e-7;
    private final Map<Double, Double> stubs = new HashMap<>();

    public void addStub(double x, double value) {
        stubs.put(x, value);
    }

    public double calculate(double x) {
        if (Double.isNaN(x) || Double.isInfinite(x)) {
            throw new IllegalArgumentException("Invalid input: " + x);
        }

        if (stubs.containsKey(x)) {
            return stubs.get(x);
        }

        x = normalize(x);

        double term = x;
        double sum  = term;
        int n = 1;

        while (Math.abs(term) > DEFAULT_EPSILON) {
            term *= -1 * x * x / ((2 * n) * (2 * n + 1));
            sum += term;
            n++;
        }

        return sum;
    }

    private double normalize(double x) {
        return Math.IEEEremainder(x, 2 * Math.PI);
    }
}
