package zest;
import org.junit.jupiter.api.*;

class MaximumSubarrayTest {

    @Nested
    class SpecificationBasedTests {

        @Test
        @DisplayName("Test the result of the example given in the assignment")
        void testNullInput() {
            int[] input = null;
            int expected = 0;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test if the return is 0 when an empty array is given")
        void testEmptyArray() {
            int[] input = {};
            int expected = 0;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the result of an array with only 1 element")
        void testArrayLength1() {
            int[] input = {5};
            int expected = 5;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the result of an array with 2 elements")
        void testArrayLength2() {
            int[] input = {5, -3};
            int expected = 5;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the array with all positive numbers")
        void testAllPositive() {
            int[] input = {1, 2, 3, 4, 5};
            int expected = 15;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the array with all negative numbers")
        void testAllNegative() {
            int[] input = {-3, -1, -2, -3, -4, -5};
            int expected = -1;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the array with all 0s")
        void testAllZeros() {
            int[] input = {0, 0, 0, 0, 0};
            int expected = 0;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the array with the largest subarray at the beginning")
        void testLargestAtBeginning() {
            int[] input = {5, 4, 1, -15, 1, 1, 4};
            int expected = 10;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the array with the largest subarray in the middle")
        void testLargestInMiddle() {
            int[] input = {6, 1, -8, 4, 1, 3, -9, 1, 3};
            int expected = 8;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the array with the largest subarray at the end")
        void testLargestAtEnd() {
            int[] input = {1, 1, 4, -10, 4, 5};
            int expected = 9;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test the array which contains the same largest sequence twice") // really needed?
        void testLargestTwice() {
            int[] input = {1, 3, -9, 1, 3};
            int expected = 4;
            int actual = MaximumSubarray.maxSubArray(input);
            Assertions.assertEquals(expected, actual);
        }

    }

    @Nested
    class StructuralTests {


    }

    @Nested
    class MutationTests {
        // TODO
    }

}