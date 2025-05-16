package log;


import log.log_func.*;
import utils.ExponentCalculator;
public class Logarithm {
    private final ExponentCalculator exp = new ExponentCalculator();
    private final NaturalLogarithm ln = new NaturalLogarithm(exp);
    private final GeneralLogarithm log = new GeneralLogarithm(ln);

    public double ln(double y) {
        return ln.calculate(y);
    }

    public double exp(double x) {
        return exp.calculate(x);
    }

    public double log(double x, double base) {
        return log.calculate(x, base);
    }
}
