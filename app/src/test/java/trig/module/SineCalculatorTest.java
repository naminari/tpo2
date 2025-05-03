package trig.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import trig.trig_func.SineCalculator;

public class SineCalculatorTest {
    private SineCalculator sineCalculator;

    @BeforeEach
    void setUp() {
        sineCalculator = new SineCalculator();
    }

    // Проверка нулевого значения
    @Test
    void shouldCalculateZero() {
        assertEquals(0.0, sineCalculator.calculate(0.0), 1e-10);
    }

    // Проверка π/2 (максимальное значение синуса)
    @Test
    void shouldCalculatePiOverTwo() {
        assertEquals(1.0, sineCalculator.calculate(Math.PI / 2), 1e-10);
    }

    // Проверка -π/2 (минимальное значение синуса)
    @Test
    void shouldCalculateMinusPiOverTwo() {
        assertEquals(-1.0, sineCalculator.calculate(-Math.PI / 2), 1e-10);
    }

    // Проверка, что синус периодичен (2π)
    @Test
    void shouldBePeriodic() {
        double x = 1.5;
        assertEquals(
            sineCalculator.calculate(x),
            sineCalculator.calculate(x + 2 * Math.PI),
            1e-10
        );
    }

    // Проверка невалидных значений (NaN, Infinity)
    @ParameterizedTest
    @ValueSource(doubles = { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY })
    void shouldThrowExceptionForInvalidInput(double x) {
        assertThrows(IllegalArgumentException.class, () -> sineCalculator.calculate(x));
    }

    // Параметризованный тест (данные из CSV)
    @ParameterizedTest(name = "sin({0}) ≈ {1}")
    @CsvFileSource(resources = "/module/sin.csv", numLinesToSkip = 1, delimiter = ',')
    void testSineCalculation(double x, double expected) {
        assertEquals(expected, sineCalculator.calculate(x), 1e-10);
    }
}
