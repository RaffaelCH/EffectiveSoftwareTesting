package zest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SumOfTwoIntegersTest {

    private SumOfTwoIntegers classUnderTest = new SumOfTwoIntegers();

    @Test
    public void positiveAdditionTest() {
        assertEquals(3, classUnderTest.getSum(1, 2), "smaller + larger");
        assertEquals(3, classUnderTest.getSum(2, 1), "larger + smaller");
        assertEquals(63, classUnderTest.getSum(42, 21), "same number of bits == 0");
        assertEquals( Integer.MAX_VALUE, classUnderTest.getSum(Integer.MAX_VALUE - 1, 1), "result is largest 32bit int");
    }

    @Test
    public void negativeAdditionTest() {
        assertEquals(-3, classUnderTest.getSum(-1, -2), "larger + smaller");
        assertEquals(-3, classUnderTest.getSum(-2, -1), "smaller + larger");
        assertEquals(-63, classUnderTest.getSum(-52, -11), "same number of bits == 0");
        assertEquals( Integer.MIN_VALUE, classUnderTest.getSum(Integer.MIN_VALUE + 1, -1), "result is smallest 32bit int");
    }

    @Test
    public void negativeAndPositiveAdditionTest() {
        assertEquals(-1, classUnderTest.getSum(-2, 1), "negative result");
        assertEquals(1, classUnderTest.getSum(2, -1), "positive result");
        assertEquals(0, classUnderTest.getSum(-3, 3), "result == 0");
        assertEquals( -1, classUnderTest.getSum(Integer.MIN_VALUE, Integer.MAX_VALUE), "min + max int");
    }
}
