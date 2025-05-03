package log;


import log.log_func.*;
import utils.ExponentCalculator;

public class Logarithm {
    private static final ExponentCalculator exp = new ExponentCalculator();
    private static final NaturalLogarithm ln = new NaturalLogarithm(exp);
    private static final GeneralLogarithm log = new GeneralLogarithm(ln);
    
    public static double ln(double y) {
        return ln.calculate(y);
    }
    
    public static double exp(double x) {
        return exp.calculate(x);
    }
    
    public static double log(double x, double base) {
        return log.calculate(x, base);
    }
    
    // Методы для стабов
    public static void addLnStub(double y, double value) {
        ln.addStub(y, value);
    }
    
    public static void addExpStub(double x, double value) {
        exp.addStub(x, value);
    }
    
    public static void addLogStub(double x, double base, double value) {
        log.addStub(x, base, value);
    }
}