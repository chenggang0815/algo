package com.LeetCode._0565_Array_Nesting;

import java.util.HashSet;
import java.util.Set;

/*
565. Array Nesting
You are given an integer array nums of length n where nums is a permutation of the numbers in the range [0, n - 1].

You should build a set s[k] = {nums[k], nums[nums[k]], nums[nums[nums[k]]], ... } subjected to the following rule:
The first element in s[k] starts with the selection of the element nums[k] of index = k.
The next element in s[k] should be nums[nums[k]], and then nums[nums[nums[k]]], and so on.
We stop adding right before a duplicate element occurs in s[k].
Return the longest length of a set s[k].

Constraints:
1. 1 <= nums.length <= 105
2. 0 <= nums[i] < nums.length
3. All the values of nums are unique.

Example 1:

Input: nums = [5,4,0,3,1,6,2]
Output: 4
Explanation:
nums[0] = 5, nums[1] = 4, nums[2] = 0, nums[3] = 3, nums[4] = 1, nums[5] = 6, nums[6] = 2.
One of the longest sets s[k]:
s[0] = {nums[0], nums[5], nums[6], nums[2]} = {5, 6, 2, 0}

*/
/*
1. 暴力求解
2. 因为 the numbers in the range [0, n - 1] 和 All the values of nums are unique
   所以这n个数属于0-n且没有重复，则对于一个数num,只有一个index，使得nums[index] = num
   所以可以直接遍历数组，对于遍历过的数，标记为负，统计每个循环的大小。
*/
public class Solution {
    static public int arrayNesting(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            Set<Integer> set = new HashSet<>();
            int j = i;
            while(0 <= j && j < nums.length && nums[j] >= 0){
                int index = nums[j];
                System.out.println(j);
                set.add(j);
                nums[j] = -1;
                j = nums[index];
                System.out.println(index);
                set.add(index);
            }
            System.out.println("=====");
            res = Math.max(set.size(), res);
        }

        return res;
    }

    static public int arrayNesting2(int[] nums) {
        int res = 0;
        for(int i = 0; i < nums.length; i++){
            int size = 0;
            int j = i;
            while(0 <= j && j < nums.length && nums[j] >= 0){
                int index = nums[j];
                nums[j] = -1;
                j = index;
                size++;
            }
            res = Math.max(size, res);
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(arrayNesting(new int[]{5,4,0,3,1,6,2}));
    }
}
