package com.leetcode._0049;

import java.util.ArrayList;
import java.util.List;

/*
47. 全排列 II

给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


 */
public class Solution {
    static List<List<Integer>> res = new ArrayList<>();

    static public List<List<Integer>> permuteUnique(int[] nums) {
        List<Integer> arr = new ArrayList<>();

        helper(nums, arr);

        return res;
    }

    static void helper(int[] nums, List<Integer> arr){
        if (arr.size() == nums.length){
            res.add(new ArrayList<>(arr));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if (arr.contains(nums[i])) continue;
            arr.add(nums[i]);

            helper(nums, arr);
            arr.remove(arr.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2};
        System.out.println(permuteUnique(nums));

    }
}
