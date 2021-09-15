package LeetCode._0078_Subsets;

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

思路一： 回溯
1. 没有显示的递归停止目标，每次的list都要加入res
2. 下一次遍历的起点在当前元素的右边，并且不包括当前元素

思路二：
从前到后（或者从后到前），每次把当前元素加入到现有集合中，作为新增集合
1. res = [[]]
2. i = 0 nums[i] = 1
   res = [[]] + 1 => [[],[1]]
3. i = 1 nums[i] = 2
   [[],[1]] + 2 => [[], [1], [2], [1,2]]
4. i = 2 nums[i] = 3
   res = [[], [1], [2], [1,2]] + 3 => [[], [1], [2], [1,2], [3], [1,3], [2,3], [1,2,3]]

5. return res;
*/
public class Solution {
     static List<List<Integer>> subsets1(int[] nums) {
         List<List<Integer>> res = new ArrayList<>();
         backtrack(nums, res, 0, new ArrayList<>());

         return res;
    }

    public static void backtrack(int[] nums, List<List<Integer>> res, int index,  List<Integer> arr){
        res.add(new ArrayList<>(arr));
        for (int i = index; i < nums.length; i++){
            arr.add(nums[i]);
            backtrack(nums, res,i + 1, arr);
            arr.remove(arr.size() - 1);
        }
    }

    //从前到后（或者从后到前），每次把当前元素加入到现有集合中，作为新增集合
    static List<List<Integer>> subsets2(int[] nums){
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++){
             int n = res.size();
             for (int j = 0; j < n; j++){
                List<Integer> temp = new ArrayList<>(res.get(j));
                temp.add(nums[i]);
                res.add(new ArrayList<>(temp));
            }
        }

        return res;
    }



//    static public List<List<Integer>> permute(int[] nums) {
//
//        List<Integer> arr = new ArrayList<>();
//
//        helper(nums, arr);
//
//        return res;
//    }
//
//    static public void helper(int[] nums, List<Integer> arr){
//        if(arr.size()==nums.length){
//            res.add(new ArrayList<>(arr));
//            return;
//        }
//        for(int i=0;i<nums.length;i++){
//            if(arr.contains(nums[i])){
//                continue;
//            }
//            List<Integer> temparr = new ArrayList<>();
//            for(int j=0;j<arr.size();j++){
//                temparr.add(arr.get(j));
//            }
//            temparr.add(nums[i]);
//            helper(nums, temparr);
//            temparr.remove(temparr.size()-1);
//        }
//    }
//
    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(subsets1(nums));
    }
}
