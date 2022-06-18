import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    Calculator calculator = Calculator.instance.get();

    @org.junit.jupiter.api.Test
    public void testPlus() {
        int a = 1, b = 2, expectedResult = 3;
        int result = calculator.plus.apply(a, b);
        Assertions.assertEquals(expectedResult, result);
    }

    @org.junit.jupiter.api.Test
    public void testDevideByZero() {
        int a = 1, b = 0;
        Assertions.assertThrows(ArithmeticException.class,
                () -> calculator.devide.apply(a, b));
    }

    @org.junit.jupiter.params.ParameterizedTest
    @MethodSource("methodSource")
    public void testIsPositive(int a, boolean expectedResult) {
        boolean result = calculator.isPositive.test(a);
        Assertions.assertEquals(expectedResult, result);
    }

    public Stream<Arguments> methodSource() {
        return Stream.of(
                Arguments.of(1, true),
                Arguments.of(-2, false),
                Arguments.of(0, false)
        );
    }
}
