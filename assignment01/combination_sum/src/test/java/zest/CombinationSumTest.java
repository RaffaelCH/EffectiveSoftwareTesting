package zest;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

class CombinationSumTest {
    @Test
    @DisplayName("Returns empty array for empty array, null input")
    void invalidInput(){
        Assertions.assertEquals(0, CombinationSum.combinationSum(new int[]{}, 7).size());
        Assertions.assertEquals(0, CombinationSum.combinationSum(null, 7).size());
    }

    @Test
    @DisplayName("Returns correct array for distinct, unique combination")
    void normalInput(){
        ArrayList<Integer> elements = new ArrayList<Integer>() {
            {
                add(5);
                add(4);
            }
        };

        List<List<Integer>> actual = CombinationSum.combinationSum(new int[]{12, 5, 4, 8}, 9);
        Assertions.assertEquals(1, actual.size());
        Assertions.assertTrue(actual.get(0).containsAll(elements));
        Assertions.assertEquals(elements.size(), actual.get(0).size());
    }

    @Test
    @DisplayName("Returns correct array for combinations where numbers appear multiple times")
    void multipleInput(){
        ArrayList<Integer> elements = new ArrayList<Integer>() {
            {
                add(5);
            }
        };

        ArrayList<Integer> elements2 = new ArrayList<Integer>() {
            {
                for(int i = 0; i<5; i++){ add(1); }
            }
        };

        List<List<Integer>> actual = CombinationSum.combinationSum(new int[]{1, 5, 6, 8}, 5);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertTrue(actual.get(0).containsAll(elements2));
        Assertions.assertEquals(elements2 .size(), actual.get(0).size());
        Assertions.assertTrue(actual.get(1).containsAll(elements));
        Assertions.assertEquals(elements.size(), actual.get(1).size());
    }

    @Test
    @DisplayName("case of zeroes being included in lis of candidates")
    void zeroIncluded(){
        ArrayList<Integer> elements = new ArrayList<Integer>() {
            {
                add(5);
            }
        };

        List<List<Integer>> actual = CombinationSum.combinationSum(new int[]{0, 5, 6, 8}, 5);
        Assertions.assertEquals(149, actual.size());
        for(int j = 0; j<149; j++) {
            List<Integer> partialResult = new ArrayList<>();
            for (int i = 0; i < j; i++) {
                partialResult.add(0);
            }
            partialResult.add(5);
            Assertions.assertEquals(partialResult.size(), actual.get(j).size());
            for (int k = 0; k < partialResult.size(); k++) {
                Assertions.assertEquals(partialResult.get(k), actual.get(j).get(k));
            }
        }
    }
}