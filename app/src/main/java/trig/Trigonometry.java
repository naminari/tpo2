package trig;

import trig.trig_func.*;
import utils.*;

public class Trigonometry {
    private static final SineCalculator sine = new SineCalculator();
    private static final CosineCalculator cosine = new CosineCalculator();
    private static final SecantCalculator secant = new SecantCalculator(cosine);
    private static final CosecantCalculator cosecant = new CosecantCalculator(sine);
    private static final PowerCalculator power = new PowerCalculator();
    
    public static double sin(double x) {
        return sine.calculate(x);
    }
    
    public static double cos(double x) {
        return cosine.calculate(x);
    }
    
    public static double sec(double x) {
        return secant.calculate(x);
    }
    
    public static double csc(double x) {
        return cosecant.calculate(x);
    }
    
    public static double pow(double base, int exponent) {
        return power.calculate(base, exponent);
    }
    
    public static void addSinStub(double x, double value) {
        sine.addStub(x, value);
    }
    
    public static void addCosStub(double x, double value) {
        cosine.addStub(x, value);
    }
    

}