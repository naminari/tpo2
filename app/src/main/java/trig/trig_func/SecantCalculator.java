package trig.trig_func;


public class SecantCalculator {
    private final CosineCalculator cosineCalculator;
    
    public SecantCalculator() {
        this.cosineCalculator = new CosineCalculator();
    }
    
    public SecantCalculator(CosineCalculator cosineCalculator) {
        this.cosineCalculator = cosineCalculator;
    }
    
    public double calculate(double x, double epsilon) {
        double cosValue = cosineCalculator.calculate(x, epsilon);
        if (Math.abs(cosValue) < epsilon) {
            throw new ArithmeticException("Secant is undefined for cos(x) = 0");
        }
        return 1.0 / cosValue;
    }
    
    public double calculate(double x) {
        return calculate(x, CosineCalculator.DEFAULT_EPSILON);
    }
}