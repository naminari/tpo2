package trig.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import trig.trig_func.CosecantCalculator;
import trig.trig_func.SineCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CosecantIntegationTest {
    @Mock
    private SineCalculator sineCalculator;

    @InjectMocks
    private CosecantCalculator cosecantCalculator;

    @Test
    void shouldCalculateUsingSineCalculator() {
        // Настраиваем mock
        when(sineCalculator.calculate(1.0)).thenReturn(0.8414709848);

        // Проверяем
        assertEquals(1.188395106, cosecantCalculator.calculate(1.0), 1e-6);
        verify(sineCalculator).calculate(1.0);
    }

    @Test
    void shouldThrowWhenSineReturnsZero() {
        when(sineCalculator.calculate(0.0)).thenReturn(0.0);

        assertThrows(ArithmeticException.class, () -> cosecantCalculator.calculate(0.0));
    }

    @Test
    void shouldUseCustomEpsilon() {
        when(sineCalculator.calculate(0.5)).thenReturn(0.4794255386);

        assertEquals(2.085829642, cosecantCalculator.calculate(0.5, 1e-5), 1e-6);
        verify(sineCalculator).calculate(0.5);
    }
}