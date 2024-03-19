package zest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    static boolean zeroSeen = false;
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // bug fix: null case
        if (candidates != null) {
            Arrays.sort(candidates);
            List<List<Integer>> result = new ArrayList<>();
            getResult(result, new ArrayList<Integer>(), candidates, target, 0);

            return result;
        } else {
            return new ArrayList<List<Integer>>();
        }
    }

    private static void getResult(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target, int start) {
        // bug fix attempt with if condition and else block
        if (target > 0) {
            for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
                if(candidates[i] != 0){
                    cur.add(candidates[i]);
                    getResult(result, cur, candidates, target - candidates[i], i);
                    cur.remove(cur.size() - 1);
                } else {
                    zeroSeen = true;
                    for(int j = 0; j<149; j++){
                        List<Integer> partialResult = new ArrayList<>();
                        for(int k = 0; k<j; k++){
                            partialResult.add(0);
                        }
                        partialResult.add(target);
                        result.add(partialResult);
                    }
                }
            }
        } else if (target == 0) {
            result.add(new ArrayList<>(cur));
            if(zeroSeen){
                result.remove(result.size()-1);
            }
            zeroSeen = false;
        }
    }
}
