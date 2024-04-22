package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import static org.junit.jupiter.api.Assertions.assertTrue;
import net.jqwik.api.lifecycle.BeforeProperty;
import org.junit.jupiter.api.Assertions;

import java.util.stream.Collectors;
import java.util.List;

public class LongestIncreasingSubsequenceTest {

    @Test
    public void testEmptyArray() {
        assertEquals(0, new LongestIncreasingSubsequence().lengthOfLIS(new int[]{}));
    }

    @Test
    public void testSingleElementArray() {
        assertEquals(1, new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1}));
    }

    @Test
    public void testIncreasingArray() {
        assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void testDecreasingArray() {
        assertEquals(1, new LongestIncreasingSubsequence().lengthOfLIS(new int[]{4, 3, 2, 1}));
    }

    @Test
    public void testArrayWithDuplicates() {
        assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(new int[]{1, 2, 2, 3, 4}));
    }

    @Test
    public void testMixedArray() {
        assertEquals(4, new LongestIncreasingSubsequence().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 7, 101, 18}));
    }

    @Test
    public void testNullArray() {
        assertThrows(NullPointerException.class, () -> new LongestIncreasingSubsequence().lengthOfLIS(null));
    }

    
    private LongestIncreasingSubsequence lis;

    @BeforeProperty
    public void setup() {
        lis = new LongestIncreasingSubsequence();
    }

    @Property
    public void alwaysNonNegativeLength(@ForAll int[] array) {
        int length = lis.lengthOfLIS(array);
        Assertions.assertTrue(length >= 0);
    }

    @Property
    public void singleElementArrayAlwaysHasLengthOne(@ForAll @IntRange(min = -1000, max = 1000) int element) {
        int length = lis.lengthOfLIS(new int[]{element});
        Assertions.assertTrue(length == 1);
    }

    @Property
    public void increasingSequenceLengthEqualsArrayLength(@ForAll List<@IntRange(min = -1000, max = 1000) Integer> numbers) {
        int[] array = numbers.stream().distinct().sorted().mapToInt(i -> i).toArray();
        int length = lis.lengthOfLIS(array);
        Assertions.assertTrue(length == array.length);
    }

    @Property
    public void lengthIsAtLeastOneForNonEmptyArrays(@ForAll("nonEmptyArrays") int[] array) {
        int length = lis.lengthOfLIS(array);
        Assertions.assertTrue(length >= 1);
    }

    @Provide
    Arbitrary<int[]> nonEmptyArrays() {
        return Arbitraries.integers().array(int[].class).ofMinSize(1);
    }

  
}
