package com.LeetCode._0046_Permutations;
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

/*
思路：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
回溯：搜索路径
             []
          /  |   \
         1   2   3
       /  | \
      1   2  3
      a  /|\
        1 2 3
        b c ok

1. 递归停止条件，list.length=nums.length 或者遍历到nums最后一个元素index=nums.length
2. 由于下次遍历可以选取当前元素的右边和左边元素 => for循环中每次遍历i要从0开始
3. 但是不能选取当前元素 => 即list中不能含有重复元素，所以可以通过多种方法来剪枝
   3.1 list中含有当前元素，不进行下一层遍历，相当于a在处剪枝
   3.2 通过一个数组，来对list中的元素进行标记,如[1,2,1]中，对1,2进行标记，在b，c可以剪枝
   3.3 在递归到最底层后，要进行回溯，list.remove(list.size()-1)和恢复元素标记
*/
public class Solution {

    static public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> arr = new ArrayList<>();
        //backtrack(nums, arr);
        backtrack2(nums, res, arr, new int[nums.length]);
        return res;
    }

    static void backtrack(int[] nums, List<List<Integer>> res, List<Integer> arr){
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
            backtrack(nums, res, arr);
            arr.remove(arr.size() - 1);
            System.out.println("递归之后 => " + arr);
        }
    }


    static void backtrack1(int[] nums, List<List<Integer>> res, int index, List<Integer> arr){
        if (index == nums.length){
            res.add(new ArrayList<>(arr));
        }
        for(int i = 0; i < nums.length; i++){
            if (arr.contains(nums[i])){
                continue;
            }
            arr.add(nums[i]);
            backtrack1(nums, res, index + 1, arr);
            //arr.remove(index);
            arr.remove(arr.size() - 1);
        }
    }

    static void backtrack2(int[] nums, List<List<Integer>> res, List<Integer> list, int[] flag){
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        // for(int i = 0; i < nums.length; i++){
        //     if(!list.contains(nums[i])){
        //         flag[i] = 1;
        //         list.add(nums[i]);
        //         backtrack(nums, res, list, flag);
        //         list.remove(list.size() - 1);
        //     }
        // }

        for(int i = 0; i < nums.length; i++){
            if(flag[i] == 0){
                flag[i] = 1;
                list.add(nums[i]);
                backtrack2(nums, res, list, flag);
                list.remove(list.size() - 1);
                flag[i] = 0;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }
}
