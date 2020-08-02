package com.leetcode._0020;
import java.util.*;
/*
448. Find All Numbers Disappeared in an Array Easy 有序数组中消失的数字
给定一个范围在  1 ≤ a[i] ≤ n ( n = 数组大小 ) 的整型数组，数组中的元素一些出现了两次，另一些只出现一次。
找到所有在 [1, n] 范围之间没有出现在数组中的数字。

您能在不使用额外空间且时间复杂度为O(n)的情况下完成这个任务吗? 你可以假定返回的数组不算在额外空间内。

Example:
Input:
[4,3,2,7,8,2,3,1]
Output:
[5,6]

思路一： 利用hashmap或者set， 先将所有元素放到hashmap中。再遍历[0, nums.length]， 统计hashmap中没有的数
         time: o(n) space: o(n)

思路二： 因为如果不缺少数字，则数字应该是在[1,n]之间的，所以用每个数 - 1表示索引index，则每个数都能唯一表示一个索引
eg ： 4 2 3 1 => 3 1 2 0
eg :  4 2 2 1 => 3 1 1 0
缺少index=2的索引，所以将nums[index] = -nums[index]（条件nums[index] > 0） ， 则存在的索引对应的值都为负数了。
而为正数的值对应的索引就是缺少的数]
 */
public class Solution {

    //time: o(n) space:o(n)
    static public List<Integer> findDisappearedNumbers1(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], nums[i]);
        }
        for(int n = 1; n <= nums.length; n++){
            if(!map.containsKey(n)){
                res.add(n);
            }
        }

        return res;
    }

    //time:o(n) space:o(1)
    static ArrayList<Integer> findDisappearedNumbers2(int[] nums){
        for (int i = 0; i < nums.length; i++){
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0){
                nums[index] = -nums[index];
            }
        }
        System.out.println(Arrays.toString(nums));

        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < nums.length; i++){
            if (nums[i] > 0){
                arr.add(i + 1);
            }
        }

        return arr;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{4,3,2,7,8,2,3,1};
        System.out.println(findDisappearedNumbers2(nums));

    }
}
