package com.LeetCode._0042_Trapping_Rain_Water;
/*
42. Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1,
compute how much water it can trap after raining.

Example 1:
Input: height = [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */

/*
solution 1：暴力法 - 遍历行 time:o(n*M) space:O(1) M表示最大高度 = mac(height)

solution 2: 暴力法 - 遍历列 time:o(n^2) space:O(1)
求每一列的水，只需要关注当前列，以及左边最高的墙，右边最高的墙就够了。
装水的多少，当然根据木桶效应，只需要看左边最高的墙和右边最高的墙中较矮的一个就够了
    2.1 如果较矮的墙的高度小于等于当前列的墙的高度 => 不会有水
    2.2 如果较矮的墙的高度大于当前列的墙的高度 => min(max_left, max_right) - height[i]


solution 3: 动态规划 time:o(n) space:O(n)
对于数组中的每个元素，找出下雨后水能达到的最高位置 => 等于两边最大高度的较小值减去当前高度的值

solution 4: 双指针 time:o(n) space:O(1)
left_max：左边的最大值，它是从左往右遍历找到的
right_max：右边的最大值，它是从右往左遍历找到的
left：从左往右处理的当前下标
right：从右往左处理的当前下标
    4.1 在某个位置i处，它能存的水，取决于它左右两边的最大值中较小的一个。
    4.2 当我们从左往右处理到left下标时，左边的最大值left_max对它而言是可信的，但right_max对它而言是不可信的。（由于中间状况未知，对于left下标而言，right_max未必就是它右边最大的值）
    4.3 当我们从右往左处理到right下标时，右边的最大值right_max对它而言是可信的，但left_max对它而言是不可信的。
对于位置left而言，它左边最大值一定是left_max，右边最大值“大于等于”right_max，这时候，如果left_max<right_max成立，那么它就知道自己能存多少水了。无论右边将来会不会出现更大的right_max，都不影响这个结果。 所以当left_max<right_max时，我们就希望去处理left下标，反之，我们希望去处理right下标。

solution 5： 栈 time:o(n) space:O(n)

 */
public class Solution {
    static int trap2(int[] height){
        int sum = 0;
        for (int i = 1; i < height.length - 1; i++){
            int max_left = 0;
            int max_right = 0;
            for (int j = 0; j < i; j++){
                max_left = Math.max(max_left, height[j]);
            }

            for (int j = i + 1; j < height.length; j++){
                max_right = Math.max(max_right, height[j]);
            }

            int min = Math.min(max_left, max_right);
            if (min > height[i]){
                sum += (min - height[i]);
            }
        }

        return sum;
    }

    // time: o(n) space:o(n)
    static int trap3(int[] height) {
        int sum = 0;
        int[] max_left = new int[height.length];
        int[] max_right = new int[height.length];

        for (int i = 1; i < height.length - 1; i++){
            max_left[i] = Math.max(max_left[i - 1], height[i - 1]);
        }

        for (int i = height.length - 2; i >= 0; i--){
            max_right[i] = Math.max(max_right[i + 1], height[i + 1]);
        }

        for (int i = 1; i < height.length - 1; i++){
            int min = Math.min(max_left[i], max_right[i]);
            if (min > height[i]) sum += (min - height[i]);
        }

        return sum;
    }

    static int trap4(int[] height){
        int sum = 0;
        int left = 0;
        int right = height.length - 1;
        int max_left = 0;
        int max_right = 0;

        while (left < right){
            if (height[left] < height[right]){
                if (height[left] >= max_left){
                    max_left = height[left];
                }else{
                    sum += (max_left - height[left]);
                }
                left++;
            }else{
                if (height[right] >= max_right){
                    max_right = height[right];
                }else{
                    sum += (max_right - height[right]);
                }
                right--;
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(height));
    }
}
