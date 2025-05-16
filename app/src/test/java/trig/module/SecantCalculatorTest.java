package trig.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import trig.trig_func.SecantCalculator;

class SecantCalculatorTest {
    private SecantCalculator secantCalculator;

    @BeforeEach
    void setUp() {
        secantCalculator = new SecantCalculator();
    }

    // Проверка основных значений
    @Test
    void shouldCalculateForZero() {
        assertEquals(1.0, secantCalculator.calculate(0.0), 1e-10);
    }

    @Test
    void shouldCalculateForPi() {
        assertEquals(-1.0, secantCalculator.calculate(Math.PI), 1e-5);
    }

    // Проверка особых случаев (где cos(x) = 0)
    @ParameterizedTest
    @ValueSource(doubles = {Math.PI/2, -Math.PI/2, 3*Math.PI/2})
    void shouldThrowExceptionWhenCosZero(double x) {
        assertThrows(ArithmeticException.class, () -> secantCalculator.calculate(x));
    }

    // Проверка периодичности
    @Test
    void shouldBePeriodic() {
        double x = Math.PI/3;
        assertEquals(
            secantCalculator.calculate(x),
            secantCalculator.calculate(x + 2*Math.PI),
            1e-10
        );
    }

    // Параметризованный тест из CSV
    @ParameterizedTest(name = "sec({0}) ≈ {1}")
    @CsvFileSource(resources = "/module/sec.csv", numLinesToSkip = 1, delimiter = ',')
    void testSecantCalculation(double x, double expected) {
        assertEquals(expected, secantCalculator.calculate(x), 1e-6);
    }
}