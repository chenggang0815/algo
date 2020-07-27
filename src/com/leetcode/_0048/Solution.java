package com.leetcode._0048;
import java.util.ArrayList;
import java.util.List;

/*
46. 全排列

给定一个没有重复数字的序列，返回其所有可能的全排列。
 */
public class Solution {
    static List<List<Integer>> res = new ArrayList<>();

    static public List<List<Integer>> permute(int[] nums) {
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
            if (arr.contains(nums[i])){
                continue;
            }
            arr.add(nums[i]);
            helper(nums, arr);
            arr.remove(arr.size() - 1);
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }
}
