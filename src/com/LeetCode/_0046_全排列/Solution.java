package com.LeetCode._0046_全排列;
import java.util.ArrayList;
import java.util.List;

/*
46. 全排列

给定一个没有重复数字的序列，返回其所有可能的全排列。

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]
Example 3:
Input: nums = [1]
Output: [[1]]
 */


public class Solution {
    static List<List<Integer>> res = new ArrayList<>();

    static public List<List<Integer>> permute(int[] nums) {
        List<Integer> arr = new ArrayList<>();
        //backtrack(nums, arr);
        backtrack1(nums, 0, arr);
        return res;
    }

    static void backtrack(int[] nums, List<Integer> arr){
        if (arr.size() == nums.length){
            res.add(new ArrayList<>(arr));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (arr.contains(nums[i])){
                continue;
            }
            arr.add(nums[i]);
            System.out.println("    递归之前 => " + arr);
            backtrack(nums, arr);
            arr.remove(arr.size() - 1);
            System.out.println("递归之后 => " + arr);
        }
    }


    static void backtrack1(int[] nums, int index, List<Integer> arr){
        if (index == nums.length){
            res.add(new ArrayList<>(arr));
        }
        for(int i = 0; i < nums.length; i++){
            if (arr.contains(nums[i])){
                continue;
            }
            arr.add(nums[i]);
            backtrack1(nums, index + 1, arr);
            //arr.remove(index);
            arr.remove(arr.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }
}
