package com.LeetCode._0189_Rotate_Array;
/*
189. Rotate Array
Given an array, rotate the array to the right by k steps, where k is non-negative.
给定一个数组，将数组中的元素向右移动 k 个位置，其中k是非负数。

Follow up:
Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

Example 1:
Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]

Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

Constraints:
1 <= nums.length <= 2 * 104
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
*/

import java.lang.reflect.Array;
import java.util.Arrays;

public class Solution {
    /*
    Input: nums = [1,2,3,4,5,6,7], k = 3
          Output: [5,6,7,1,2,3,4]
1->4 0->3
4->7 3->6
7->3 6+3 % 7 = 2
3->6
6->2
2->5
5->1

nums[3] = nums[0]
nums[6] = nums[3]
nums[(6+3) % 7] = nums[2] = nums[6]

     */
    static void rotate(int[] nums, int k) {
        int length = nums.length;
        int num = nums[0];
        int i = 0;
        int temp1 = nums[0];
        int temp2 = nums[0];
        int count = 0;
        while (true){
            if (count == length
            ) break;
            temp1 = nums[i + k];
            nums[i + k] = temp2;
            temp2 = temp1;
            i = (i + k) % length;
            count++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
    }
}
