package log.log_func;

import java.util.HashMap;
import java.util.Map;


public class GeneralLogarithm {
    private final NaturalLogarithm lnCalculator;
    private final Map<Double, Map<Double, Double>> stubs = new HashMap<>();
    
    public GeneralLogarithm() {
        this.lnCalculator = new NaturalLogarithm();
    }
    
    public GeneralLogarithm(NaturalLogarithm lnCalculator) {
        this.lnCalculator = lnCalculator;
    }
    
    public void addStub(double x, double base, double value) {
        stubs.computeIfAbsent(x, k -> new HashMap<>()).put(base, value);
    }
    
    public double calculate(double x, double base) {
        if (x <= 0 || base <= 0 || base == 1) return Double.NaN;
        if (stubs.containsKey(x) && stubs.get(x).containsKey(base)) {
            return stubs.get(x).get(base);
        }
        return lnCalculator.calculate(x) / lnCalculator.calculate(base);
    }
}