package log.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import log.log_func.GeneralLogarithm;
import log.log_func.NaturalLogarithm;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GeneralLogarithmIntegrationTest {
    @Mock
    private NaturalLogarithm lnCalculator;

    @InjectMocks
    private GeneralLogarithm generalLogarithm;

    @Test
    void shouldCalculateUsingNaturalLogarithm() {
        when(lnCalculator.calculate(8.0)).thenReturn(2.079441542);
        when(lnCalculator.calculate(2.0)).thenReturn(0.693147181);

        assertEquals(3.0, generalLogarithm.calculate(8, 2), 1e-6);
        verify(lnCalculator).calculate(8.0);
        verify(lnCalculator).calculate(2.0);
    }

    @Test
    void shouldUseStubValue() {
        generalLogarithm.addStub(27.0, 3.0, 3.0); // log₃(27) = 3
        assertEquals(3.0, generalLogarithm.calculate(27, 3));
        verify(lnCalculator, never()).calculate(anyDouble());
    }

    @Test
    void shouldHandleInvalidCasesWithoutCalculations() {
        assertEquals(Double.NaN, generalLogarithm.calculate(0, 10));
        assertEquals(Double.NaN, generalLogarithm.calculate(10, 1));
        verify(lnCalculator, never()).calculate(anyDouble());
    }
}