package zest;

public class ClimbingStairs {

    /**
     * @param n value number of steps to reach the top. has to be positive integer.
     * @return number of distinct ways to climb to the top. has to be non-negative integer
     */
    public long climbStairs(int n) {
        // assumption: positive (in description of constraints) means not zero
        assert n>0;

        if (n <= 2) {
            return n;
        }
        long oneStepBefore = 2;
        long twoStepsBefore = 1;
        long allWays = 0; // allWays is still trivially >=0 by definition here so no assert

        for (int i = 2; i < n; i++) {
            allWays = oneStepBefore + twoStepsBefore;
            assert allWays >= 0;
            twoStepsBefore = oneStepBefore;
            oneStepBefore = allWays;
            assert twoStepsBefore >= 0;
            assert oneStepBefore >= 0;
        }

        assert allWays >=0;
        return allWays;
    }
}
