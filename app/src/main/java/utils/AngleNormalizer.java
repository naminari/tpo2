package utils;


public final class AngleNormalizer {
    private AngleNormalizer() {}
    
    public static double normalize(double x) {
        double pi = Math.PI;
        x = x % (2 * pi);
        if (x > pi) x -= 2 * pi;
        else if (x < -pi) x += 2 * pi;
        return x;
    }
}