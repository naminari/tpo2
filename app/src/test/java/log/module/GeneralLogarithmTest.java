package log.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import log.log_func.GeneralLogarithm;

class GeneralLogarithmTest {
    private GeneralLogarithm generalLogarithm;

    @BeforeEach
    void setUp() {
        generalLogarithm = new GeneralLogarithm();
    }

    @Test
    void shouldCalculateCommonLogarithm() {
        assertEquals(2.0, generalLogarithm.calculate(100, 10), 1e-5);
    }

    @Test
    void shouldCalculateBinaryLogarithm() {
        assertEquals(3.0, generalLogarithm.calculate(8, 2), 1e-4);
    }

    // Проверка особых случаев
    @ParameterizedTest
    @ValueSource(doubles = {0, -1, -100})
    void shouldReturnNaNForNonPositiveX(double x) {
        assertEquals(Double.NaN, generalLogarithm.calculate(x, 10));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -1, 1, -100})
    void shouldReturnNaNForInvalidBase(double base) {
        assertEquals(Double.NaN, generalLogarithm.calculate(10, base));
    }

    @ParameterizedTest(name = "log_{1}({0}) ≈ {2}")
    @CsvFileSource(resources = "/module/log.csv", numLinesToSkip = 1, delimiter = ',')
    void testGeneralLogarithm(double x, double base, double expected) {
        assertEquals(expected, generalLogarithm.calculate(x, base), 1e-4);
    }
}