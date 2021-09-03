package com.LeetCode._0085_Maximal_Rectangle;

import java.util.Stack;

/*
85. Maximal Rectangle
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

Example 1:
Input: matrix =
[['1','0','1','0','0'],
 ['1','0','1','1','1'],
 ['1','1','1','1','1'],
 ['1','0','0','1','0']]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

solution:
we can use the same idea of largest rectangle in histogram,
1. for every row, we can calculate the height of each column
2. the height in a particular row is for top to this position, there are how many '1', and if current value equal '0', the height equal 0,
3. and we use a monotonic stack to solve this problem

height 1 0 1 0 0
height 2 0 2 1 1
height 3 1 3 2 2
height 4 0 0 3 0

*/
public class Solution {
    static int maximalRectangle(char[][] matrix){
        if (matrix.length == 0) return 0;

        int row = matrix.length, col = matrix[0].length, res = 0;
        int[] heights = new int[col];
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (matrix[i][j] == '1'){
                    heights[j] += 1;
                }else {
                    heights[j] = 0;
                }
            }
            res = Math.max(res, largestRectangleArea(heights));
        }

        return res;
    }

    static int largestRectangleArea(int[] heights){
        int res = 0;
        int[] temp = new int[heights.length + 2];
        temp[0] = 0; temp[heights.length + 1] = 0;
        System.arraycopy(heights, 0, temp, 1, heights.length + 1 - 1);
        heights = temp;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < heights.length; i++){
            while (!stack.isEmpty() && heights[i] < heights[stack.peek()]){
                int height = heights[stack.pop()];
                int width = i - stack.peek() - 1; // i - stack.peek() + 1 - 2
                res = Math.max(res, height * width);
            }
            stack.push(i);
        }

        return res;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}};

        System.out.println(maximalRectangle(matrix));
}
}
