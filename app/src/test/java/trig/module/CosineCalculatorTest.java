package trig.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import trig.trig_func.CosineCalculator;

public class CosineCalculatorTest {
    private CosineCalculator cosineCalculator;

    @BeforeEach
    void setUp() {
        cosineCalculator = new CosineCalculator();
    }

    // Проверка нулевого значения
    @Test
    void shouldCalculateZero() {
        assertEquals(1.0, cosineCalculator.calculate(0.0), 1e-10);
    }

    // Проверка π (минимальное значение косинуса)
    @Test
    void shouldCalculatePi() {
        assertEquals(-1.0, cosineCalculator.calculate(Math.PI), 1e-10);
    }

    // Проверка π/2 (косинус должен быть 0)
    @Test
    void shouldCalculatePiOverTwo() {
        assertEquals(0.0, cosineCalculator.calculate(Math.PI / 2), 1e-10);
    }

    // Проверка периодичности (2π)
    @Test
    void shouldBePeriodic() {
        double x = 1.5;
        assertEquals(
            cosineCalculator.calculate(x),
            cosineCalculator.calculate(x + 2 * Math.PI),
            1e-10
        );
    }

    // Проверка невалидных значений
    @ParameterizedTest
    @ValueSource(doubles = { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY })
    void shouldThrowExceptionForInvalidInput(double x) {
        assertThrows(IllegalArgumentException.class, () -> cosineCalculator.calculate(x));
    }

    // Параметризованный тест из CSV
    @ParameterizedTest(name = "cos({0}) ≈ {1}")
    @CsvFileSource(resources = "/module/cos.csv", numLinesToSkip = 1, delimiter = ',')
    void testCosineCalculation(double x, double expected) {
        assertEquals(expected, cosineCalculator.calculate(x), 1e-10);
    }
}