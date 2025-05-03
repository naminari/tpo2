package log.log_func;

import java.util.HashMap;
import java.util.Map;

import utils.*;

public class NaturalLogarithm {
    private static final double DEFAULT_EPSILON = 1e-10;
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

        double low = -100;
        double high = 100;
        double t = 0;

        while (high - low > epsilon) {
            t = (low + high) / 2;
            double et = expCalculator.calculate(t, epsilon);
            if (et < y) {
                low = t;
            } else {
                high = t;
            }
        }
        return t;
    }
    
    public double calculate(double y) {
        return calculate(y, DEFAULT_EPSILON);
    }
}