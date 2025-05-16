package trig.trig_func;

public class CosecantCalculator {
    private final SineCalculator sineCalculator;
    
    public CosecantCalculator() {
        this.sineCalculator = new SineCalculator();
    }
    
    public CosecantCalculator(SineCalculator sineCalculator) {
        this.sineCalculator = sineCalculator;
    }
    
    public double calculate(double x, double epsilon) {
        double sinValue = sineCalculator.calculate(x);
        if (Math.abs(sinValue) < epsilon) {
            throw new ArithmeticException("Cosecant is undefined for sin(x) = 0");
        }
        return 1.0 / sinValue;
    }
    
    public double calculate(double x) {
        return calculate(x, SineCalculator.DEFAULT_EPSILON);
    }
}