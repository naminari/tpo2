package log.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import utils.ExponentCalculator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import log.log_func.NaturalLogarithm;

@ExtendWith(MockitoExtension.class)
class NaturalLogarithmIntegrationTest {
    @Mock
    private ExponentCalculator expCalculator;

    @InjectMocks
    private NaturalLogarithm naturalLogarithm;

    @Test
    void shouldCalculateUsingExponentCalculator() {
        // Настраиваем mock для итераций бинарного поиска
        when(expCalculator.calculate(anyDouble(), anyDouble()))
            .thenReturn(0.5)   // первая итерация (t=0)
            .thenReturn(1.5)   // вторая итерация (t=0)
            .thenReturn(1.0);   // третья итерация (t=0)

        assertEquals(0.0, naturalLogarithm.calculate(1.0), 1e-8);
        verify(expCalculator, atLeast(3)).calculate(anyDouble(), anyDouble());
    }

    @Test
    void shouldUseStubValue() {
        naturalLogarithm.addStub(2.5, 0.9162907319);
        assertEquals(0.9162907319, naturalLogarithm.calculate(2.5));
        verify(expCalculator, never()).calculate(anyDouble(), anyDouble());
    }

    @Test
    void shouldHandleEdgeCasesWithoutCallingExp() {
        assertEquals(Double.NaN, naturalLogarithm.calculate(0.0));
        assertEquals(Double.NaN, naturalLogarithm.calculate(-1.0));
        verify(expCalculator, never()).calculate(anyDouble(), anyDouble());
    }
}