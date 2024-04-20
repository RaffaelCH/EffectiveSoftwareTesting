package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClimbingStairsTest {
    private final ClimbingStairs cs = new ClimbingStairs();

    @Test
    void normalInput(){
        assertEquals(1, cs.climbStairs(1));
        assertDoesNotThrow(() -> cs.climbStairs(1));

        assertEquals(2, cs.climbStairs(2));
        assertDoesNotThrow(() -> cs.climbStairs(2));

        assertEquals(3, cs.climbStairs(3));
        assertDoesNotThrow(() -> cs.climbStairs(3));

        assertEquals(5, cs.climbStairs(4));
        assertDoesNotThrow(() -> cs.climbStairs(4));

        assertEquals(1836311903, cs.climbStairs(45));
        assertDoesNotThrow(() -> cs.climbStairs(45));

        // those outputs are all >= 0 so post condition holds
        // no assertion error is thrown so allWays stayed >= during loop execution (and so did helper values oneStepBefore, twoStepsBefore)
    }

    @Test
    void nonPositiveInput(){
        // pre-condition being violated is caught through assertion error
        assertThrows(AssertionError.class, () -> cs.climbStairs(-2));
        assertThrows(AssertionError.class, () -> cs.climbStairs(0));
        assertThrows(AssertionError.class, () -> cs.climbStairs(Integer.MIN_VALUE));
    }

    @Property
    void trivialInput(@ForAll @IntRange(min = 1, max = 2) int n) {
        assert(cs.climbStairs(n) == n);
    }

    // 90 is last number whose result does not lead to jqwik shrinking issues
    @Property
    void isPositive(@ForAll @IntRange(min = 2, max = 91) int n) {
        assert(cs.climbStairs(n) > 0);
    }
}


