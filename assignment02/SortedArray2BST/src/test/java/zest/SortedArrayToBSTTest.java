package zest;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

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

        // Check if the correct exception is thrown when the pre-conditions are violated
        // Pre-Condition 1: input array must not be null
        // Pre-Condition 2: The input array length must be in the range [0, 10^4]
        // Pre-Condition 3: The input array must be sorted in ascending order
        // Pre-Condition 4: The input array must not contain any duplicates

        // Check if the post-conditions hold
        // Post-Condition 1: The size of the created BST must be the same as the input array length
        // Post-Condition 2: The result must be a balanced binary search tree.

        // Check if the invariants hold
        // Invariant 1: The left subtree of a node contains only nodes with keys less than the node's key.
        // Invariant 2: The right subtree of a node contains only nodes with keys greater than the node's key.
        // Invariant 3: Both the left and right subtrees must be valid binary search trees.

    }

}