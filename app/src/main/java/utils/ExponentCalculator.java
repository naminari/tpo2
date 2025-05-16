package utils;


import java.util.HashMap;
import java.util.Map;

public class ExponentCalculator {
    private static final double DEFAULT_EPSILON = 1e-10;
    private final Map<Double, Double> stubs = new HashMap<>();

    public void addStub(double x, double value) {
        stubs.put(x, value);
    }

    public double calculate(double x, double epsilon) {
        if (stubs.containsKey(x)) {
            return stubs.get(x);
        }

        if (x < 0) {
            return 1.0 / calculate(-x, epsilon);
        }

        if (x > 1.0) {
            double half = calculate(x / 2.0, epsilon);
            return half * half;
        }

        // теперь x ∈ [0, 1], можно безопасно использовать ряд
        double result = 1.0;
        double term = 1.0;
        int n = 1;

        while (Math.abs(term) >= epsilon) {
            term *= x / n;
            result += term;
            n++;
        }

        return result;
    }

    public double calculate(double x) {
        return calculate(x, DEFAULT_EPSILON);
    }
}
