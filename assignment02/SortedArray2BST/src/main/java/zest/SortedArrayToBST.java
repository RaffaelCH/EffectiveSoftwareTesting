package zest;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class SortedArrayToBST {

    private void checkPreconditions(int[] nums) {
        // Check if the input array is null
        if (nums == null)
            throw new IllegalArgumentException("The input array must not be null.");

        // Check if the input array is in the range [0, 10^4]
        if (nums.length > Math.pow(10, 4))
            throw new IllegalArgumentException("The input array length must be in the range [0, 10^4].");

        // Check if the array nums is sorted ascending
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                throw new IllegalArgumentException("The input array must be sorted in ascending order.");
        }

        // Check if the array does not contain any duplicates
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1])
                throw new IllegalArgumentException("The input array must not contain any duplicates.");
        }

    }

    int calculateSize(TreeNode node) {
        // Calculate the size of the BST
        if (node == null) {
            return 0;
        }
        return 1 + calculateSize(node.left) + calculateSize(node.right);
    }

    void checkBST(TreeNode node, int min, int max) {
        if (node == null) return;

        if ((min != Integer.MIN_VALUE && node.val <= min) || (max != Integer.MAX_VALUE && node.val >= max))
            throw new IllegalArgumentException("The array is not a valid binary search tree.");

        checkBST(node.left, min, node.val);
        checkBST(node.right, node.val, max);
    }


    void checkPostconditions(TreeNode result, int[] nums) {
        // Postconditions:
        // Result length must be the same as the input (nums)
        // The result must be a balanced binary search tree

        if(result == null) return;

        // Check that the result length must be the same as the input (nums)
        int resultSize = 0;
        resultSize = calculateSize(result);

        assert resultSize == nums.length : "The result length must be the same as the input (nums).";

        // Check that the result is a balanced binary search tree
        checkBST(result, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    void checkInvariants(TreeNode node) {

        if(node == null) return;

        // Invariant 1: The left subtree of a node contains only nodes with keys less than the node's key.
        if (node.left != null && node.left.val >= node.val)
            throw new IllegalArgumentException("Invariant 1 violated: The left subtree of a node contains only nodes with keys less than the node's key.");


        // Invariant 2: The right subtree of a node contains only nodes with keys greater than the node's key.
        if (node.right != null && node.right.val <= node.val)
            throw new IllegalArgumentException("Invariant 2 violated: The right subtree of a node contains only nodes with keys greater than the node's key.");


        // Invariant 3: Both the left and right subtrees must be valid binary search trees.
        if (node.left != null) checkBST(node.left, Integer.MIN_VALUE, node.val);

        if (node.right != null) checkBST(node.right, node.val, Integer.MAX_VALUE);

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        checkPreconditions(nums);
        TreeNode result = constructBSTRecursive(nums, 0, nums.length - 1);
        checkPostconditions(result, nums);
        return result;
    }

    private TreeNode constructBSTRecursive(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }

        // Middle element to maintain balance
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[mid]);

        // Recursively construct the left and right subtrees
        node.left = constructBSTRecursive(nums, left, mid - 1);
        node.right = constructBSTRecursive(nums, mid + 1, right);

        checkInvariants(node);

        return node;
    }
}
