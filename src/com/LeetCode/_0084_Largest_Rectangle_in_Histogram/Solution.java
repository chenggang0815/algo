package com.LeetCode._0084_Largest_Rectangle_in_Histogram;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/*
84. Largest Rectangle in Histogram
Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
return the area of the largest rectangle in the histogram.

思路1：暴力解法
思路2：
思路3：单调栈
https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/84-by-ikaruga/
*/
public class Solution {
    //暴力解法 time:o(n^2) space:o(1)
    //枚举以每个柱形位置为矩形高度的最大矩形面积，
    //所以每个中心位置从左右两边分别扩散，直到当前位置的高度是最远的大于等于中心位置
    //计算当前矩形的面积
    static int largestRectangleArea1(int[] heights) {
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

    static int largestRectangleArea2(int[] heights) {
        int len = heights.length;
        if (len == 0) return 0;

        int res = 0;
        int[] temp = new int[len + 2];
        temp[0] = 0;
        temp[len + 1] = 0;
        int index = 0;
        for (int i = 1; i < len + 1; i++){
            temp[i] = heights[index++];
        }

        Stack<Integer> stack = new Stack<>();
        for(int i = 0; i < temp.length; i++){
            while (!stack.isEmpty() && temp[i] < temp[stack.peek()]){
                int height = temp[stack.pop()];
                int width = i - stack.peek() - 1;
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }

        return res;
    }

        public static void main(String[] args) {
        //int[] heights = new int[]{2,1,5,6,2,3};
            int[] heights = new int[]{3, 1, 3, 2, 2};
        System.out.println(largestRectangleArea2(heights));
    }
}
