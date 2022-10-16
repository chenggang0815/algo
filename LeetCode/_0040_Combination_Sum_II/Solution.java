package LeetCode._0040_Combination_Sum_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
40. Combination Sum II
Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sum to target.
Each number in candidates may only be used once in the combination.
Note: The solution set must not contain duplicate combinations.

Example 1:
Input: candidates = [10,1,2,7,6,1,5], target = 8
Output:
[
[1,1,6],
[1,2,5],
[1,7],
[2,6]
]

Example 2:
Input: candidates = [2,5,2,1,2], target = 5
Output:
[
[1,2,2],
[5]
]
*/

/*
直接回溯，无法解决重复
         2
     /  \ \   \
    5   2  1   2
   x   / \  \  x
      1  2   2
         x

1. 必须先排序，
2. 再判断相邻元素是否重复，如果重复直接跳过，因为之后重复的元素的解必然在之前元素的解之中
[2,5,2,1,2]
sorted
[1,2,2,2,5]
index=0                        1(i=0)
                        /      \       \        \
index=1            2(i=1)   2(i=2)   2(i=3)   5(i=4)
                   /     \  \        /   \      \
index=2          2(i=2) 2(3) 5(4)   2(3) 5(4)   5(5)
可以看出，第二层递归的三个2，后两个2(i=2, i=3)的解范围大于(i=index=1)的2，所以直接跳过后两个2

if(i > index && nums[i] == nums[i - 1]) continue;
在一个for循环中，所有被遍历到的数都是属于一个层级的。我们要让一个层级中，必须出现且只出现一个2，那么就放过第一个出现重复的2，但不放过后面出现的2
第一个出现的2的特点就是 cur == begin. 第二个出现的2 特点是cur > begin.
https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
*/
public class Solution {
    static List<List<Integer>> combinationSum(int[] candidates, int target){
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backTracking(candidates, res, new ArrayList<>(), target, 0, 0);

        return res;
    }

    static void backTracking(int[] nums, List<List<Integer>> res, List<Integer> temp, int target, int index, int sum){
        if (sum > target) return;
        if (sum == target){
            res.add(new ArrayList<>(temp));
            return;
        }

        for (int i = index; i < nums.length; i++){
            if(i > index && nums[i] == nums[i - 1]) continue;
            temp.add(nums[i]);
            backTracking(nums, res, temp, target, i + 1, sum + nums[i]);
            temp.remove(temp.size() - 1);
        }
    }

    // 参考47题的解法 用flag数组
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        int[] flag = new int[candidates.length];
        backTracking2(candidates, target, res, new ArrayList<>(), 0, 0, flag);

        return res;
    }

    void backTracking2(int[] candidates, int target, List<List<Integer>> res, List<Integer> temp, int sum, int index, int[] flag){
        if(sum > target) return;
        if(sum == target){
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i = index; i < candidates.length; i++){
            if(i > 0 && candidates[i] == candidates[i - 1] && flag[i - 1] == 0) continue;
            flag[i] = 1;
            temp.add(candidates[i]);
            backTracking2(candidates, target, res, temp, sum + candidates[i], i + 1, flag);
            flag[i] = 0;
            temp.remove(temp.size() - 1);
        }

    }


    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2,5,2,1,20}, 5));
        //System.out.println(combinationSum(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}, 27));
    }
}
