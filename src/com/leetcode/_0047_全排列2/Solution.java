package com.leetcode._0047_全排列2;

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
    static List<List<String>> res = new ArrayList<>();

    static public List<List<String>> perList(String[] nums) {
        List<String> arr = new ArrayList<>();

        helper(nums, new boolean[nums.length], arr);

        return res;
    }

    static void helper(String[] nums, boolean[] visited, List<String> arr){
        if (arr.size() == nums.length){
            res.add(new ArrayList<>(arr));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if (visited[i]) continue;
            if(i > 0 && nums[i] == nums[i-1] && visited[i-1]) break;
            arr.add(nums[i]);
            visited[i] = true;
            helper(nums, visited, arr);
            arr.remove(arr.size() - 1);
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        String[] nums = new String[]{"A", "B", "B"};
        System.out.println(perList(nums));

    }
}
