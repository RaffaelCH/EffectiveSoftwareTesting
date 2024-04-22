package zest;

public class FindDuplicate {
    public static int findDuplicate(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Array must not be null.");
          }
          if (nums.length <= 1) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
          }
        
          for (int num : nums) {
            if (num < 0 || num >= nums.length) {
              throw new IllegalArgumentException("Array values must be valid indices within the array.");
            }
          }

        int tortoise = nums[0];
        int hare = nums[0];
        // Phase 1: Finding the intersection point of the two runners.
        do {
            tortoise = nums[tortoise];
            hare = nums[nums[hare]];
        } while (tortoise != hare);

        // Phase 2: Finding the "entrance" to the cycle.
        tortoise = nums[0];
        while (tortoise != hare) {
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return hare;
    }
}
