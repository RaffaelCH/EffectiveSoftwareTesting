package zest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        getResult(result, new ArrayList<Integer>(), candidates, target, 0);

        return result;
    }

    private static void getResult(List<List<Integer>> result, List<Integer> cur, int[] candidates, int target, int start) {
        // bug fix attempt
        List<Integer> candidateList = new ArrayList<>(){
            {
                for(int k : candidates){
                    add(k);
                }
            }
        };

        for(int k : candidateList){
            if(k == 0){
                candidateList.remove(k);
                List<Integer> zeroResult = new ArrayList<>(){
                    {
                        add(0);
                        add(target);
                    }
                };
            }
        }



        if (target > 0) {
            for (int i = start; i < candidates.length && target >= candidates[i]; i++) {
                cur.add(candidates[i]);
                getResult(result, cur, candidates, target - candidates[i], i);
                cur.remove(cur.size() - 1);
            }
        } else if (target == 0) {
            result.add(new ArrayList<Integer>(cur));
        }
    }
}
