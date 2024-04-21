# SortedArray2BST
## Task 1: Code Coverage
We applied structural testing to apply 100% line coverage.
We reached not only 100% line coverage but also 100% branch coverage.
We used the JaCoCo plugin to analyze and generate coverage reports, the screenshots can be found in the ['Assets' folder](./Assets) as described in the assignment's instructions.
The screenshots were created before we wrote the tests for the other tasks, so the coverage is only for the code coverage tests.

## Task 2: Designing Contracts
### Pre-Conditions
We've designed four different pre-conditions for that exercise, according to the specification / exercise description.
We've extracted the checks for the pre-conditions into a separate method, which are called at the beginning of the `sortedArrayToBST` method.
The pre-conditions are as follows:

**Pre-Condition 1:** The input array must not be null. Code:
```java
if (nums == null) {
    throw new IllegalArgumentException("The input array must not be null.");
}
```

**Pre-Condition 2:** The input array length must be in the range [0, 10^4]. 
We do not need to check if the input array length is below 0, as this is not possible in Java.
Code:
```java
if (nums.length > Math.pow(10, 4)) {
    throw new IllegalArgumentException("The input array length must be in the range [0, 10^4].");
}
```

**Pre-Condition 3:** The input array must be sorted in ascending order.
Code:
```java
for (int i = 0; i < nums.length - 1; i++) {
    if (nums[i] > nums[i + 1]) {
        throw new IllegalArgumentException("The input array must be sorted in ascending order.");
    }
}
```

**Pre-Condition 4:** The input array must not contain any duplicates.
Code:
```java
for (int i = 0; i < nums.length - 1; i++) {
    if (nums[i] == nums[i + 1]) {
        throw new IllegalArgumentException("The input array must not contain any duplicates.");
    }
}
```

### Post-Conditions
We created two post-conditions from the requirements / exercise description.

**Post-Condition 1:** The size of the created BST must be the same as the input array length.
For that, we created a helper method called `calculateSize` which calculates the size of a BST.
Then, we assert that the size of the created BST is the same as the input array length.

**Post-Condition 2:** The result must be a balanced binary search tree.
For that, we created a helper method called `isBalanced` which checks if a BST is balanced.
The method throws an exception when the BST is not balanced.

### Invariants
**Invariant 1:** The left subtree of a node contains only nodes with keys less than the node's key.
We check this invariant at the end of the `constructBSTRecursive` method with this code:
```java
if (node.left != null && node.left.val >= node.val)
    throw new IllegalArgumentException("Invariant 1 violated: The left subtree of a node contains only nodes with keys less than the node's key.");
```

**Invariant 2:** The right subtree of a node contains only nodes with keys greater than the node's key.
We check this analogous to the first invariant:
```java
if (node.right != null && node.right.val <= node.val)
    throw new IllegalArgumentException("Invariant 2 violated: The right subtree of a node contains only nodes with keys greater than the node's key.");
```

**Invariant 3:** Both the left and right subtrees must be valid binary search trees.
We check this using our helper method `checkBST`, which we call at the end of the `constructBSTRecursive` method:
```java
if (node.left != null) checkBST(node.left, Integer.MIN_VALUE, node.val);

if (node.right != null) checkBST(node.right, node.val, Integer.MAX_VALUE);
```

## Task 3: Testing Contracts
We wrote tests for all pre-conditions, post-conditions and invariants.
The tests can be found in the [SortedArray2BSTTest.java](./src/SortedArray2BSTTest.java) file.
We tested the following:
- Pre-Condition 1: The input array must not be null by checking whether the method throws an IllegalArgumentException when the input array is null.
- Pre-Condition 2: The input array length must be in the range [0, 10^4] by checking whether the method throws an IllegalArgumentException when the input array length is greater than 10^4.
- Pre-Condition 3: The input array must be sorted in ascending order by checking whether the method throws an IllegalArgumentException when the input array is not sorted in ascending order.
- Pre-Condition 4: The input array must not contain any duplicates by checking whether the method throws an IllegalArgumentException when the input array contains duplicates.
- Post-Condition 1: The size of the created BST must be the same as the input array length by checking whether the size of the created BST is the same as the input array length using the `calculateSize` method.
- Post-Condition 2: The result must be a balanced binary search tree by checking whether the method throws an IllegalArgumentException when the created BST is not balanced. To check that, we used the `checkBST` method once again.
- Invariant 1: The left subtree of a node contains only nodes with keys less than the node's key.
- Invariant 2: The right subtree of a node contains only nodes with keys greater than the node's key.
- Invariant 3: Both the left and right subtrees must be valid binary search trees.

## Task 4: Property-Based Testing
The test generates random (sorted) arrays of different lengths by using the `sortedArrays` method.
When the input array is empty, we check that the output tree is null.
Otherwise, we check that the size of the created BST is the same as the input array length.
Then, we check the postconditions and invariants as described in earlier tasks.