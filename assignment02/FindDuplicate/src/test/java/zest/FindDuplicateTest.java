package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import net.jqwik.api.*;
import java.util.HashSet;
import java.util.Set;



public class FindDuplicateTest {

    @Test
    void findDuplicate_Standard() {
        int[] nums = {2, 3, 1, 4, 2};  // All numbers are within index range [0, 4]
        assertEquals(2, FindDuplicate.findDuplicate(nums));
    }

    @Test
    void findDuplicate_WithMinimumSize() {
        int[] nums = {0, 1, 0};  // All numbers are within index range [0, 2]
        assertEquals(0, FindDuplicate.findDuplicate(nums));
    }

    @Test
    void findDuplicate_InvalidIndex() {
        int[] nums = {2, 3, 5};  // '5' is out of index range [0, 2]
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums));
    }

    @Test
    void findDuplicate_NullArray() {
        int[] nums = null;
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums));
    }

    @Test
    void findDuplicate_EmptyArray() {
        int[] nums = {};
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums));
    }

    @Test
    void findDuplicate_EntersAndMeetsInCycle() {
        int[] nums = {1, 3, 4, 2, 2};
        assertEquals(2, FindDuplicate.findDuplicate(nums));
    }

    @Test
    void findDuplicate_ValueTooLarge() {
        int[] nums = {1, 2, 5, 3};
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums),
            "Array values must be valid indices within the array.");
    }

    @Test
    void findDuplicate_NegativeValues() {
        int[] nums = {1, 2, -1, 3};
        assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(nums),
            "Array values must be valid indices within the array.");
    }
   
    
    @Property
    void shouldThrowForInvalidArrays(@ForAll("invalidDuplicateArrays") int[] array) {
      assertThrows(IllegalArgumentException.class, () -> FindDuplicate.findDuplicate(array));
    }
    
    @Provide
    Arbitrary<int[]> validDuplicateArrays() {
      return Arbitraries.integers().between(0, 49).array(int[].class)
        .ofMinSize(2)
        .ofMaxSize(49)
        .filter(array -> { // Ensure elements are within valid index range
          Set<Integer> seen = new HashSet<>();
          for (int num : array) {
            if (num < 0 || num >= array.length) { // Check for negative or out-of-bounds values
              return false;
            }
            if (seen.contains(num)) {
              return true;
            }
            seen.add(num);
          }
          return false;
        });
    }
    
    @Provide
    Arbitrary<int[]> invalidDuplicateArrays() { // Allow generation of arrays with invalid indices for testing
      return Arbitraries.integers().between(Integer.MIN_VALUE, 50).array(int[].class)
        .ofMinSize(2);
    }
    


    
    
    
}
