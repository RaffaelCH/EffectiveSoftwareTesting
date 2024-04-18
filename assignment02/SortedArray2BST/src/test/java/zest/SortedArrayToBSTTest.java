package zest;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNull;

class SortedArrayToBSTTest {

    @Nested
    class CodeCoverageTests {

        @Test
        void testEmptyArray() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assertNull(node);
        }

        @Test
        void testArrayWithOneElement() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assert(node.val == 1);
            assertNull(node.left);
            assertNull(node.right);
        }

        @Test
        void testArrayWithSubsequentElements() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 4, 5};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assert(node.val == 3);
            assert(node.left.val == 1);
            assert(node.right.val == 4);
            assert(node.left.right.val == 2);
            assert(node.right.right.val == 5);
        }

    }

    @Nested
    class ContactTests {

        // Pre-Condition 1: input array must not be null
        @Test
        void testNullArray() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = null;
            try {
                sortedArrayToBST.sortedArrayToBST(nums);
            } catch (IllegalArgumentException e) {
                assert(e.getMessage().equals("The input array must not be null."));
            }
        }

        // Pre-Condition 2: The input array length must be in the range [0, 10^4]
        @Test
        void testArrayLengthGreaterThan10Power4() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = new int[(int) Math.pow(10, 4) + 1];
            try {
                sortedArrayToBST.sortedArrayToBST(nums);
            } catch (IllegalArgumentException e) {
                assert(e.getMessage().equals("The input array length must be in the range [0, 10^4]."));
            }
        }

        // Pre-Condition 3: The input array must be sorted in ascending order
        @Test
        void testUnsortedArray() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 5, 4};
            try {
                sortedArrayToBST.sortedArrayToBST(nums);
            } catch (IllegalArgumentException e) {
                assert(e.getMessage().equals("The input array must be sorted in ascending order."));
            }
        }

        // Pre-Condition 4: The input array must not contain any duplicates
        @Test
        void testArrayWithDuplicates() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 3, 4};
            try {
                sortedArrayToBST.sortedArrayToBST(nums);
            } catch (IllegalArgumentException e) {
                assert(e.getMessage().equals("The input array must not contain any duplicates."));
            }
        }

        // Post-Condition 1: The size of the created BST must be the same as the input array length
        @Test
        void testSizeOfBST() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 4, 5};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assert(sortedArrayToBST.calculateSize(node) == nums.length);
        }

        // Post-Condition 2: The result must be a balanced binary search tree.
        @Test
        void testBalancedBST() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 4, 5};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assertDoesNotThrow(() -> sortedArrayToBST.checkBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE));
        }

        // Invariant 1: The left subtree of a node contains only nodes with keys less than the node's key.
        @Test
        void testLeftSubtreeInvariant() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 4, 5};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assertDoesNotThrow(() -> sortedArrayToBST.checkInvariants(node));
        }

        // Invariant 2: The right subtree of a node contains only nodes with keys greater than the node's key.
        @Test
        void testRightSubtreeInvariant() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 4, 5};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assertDoesNotThrow(() -> sortedArrayToBST.checkInvariants(node));
        }

        // Invariant 3: Both the left and right subtrees must be valid binary search trees.
        @Test
        void testLeftRightSubtreeInvariant() {
            SortedArrayToBST sortedArrayToBST = new SortedArrayToBST();
            int[] nums = {1, 2, 3, 4, 5};
            TreeNode node = sortedArrayToBST.sortedArrayToBST(nums);
            assertDoesNotThrow(() -> sortedArrayToBST.checkInvariants(node));
        }

    }

}