package log.log_func;

import java.util.HashMap;
import java.util.Map;

import utils.*;

public class NaturalLogarithm {
    private static final double DEFAULT_EPSILON = 1e-5;
    private final Map<Double, Double> stubs = new HashMap<>();
    private final ExponentCalculator expCalculator;
    
    public NaturalLogarithm() {
        this.expCalculator = new ExponentCalculator();
    }
    
    public NaturalLogarithm(ExponentCalculator expCalculator) {
        this.expCalculator = expCalculator;
    }
    
    public void addStub(double y, double value) {
        stubs.put(y, value);
    }

    public double calculate(double y, double epsilon) {
        if (y <= 0) return Double.NaN;
        if (stubs.containsKey(y)) return stubs.get(y);

        double low = -1.0;
        double high = 1.0;

        while (expCalculator.calculate(high, epsilon) < y) {
            low = high;
            high *= 2;
            if (high > 1e6) return Double.POSITIVE_INFINITY;
        }
        while (expCalculator.calculate(low, epsilon) > y) {
            high = low;
            low *= 2;
            if (low < -1e6) return Double.NEGATIVE_INFINITY;
        }

        double mid = 0.0;
        while (high - low > epsilon) {
            mid = (low + high) / 2;
            double expMid = expCalculator.calculate(mid, epsilon);
            if (expMid < y) {
                low = mid;
            } else {
                high = mid;
            }
        }

        return (low + high) / 2;
    }


    
    public double calculate(double y) {
        return calculate(y, DEFAULT_EPSILON);
    }
}