package zest;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class SortedArrayToBSTTest {

    @Nested
    class Task1CodeCoverage {

        /*
        - Test empty array (size 0)
        - Test array with one element
        - Test array with subsequent elements
        - Test array which is already in BST form
         */

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

}