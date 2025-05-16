package trig;

import trig.trig_func.*;
import utils.*;

public class Trigonometry {
    private final SineCalculator sine = new SineCalculator();
    private final CosineCalculator cosine = new CosineCalculator();
    private final SecantCalculator secant = new SecantCalculator(cosine);
    private final CosecantCalculator cosecant = new CosecantCalculator(sine);
    private final PowerCalculator power = new PowerCalculator();

    public double sin(double x) {
        return sine.calculate(x);
    }

    public double cos(double x) {
        return cosine.calculate(x);
    }

    public double sec(double x) {
        return secant.calculate(x);
    }

    public double csc(double x) {
        return cosecant.calculate(x);
    }

    public double pow(double base, int exponent) {
        return power.calculate(base, exponent);
    }
}
