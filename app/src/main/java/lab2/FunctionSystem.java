package lab2;


import log.Logarithm;
import trig.Trigonometry;


public class FunctionSystem {
    private final double epsilon;
    private final Trigonometry trigonometry;
    private final Logarithm logarithm;

    // Конструктор с возможностью инъекции зависимостей
    public FunctionSystem() {
        this(1e-10, new Trigonometry(), new Logarithm());
    }

    public FunctionSystem(double epsilon, Trigonometry trigonometry, Logarithm logarithm) {
        this.epsilon = epsilon;
        this.trigonometry = trigonometry;
        this.logarithm = logarithm;
    }

    public double calculate(double x) {
        if (x <= 0) {
            return calculateTrigonometricPart(x);
        } else {
            return calculateLogarithmicPart(x);
        }
    }

    private double calculateTrigonometricPart(double x) {
        double sinX = trigonometry.sin(x);
        double cosX = trigonometry.cos(x);
        double cscX = trigonometry.csc(x);
        double secX = trigonometry.sec(x);

        return ((((sinX + sinX) / cosX) + cscX) - (cscX * secX)) / (cscX - secX);
    }

    private double calculateLogarithmicPart(double x) {
        double lnX = Logarithm.ln(x);
        double log5X = logarithm.log(x, 5);
        double log3X = logarithm.log(x, 3);
        double log10X = logarithm.log(x, 10);

        double temp = lnX * log5X;
        double powPart = Trigonometry.pow(temp, 12); // Оставил статическим, можно вынести в зависимость
        return powPart * (log3X - log5X - log10X);
    }
}