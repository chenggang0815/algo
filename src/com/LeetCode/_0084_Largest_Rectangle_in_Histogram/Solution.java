package com.LeetCode._0084_Largest_Rectangle_in_Histogram;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
84. Largest Rectangle in Histogram
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.
*/
public class Solution {
    //暴力解法 time:o(n^2) space:o(1)
    //枚举以每个柱形位置为矩形高度的最大矩形面积，
    //所以每个中心位置从左右两边分别扩散，直到当前位置的高度是最远的大于等于中心位置
    //计算当前矩形的面积
    static int largestRectangleArea(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;

        int res = 0;
        for (int i = 0; i < len; i++){
            int left = i;
            while (left > 0 && heights[left - 1] >= heights[i]){
                left--;
            }

            int right = i;
            while (right < len - 1 && heights[right + 1] >= heights[i]){
                right++;
            }

            res = Math.max(res, heights[i] * (right - left + 1));
        }

        return res;
    }


    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(3);

        stack.addLast(2);
        stack.addLast(1);
        System.out.println(stack.peekLast());
        System.out.println(stack.pollLast());
        System.out.println(stack.peekLast());

    }
}
