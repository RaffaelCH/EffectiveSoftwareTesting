package zest;
import org.junit.jupiter.api.*;

class MaximumSubarrayTest {

    @Nested
    class SpecificationBasedTests {

        @Test
        @DisplayName("Test if the return is 0 when an empty array is given")
        void testEmptyArray() {
            int[] input = {};
            int expected = 0;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

    }

    @Nested
    class StructuralTests {
        // TODO
    }

    @Nested
    class MutationTests {
        // TODO
    }

}