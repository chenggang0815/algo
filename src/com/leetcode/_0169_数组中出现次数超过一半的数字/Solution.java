package com.leetcode._0169_数组中出现次数超过一半的数字;
import java.util.HashMap;
/*
169
Given an array of size n, find the majority element.
The majority element is the element that appears more than ⌊ n/2 ⌋ times.
You may assume that the array is non-empty and the majority element always exist in the array.
Example 1:
Input: [3,2,3]
Output: 3
Example 2:
Input: [2,2,1,1,1,2,2]
Output: 2

思路1： 利用hashmap
思路2： 排序，取中间值再判断 time：o(nlogn) space:o(nlogn)
思路3： 守擂台算法 time：o(n) space: o(1)
 */
public class Solution {
    //time: o(n) space: o(N)
    static int majorityElement(int[] nums){
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i])){
                map.put(nums[i], 1);
            }else {
                map.put(nums[i], map.get(nums[i]) + 1);
            }
            if (map.get(nums[i]) > nums.length / 2){
                return nums[i];
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] nums = new int[]{2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));

    }
}
