package trig.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import trig.trig_func.CosineCalculator;
import trig.trig_func.SecantCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SecantIntegrationTest {
    @Mock
    private CosineCalculator cosineCalculator;

    @InjectMocks
    private SecantCalculator secantCalculator;

    @Test
    void shouldCalculateUsingCosineCalculator() {
        // Настраиваем mock
        when(cosineCalculator.calculate(1.0, 1e-10)).thenReturn(0.5403023059);

        // Проверяем
        assertEquals(1.850815718, secantCalculator.calculate(1.0), 1e-6);
        verify(cosineCalculator).calculate(1.0, 1e-10);
    }

    @Test
    void shouldThrowWhenCosineReturnsZero() {
        when(cosineCalculator.calculate(Math.PI/2, 1e-10)).thenReturn(0.0);

        assertThrows(ArithmeticException.class, () -> secantCalculator.calculate(Math.PI/2));
    }

    @Test
    void shouldUseCustomEpsilon() {
        when(cosineCalculator.calculate(0.5, 1e-5)).thenReturn(0.8775825619);

        assertEquals(1.139493927, secantCalculator.calculate(0.5, 1e-5), 1e-6);
        verify(cosineCalculator).calculate(0.5, 1e-5);
    }
}