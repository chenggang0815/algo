package com.LeetCode._0090_Subsets_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
90. Subsets II
Given an integer array nums that may contain duplicates, return all possible subsets (the power set).
The solution set must not contain duplicate subsets. Return the solution in any order.
Example 1:
Input: nums = [1,2,2]
Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
Example 2:
Input: nums = [0]
Output: [[],[0]]

回溯：
解题思路：
1. 剪枝条件与47题略有差异（都是有重复元素）
    1.1 47题下次遍历的元素可以是当前元素的左边和右边元素，i=0
    1.2 本题中下次遍历的元素只能从当前元素的右边开始 i = i + 1
    1.3 所以本题中不需要判断当前元素是否重复选取 => if(flag[i] == 1) continue;

2. 与47题相同的地方，在于对nums中重复元素的判断，
    2.1 本题可以采用47题相似的方法,backtrack1中实现
    2.2 也可以直接判断 if (i > index && nums[i] == nums[i - 1]) continue;
eg:
[1,2,2']
                1     index=0 i=0
              /    \
             /      \
index=1 i=1 2        2'  index=1 i=2
           /         x
          2' =>[1,2,2']
     index=2 i=2
如上图所示，在x处，搜索2’会得到重复结果，触发剪枝条件：if (i > index && nums[i] == nums[i - 1])，所以在2'剪枝
*/
public class Solution {

    static void backtrack1(int[] nums, List<List<Integer>> res, ArrayList<Integer> list, int[] flag, int index){
        res.add(new ArrayList<>(list));

        for(int i = index; i < nums.length; i++){
            //if(flag[i] == 1) continue; //不需要判断
            if(i > 0 && nums[i] == nums[i - 1] && flag[i - 1] == 0) continue;
            flag[i] = 1;
            list.add(nums[i]);
            backtrack1(nums, res, list, flag, i + 1);
            flag[i] = 0;
            list.remove(list.size() - 1);
        }
    }

    static void backtrack2(List<List<Integer>> res, int[] nums, int index, List<Integer> track){
        res.add(new ArrayList<>(track));

        for(int i = index; i < nums.length; i++){
            if (i > index && nums[i] == nums[i - 1]) continue;
            track.add(nums[i]);
            backtrack2(res, nums, i + 1, track);
            track.remove(track.size() - 1);
        }
    }

    static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack2(res, nums, 0, new ArrayList<>());

        return res;
    }

    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1,2,2}));
    }
}
