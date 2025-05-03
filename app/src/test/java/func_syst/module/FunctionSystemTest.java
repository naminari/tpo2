package func_syst.module;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;


import static org.junit.jupiter.api.Assertions.*;

import lab2.FunctionSystem;



class FunctionSystemTest {
    private FunctionSystem functionSystem;
    private static final double EPSILON = 1e-10;

    @BeforeEach
    void setUp() {
        functionSystem = new FunctionSystem();
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, -Math.PI/2, -Math.PI, -2*Math.PI})
    void shouldThrowExceptionForTrigSpecialValues(double x) {
        assertThrows(ArithmeticException.class, () -> functionSystem.calculate(x));
    }

    @Test
    void shouldCalculateForNegativeValue() {
        double x = -Math.PI / 4;
        double expected = 0.5; // Реальное значение
        assertEquals(expected, functionSystem.calculate(x), EPSILON);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1, 0.5, 10, 100})
    void shouldCalculateForPositiveValues(double x) {
        double result = functionSystem.calculate(x);
        assertFalse(Double.isNaN(result));
        assertFalse(Double.isInfinite(result));
    }


        @ParameterizedTest(name = "f({0}) ≈ {1}")
        @CsvFileSource(resources = "/module/system.csv", numLinesToSkip = 1)
        void testSystemCalculation(double x, double expected) {
            FunctionSystem system = new FunctionSystem();
            double actual = system.calculate(x);

            // Для NaN
            if (Double.isNaN(expected)) {
                assertTrue(Double.isNaN(actual),
                        () -> failMessage(x, "NaN", actual));
                return;
            }

            // Для обычных значений
            assertEquals(expected, actual, EPSILON,
                    () -> failMessage(x, expected, actual));
        }

        private String failMessage(double x, Object expected, double actual) {
            return String.format(
                    """
                    Тест провален для x = %.4f:
                      Ожидалось: %s
                      Получено:  %.4e
                    """,
                    x, expected, actual
            );
        }
    }
