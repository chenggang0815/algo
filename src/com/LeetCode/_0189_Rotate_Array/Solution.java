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
思路1：使用额外的数组 time:o(n) space:o(n)
        for (int i = 0; i < nums.length; i++){
            res[(i + k) % nums.length] = nums[i];
        }

思路2：环状替换 time:o(n) space:o(1)
Input: nums = [1,2,3,4,5,6,7], k = 3 Output: [5,6,7,1,2,3,4]
1->4 0->3
4->7 3->6
7->3 6+3 % 7 = 2
3->6
6->2
2->5
5->1
转移nums.length次
Input: nums = [-1,-100,3,99], k = 2 Output: [3,99，-1，-100]
-1 -> 3
3 -> -1
转移nums.length次后，数组不变
可以得出如果数组为偶数的长度，需要每次转移到相同位置i后，从i+1继续转移，总共还是转移nums.length次

思路3：数组翻转 time:o(n) space:o(1)
将数组的元素向右移动k次后，尾部k % n个元素会移动至数组头部，其余元素向后移动k % mod个位置
1. 可以先将所有元素翻转，这样尾部的k % n个元素就被移至数组头部，
2  再翻转[0,k % n−1]区间的元素和 [k % n, n-1]区间的元素即能得到最后的答案
eg:[1,2,3,4,5,6,7] k=3
1. 翻转所有元素 => [7,6,5,4,3,2,1]
2. 翻转[0,k % n−1]即[0,3]的元素 => [5,6,7,4,3,2,1]
4. 翻转[k % n,n−1]即[3,6]的元素 => [5,6,7,1,2,3,4]
*/
    static void rotate1(int[] nums, int k){
        int[] res = new int[nums.length];
//        int count = nums.length;
//        int i = 0;
//        while (count > 0){
//            res[(i + k) % nums.length] = nums[i];
//            i++;
//            count--;
//        }

        for (int i = 0; i < nums.length; i++){
            res[(i + k) % nums.length] = nums[i];
        }

        System.arraycopy(res, 0, nums, 0, res.length);
    }

    static void rotate2(int[] nums, int k) {
        int length = nums.length;
        int i = 0;
        int temp1 = nums[0];
        int temp2 = nums[0];
        int count = 0;
        int start = 0;
        while (true){
            if (count == length
            ) break;
            i = (i + k) % length;
            temp1 = nums[i];
            nums[i] = temp2;
            temp2 = temp1;
            count++;

            if (i == start){
                i = (i + 1) % length;
                start = i;
                temp2 = nums[i];
            }
        }
    }

    static void rotate3(int[] nums, int k) {
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    static void reverse(int[] nums, int left, int right){
        while (left < right){
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        //int[] nums = new int[]{1,2,3,4,5,6,7};
        int[] nums = new int[]{-1,-100,3,99};
        rotate2(nums, 2);
        System.out.println(Arrays.toString(nums));
    }
}


