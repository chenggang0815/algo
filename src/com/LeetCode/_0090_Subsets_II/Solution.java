package com.LeetCode._0090_Subsets_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

    static void backtrack(List<List<Integer>> res, int[] nums, int start, List<Integer> track){
        res.add(new ArrayList<>(track));

        for(int i = start; i < nums.length; i++){
           if (i > start && nums[i] == nums[i - 1]) continue;
           track.add(nums[i]);
           backtrack(res, nums, i + 1, track);
           track.remove(track.size() - 1);
        }
    }
    static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(res, nums, 0, new ArrayList<>());

        return res;
    }

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }
}
