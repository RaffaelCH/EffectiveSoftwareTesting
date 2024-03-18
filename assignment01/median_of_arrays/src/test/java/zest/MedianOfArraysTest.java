package zest;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class MedianOfArraysTest {

    @Nested
    class SpecificationBasedTests {

        @Test
        @DisplayName("Test empty arrays")
        void testEmptyArrays() {
            int[] nums1 = {};
            int[] nums2 = {};
            double expected = -1;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test null arrays")
        void testNullArrays() {
            int[] nums1 = null;
            int[] nums2 = null;
            double expected = 0;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test one null array")
        void testOneNullArray() {
            int[] nums1 = {1, 2, 3, 4, 5};
            int[] nums2 = null;
            double expected = 0;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test where one array is empty")
        void testOneArrayEmpty() {
            int[] nums1 = {};
            int[] nums2 = {1, 2, 3, 4, 5};
            double expected = 3;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test unsorted arrays")
        void testUnsortedArrays() {
            int[] nums1 = {1, 2, 3, 4, 5};
            int[] nums2 = {5, 4, 3, 2, 1};
            double expected = 0;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test if negative sorting is done correctly")
        void testNegativeSorting() {
            int[] nums1 = {1, 2, 3, 4, 5};
            int[] nums2 = {-1, -2, -3, -4, -5};
            double expected = 0;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test arrays with one element")
        void testArraysOneElement() {
            int[] nums1 = {1};
            int[] nums2 = {2};
            double expected = 1.5;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test arrays with two elements")
        void testArraysTwoElements() {
            int[] nums1 = {1, 2};
            int[] nums2 = {3, 4};
            double expected = 2.5;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test arrays with negative numbers")
        void testArraysNegativeNumbers() {
            int[] nums1 = {-5, -4, -3, -2, -1};
            int[] nums2 = {-10, -9, -8, -7, -6};
            double expected = -5.5;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test where one array contains negative numbers and the other contains positive numbers")
        void testArraysNegativeAndPositiveNumbers() {
            int[] nums1 = {-8, -7, -6, -5, -4};
            int[] nums2 = {6, 7, 8, 9, 10};
            double expected = 1.0;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

        @Test
        @DisplayName("Test MAX_VALUE and MIN_VALUE")
        void testMaxInt() {
            int[] nums1 = {Integer.MAX_VALUE};
            int[] nums2 = {Integer.MIN_VALUE};
            double expected = -0.5;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }

    }

    @Nested
    class StructuralTests {
        @Test
        @DisplayName("Test unsorted where input 1 is unsorted")
        void testUnsortedArraysInput1() {
            int[] nums1 = {5, 4, 3, 2, 1};
            int[] nums2 = {1, 2, 3, 4, 5};
            double expected = 0;
            double actual = new MedianOfArrays().findMedianSortedArrays(nums1, nums2);
            Assertions.assertEquals(expected, actual);
        }
    }

}