package zest;

import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import net.jqwik.api.*;

class SumOfTwoIntegersTest {

    private final SumOfTwoIntegers classUnderTest = new SumOfTwoIntegers();

    @Test
    public void positiveAdditionTest() {
        assertEquals(3, classUnderTest.getSum(1, 2), "smaller + larger");
        assertEquals(3, classUnderTest.getSum(2, 1), "larger + smaller");
        assertEquals(4, classUnderTest.getSum(2, 2), "same number");
        assertEquals(42, classUnderTest.getSum(42, 0), "one of the arguments is 0");
        assertEquals(63, classUnderTest.getSum(42, 21), "same number of bits == 0");
        assertEquals( Integer.MAX_VALUE, classUnderTest.getSum(Integer.MAX_VALUE - 1, 1), "result is largest 32bit int");
        // assertEquals( Integer.MAX_VALUE + 1, classUnderTest.getSum(Integer.MAX_VALUE, 1), "overflow behaves like + operator");
        // assertEquals( (long) Integer.MAX_VALUE + 1, classUnderTest.getLongSum(Integer.MAX_VALUE, 1), "overflow");
    }

    @Test
    public void negativeAdditionTest() {
        assertEquals(-3, classUnderTest.getSum(-1, -2), "larger + smaller");
        assertEquals(-3, classUnderTest.getSum(-2, -1), "smaller + larger");
        assertEquals(-2, classUnderTest.getSum(-1, -1), "same number");
        assertEquals(-42, classUnderTest.getSum(-42, 0), "one of the arguments is 0");
        assertEquals(-63, classUnderTest.getSum(-52, -11), "same number of bits == 0");
        assertEquals( Integer.MIN_VALUE, classUnderTest.getSum(Integer.MIN_VALUE + 1, -1), "result is smallest 32bit int");
        // assertEquals( Integer.MIN_VALUE - 1, classUnderTest.getSum(Integer.MIN_VALUE, -1), "underflow behaves like + operator");
    }

    @Test
    public void negativeAndPositiveAdditionTest() {
        assertEquals(0, classUnderTest.getSum(0, 0), "both numbers 0");
        assertEquals(-1, classUnderTest.getSum(-2, 1), "negative result");
        assertEquals(1, classUnderTest.getSum(2, -1), "positive result");
        assertEquals(0, classUnderTest.getSum(-3, 3), "result == 0");
        assertEquals( -1, classUnderTest.getSum(Integer.MIN_VALUE, Integer.MAX_VALUE), "min + max int");
    }

    @Property
    public void uniquePathsSuccess(
            @ForAll
            @IntRange(min = Integer.MIN_VALUE)
            int firstValue,
            @ForAll
            @IntRange(min = Integer.MIN_VALUE)
            int secondValue) {
        var result = classUnderTest.getSum(firstValue, secondValue);
        assertEquals(firstValue + secondValue, result, "Behaves like + operator");

        if (firstValue <= 0) {
            if (secondValue >= 0) {
                assertTrue(firstValue <= result);
                assertTrue(result <= secondValue);
            }
            else {
                assertTrue(result <= firstValue);
                assertTrue(result <= secondValue);
            }
        }
        else {
            if (secondValue <= 0) {
                assertTrue(firstValue >= result);
                assertTrue(result >= secondValue);
            }
            else {
                assertTrue(result > firstValue);
                assertTrue(result > secondValue);
            }
        }
    }
}
