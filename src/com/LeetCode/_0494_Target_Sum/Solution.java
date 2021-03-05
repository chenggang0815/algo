package com.LeetCode._0494_Target_Sum;
/*
494. Target Sum
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. Now you have 2 symbols + and -. For each integer, you should choose one from + and - as its new symbol.
Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3.
Output: 5
Explanation:
-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
 */
/*
思路1：枚举 time: (o(2^n)) space: o(n)递归栈的深度
1. 递归，枚举出所有可能的情况
2. 依次遍历第i个数时，可以将它添加+或-，递归地搜索这两种情况
3. 遍历完所有的N个数时，计算出本次遍历所有数的和，并判断是否等于S => 若等于 => count++
 */
public class Solution {
    static int findTargetSumWays1(int[] nums, int S){
        int[] res = new int[1];
        helper(nums, 0, 0, S, res);
        return res[0];
    }

    static void helper(int[] nums, int index, int sum, int S, int[] res){
        if (index == nums.length){
            if (sum == S) res[0]++;
        }else{
            helper(nums, index + 1, sum + nums[index], S, res);
            helper(nums, index + 1, sum - nums[index], S, res);
        }
    }
/*
    // int count = 0; 必须作为全局变量
    static int findTargetSumWays1_1(int[] nums, int S){
        int count = 0;
        helper1_1(nums, 0, 0, S, count);
        return count;
    }

    static void helper1_1(int[] nums, int index, int sum, int S, int count){
        if (index == nums.length){
            if (sum == S) count++;
        }else{
            helper1_1(nums, index + 1, sum + nums[index], S, count);
            helper1_1(nums, index + 1, sum - nums[index], S, count);
        }
    }
*/

    static int count = 0;
    static int findTargetSumWays2(int[] nums, int S){
        helper2(nums, 0, 0, S);
        return count;
    }

    static void helper2(int[] nums, int index, int sum, int S){
        if (index == nums.length){
            if (sum == S) count++;
            return;
        }

        helper2(nums, index + 1, sum + nums[index], S);
        helper2(nums, index + 1, sum - nums[index], S);

    }

//    static int findTargetSumWays(int[] nums, int S) {
//
//    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1, 1, 1};
        System.out.println(findTargetSumWays1_1(nums, 3));

    }
}
