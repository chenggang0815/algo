package com.LeetCode._0031_Next_Permutation;
/*
31. 下一个排列

实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2
3,2,1 → 1,2,3
1,1,5 → 1,5,1

 */

import java.util.Arrays;

/*
思路
1 暴力法 time：o(n!) space: o(n)

2. 一次遍历
1, 2, 3, 8, 5, 7, 6, 4
从后向前查找第一个相邻升序的元素对 (i, i+1)，满足 A[i] < A[i+1]。此时 [i+1,end) 必然是降序
在 [i+1,end) 从后向前查找第一个满足 A[i] < A[j] 的 j。A[i]、A[j] 分别就是上文所说的「小数」、「大数」
将 A[i] 与 A[j] 交换
可以断定这时 [i+1,end) 必然是降序，逆置 [i+1,end)，使其升序
如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
 */
public class Solution {
    static void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i + 1] <= nums[i]){
            i--;
        }

        if (i >= 0){
            int j = nums.length - 1;
            while (j >= 0 && nums[j] <= nums[i]){
                j--;
            }
            swap(nums, i, j);
        }

        reverse(nums, i + 1);
    }

    static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    static void reverse(int[] nums, int i){
        int j = nums.length - 1;
        while (i < j){
           swap(nums, i, j);
           i++;
           j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 8, 5, 7, 6, 4};
        nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }
}
