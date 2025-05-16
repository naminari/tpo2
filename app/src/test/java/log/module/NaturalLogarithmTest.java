package log.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import log.log_func.NaturalLogarithm;

class NaturalLogarithmTest {
    private NaturalLogarithm naturalLogarithm;

    @BeforeEach
    void setUp() {
        naturalLogarithm = new NaturalLogarithm();
    }

    // Проверка основных значений
    @Test
    void shouldCalculateForOne() {
        assertEquals(0.0, naturalLogarithm.calculate(1.0), 1e-5);
    }

    @Test
    void shouldCalculateForE() {
        assertEquals(1.0, naturalLogarithm.calculate(Math.E), 1e-5);
    }

    // Проверка особых случаев
    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -100, Double.NEGATIVE_INFINITY})
    void shouldReturnNaNForNonPositive(double y) {
        assertEquals(Double.NaN, naturalLogarithm.calculate(y));
    }

    // Проверка точности вычислений
    @Test
    void shouldBePreciseForSmallValues() {
        assertEquals(Math.log(1e-10), naturalLogarithm.calculate(1e-10), 1e-5);
    }

    @Test
    void shouldBePreciseForLargeValues() {
        assertEquals(Math.log(1e10), naturalLogarithm.calculate(1e10), 1e-5);
    }

    // Параметризованный тест из CSV
    @ParameterizedTest(name = "ln({0}) ≈ {1}")
    @CsvFileSource(resources = "/module/ln.csv", numLinesToSkip = 1, delimiter = ',')
    void testLogarithmCalculation(double y, double expected) {
        assertEquals(expected, naturalLogarithm.calculate(y), 1e-5);
    }
}