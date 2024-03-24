package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Frac2DecTest {

    @Test
    public void testSimpleFraction() {
        assertEquals("0.25", Frac2Dec.fractionToDecimal(1, 4));
    }

    @Test
    public void testRepeatingFraction() {
        assertEquals("0.(6)", Frac2Dec.fractionToDecimal(2, 3));
    }

    @Test
    public void testNoFraction() {
        assertEquals("3", Frac2Dec.fractionToDecimal(3, 1));
    }

    @Test
    public void testNegativeFraction() {
        assertEquals("-0.25", Frac2Dec.fractionToDecimal(-1, 4));
    }

    @Test
    public void testBothNegative() {
        assertEquals("0.25", Frac2Dec.fractionToDecimal(-1, -4));
    }

    @Test
    public void testZeroNumerator() {
        assertEquals("0", Frac2Dec.fractionToDecimal(0, 3));
    }

    @Test
    public void testZeroDenominator() {
        assertThrows(ArithmeticException.class, () -> Frac2Dec.fractionToDecimal(1, 0));
    }

    @Test
    public void testLargeNumbers() {
        assertEquals("-1073741824", Frac2Dec.fractionToDecimal(-2147483648, 2));
    }

    @Test
    public void testLongRepeating() {
        assertEquals("0.(142857)", Frac2Dec.fractionToDecimal(1, 7));
    }

    @Test
    public void testBothOne() {
        assertEquals("1", Frac2Dec.fractionToDecimal(1, 1));
    }
}
