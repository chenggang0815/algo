package LeetCode._0039_Combination_Sum;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode-cn.com/problems/combination-sum/solution/zu-he-zong-he-by-leetcode-solution/

Example 1:
Input: candidates = [2,3,6,7], target = 7
Output: [[2,2,3],[7]]
Explanation:
2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
7 is a candidate, and 7 = 7.
These are the only two combinations.

Example 2:
Input: candidates = [2,3,5], target = 8
Output: [[2,2,2,2],[2,3,3],[3,5]]
 */
/*
回溯 - 解题思路
1. 递归退出条件：当前list中元素和>=target，如果sum==target，res.add（list）,再return
2. 需要注意的是，可以重复选取当前的数字，但是解集中又不能有重复解
   2.1 即下次递归遍历中，index只能从当前数字以及当前数字的右边开始，所以for循环的代码如下：
           for(int i = index; i < candidates.length; i++){
            list.add(candidates[i]);
            backtrack(candidates, target, res, list, i, sum + candidates[i]);
            list.remove(list.size() - 1);
        }
   2.2 以下写法会造成解集中有重复解：
           for(int i = 0; i < candidates.length; i++){
            list.add(candidates[i]);
            backtrack(candidates, target, res, list, i, sum + candidates[i]);
            list.remove(list.size() - 1);
        }
*/
public class Solution {
//    static int sum(List<Integer> track){
//        int sum = 0;
//        if (track.size() == 0) return sum;
//        for (int item : track){
//            sum += sum;
//        }
//
//        return sum;
//    }

    static void backtrack(List<List<Integer>> res, int[] candidates, int target, List<Integer> track, int sum, int index){
        if (sum >= target){
            if (sum == target){
            res.add(new ArrayList<>(track));
            }
            return;
        }

        for (int i = index; i < candidates.length; i++){
                track.add(candidates[i]);
                backtrack(res, candidates, target, track, sum + candidates[i], i);
                track.remove(track.size() - 1);
            }
        }

    static public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, candidates, target, new ArrayList<>(), 0, 0);

        return res;
    }


    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,3,6, 7}, 7));
    }
}
