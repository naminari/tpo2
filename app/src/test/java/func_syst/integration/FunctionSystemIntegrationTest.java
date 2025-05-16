package func_syst.integration;

import lab2.FunctionSystem;
import log.Logarithm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import trig.Trigonometry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FunctionSystemIntegrationTest {
    private static final double EPSILON = 1e-10;
    private static final double TEST_X = 2.0;

    @Mock
    private Trigonometry trigMock;

    @Mock
    private Logarithm logMock;

    @InjectMocks
    private FunctionSystem functionSystem;

    @BeforeEach
    void setUp() {
        functionSystem = new FunctionSystem(1e-10, trigMock, logMock);
    }

    @Test
    void shouldUseTrigonometryForNegativeX() {
        double x = -1.0;
        when(trigMock.sin(x)).thenReturn(-0.8414709848);
        when(trigMock.cos(x)).thenReturn(0.5403023059);
        when(trigMock.csc(x)).thenReturn(-1.188395106);
        when(trigMock.sec(x)).thenReturn(1.850815718);

        functionSystem.calculate(x);

        verify(trigMock).sin(x);
        verify(trigMock).cos(x);
        verify(trigMock).csc(x);
        verify(trigMock).sec(x);
        verifyNoInteractions(logMock);
    }

    @Test
    void shouldUseLogarithmForPositiveX() {
        when(logMock.ln(TEST_X)).thenReturn(0.6931471806);
        when(logMock.log(TEST_X, 5)).thenReturn(0.4306765581);
        when(logMock.log(TEST_X, 3)).thenReturn(0.6309297536);
        when(logMock.log(TEST_X, 10)).thenReturn(0.3010299957);

        functionSystem.calculate(TEST_X);

        verify(logMock).ln(TEST_X);
        verify(logMock).log(TEST_X, 5);
        verify(logMock).log(TEST_X, 3);
        verify(logMock).log(TEST_X, 10);

        verify(trigMock).pow(anyDouble(), eq(12));
        verifyNoMoreInteractions(trigMock);

    }

    @Test
    void shouldCalculateCorrectForPositiveXWithMocks() {
        when(logMock.ln(TEST_X)).thenReturn(0.6931471806);
        when(logMock.log(TEST_X, 5)).thenReturn(0.4306765581);
        when(logMock.log(TEST_X, 3)).thenReturn(0.6309297536);
        when(logMock.log(TEST_X, 10)).thenReturn(0.3010299957);

        double temp = 0.6931471806 * 0.4306765581;
        when(trigMock.pow(temp, 12)).thenReturn(1.844e-07);

        double expected = 1.844e-07 * (-0.1007768002); // ≈ -1.859e-08

        assertEquals(expected, functionSystem.calculate(TEST_X), EPSILON);
    }

}