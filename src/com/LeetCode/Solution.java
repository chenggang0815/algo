package com.LeetCode;
import java.util.*;
public class Solution {

    static List<List<Integer>> res = new ArrayList<>();
    static List<List<Integer>> subset(int[] nums){
        List<Integer> temp = new ArrayList<>();

        helper(nums, 0, temp);

        return res;
    }

    static void helper(int[] nums, int index, List<Integer> temp){
        res.add(new ArrayList<>(temp));

        for (int i = index; i < nums.length; i++){
            temp.add(nums[i]);
            helper(nums, i + 1, temp);
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println(subset(new int[]{1, 2, 3}));
    }
}
