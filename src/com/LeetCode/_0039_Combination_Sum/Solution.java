package com.LeetCode._0039_Combination_Sum;

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
