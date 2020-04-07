package com.leetcode._0024;

import java.util.ArrayList;
import java.util.List;

/*
78. Subsets Medium
Given a set of distinct integers, nums, return all possible subsets (the power set).
Note: The solution set must not contain duplicate subsets.

Example:
Input: nums = [1,2,3]
Output:
[ [3], [1], [2], [1,2,3], [1,3], [2,3], [1,2], [] ]
 */
public class Solution {
     static List<List<Integer>> res = new ArrayList<>();

//     static List<List<Integer>> subsets(int[] nums) {
//        helper(nums, 0, new ArrayList<>());
//        return res;
//    }

    // 错误解法 arr变量一直都是同一个，每次会覆盖上次的值
//    public static void helper(int[] nums, int index, ArrayList<Integer> arr){
//        if (index==nums.length){
//            System.out.println(arr);
//            res.add(arr);
//            return;
//        }
//        arr.add(nums[index]);
//        helper(nums, index+1, arr);
//        arr.remove(arr.size()-1);
//        helper(nums, index+1, arr);
//    }

//    public static void helper(int[] nums, int index, List<Integer> arr){
//        if (index==nums.length){
//            System.out.println(arr);
//            res.add(arr);
//            return;
//        }
//        List<Integer> temparr = new ArrayList<>();
//        for (int i=0;i<arr.size();i++){
//            temparr.add(arr.get(i));
//        }
//        temparr.add(nums[index]);
//        helper(nums, index+1, temparr);
//        helper(nums, index+1, arr);
//    }

    static public List<List<Integer>> permute(int[] nums) {

        List<Integer> arr = new ArrayList<>();

        helper(nums, arr);

        return res;
    }

    static public void helper(int[] nums, List<Integer> arr){
        if(arr.size()==nums.length){
            System.out.println(arr);
            res.add(new ArrayList<>(arr));
            return;
        }
        System.out.println("====");
        for(int i=0;i<nums.length;i++){
            //System.out.println(nums[i]);
            if(arr.contains(nums[i])){
                continue;
            }
            List<Integer> temparr = new ArrayList<>();
            for(int j=0;j<arr.size();j++){
                temparr.add(arr.get(j));
            }
            temparr.add(nums[i]);
            //System.out.println(temparr);
            helper(nums, temparr);

            temparr.remove(temparr.size()-1);
        }
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }
}
