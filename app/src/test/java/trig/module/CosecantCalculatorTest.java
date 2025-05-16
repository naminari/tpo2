package trig.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import trig.trig_func.CosecantCalculator;

class CosecantCalculatorTest {
    private CosecantCalculator cosecantCalculator;

    @BeforeEach
    void setUp() {
        cosecantCalculator = new CosecantCalculator();
    }

    @Test
    void shouldCalculateForPiOverTwo() {
        assertEquals(1.0, cosecantCalculator.calculate(Math.PI/2), 1e-5);
    }

    @Test
    void shouldCalculateForMinusPiOverTwo() {
        assertEquals(-1.0, cosecantCalculator.calculate(-Math.PI/2), 1e-5);
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, Math.PI, -Math.PI, 2*Math.PI})
    void shouldThrowExceptionWhenSinZero(double x) {
        assertThrows(ArithmeticException.class, () -> cosecantCalculator.calculate(x));
    }

    @Test
    void shouldBePeriodic() {
        double x = Math.PI/4;
        assertEquals(
            cosecantCalculator.calculate(x),
            cosecantCalculator.calculate(x + 2*Math.PI),
            1e-10
        );
    }

    @ParameterizedTest(name = "csc({0}) ≈ {1}")
    @CsvFileSource(resources = "/module/csc.csv", numLinesToSkip = 1, delimiter = ',')
    void testCosecantCalculation(double x, double expected) {
        assertEquals(expected, cosecantCalculator.calculate(x), 1e-6);
    }
}