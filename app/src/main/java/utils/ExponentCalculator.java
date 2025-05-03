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

        double result = 1;
        double term = 1;
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