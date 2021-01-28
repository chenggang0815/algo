package com.LeetCode._0048_Rotate_Image;
/*
48. Rotate Image
You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).
You have to rotate the image in-place, which means you have to modify the input 2D matrix directly.
DO NOT allocate another 2D matrix and do the rotation.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [[7,4,1],[8,5,2],[9,6,3]]

Example 2:
Input: matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
Output: [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 */

import java.util.Arrays;

/*
solution 1:
根据example 1找规律：
[1,2,3]     [7,4,1]
[4,5,6]  => [8,5,2]
[7,8,9]     [9,6,3]
    1.1 翻转前的第一行是翻转后倒数第一列的值，翻转前第二行是翻转后倒数第二列的值 => matrix_1[i][j] = matrix_2[j][n - 1 - i]
    1.2 复制数组，将对应位置的数赋值
 */
public class Solution {
    static void rotate(int[][] matrix){
        int n = matrix.length;
        int[][] matrix_2 = new int[n][n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                matrix_2[j][n - 1 - i] = matrix[i][j];
            }
        }

        for (int i= 0; i < n; i++){
            for (int j= 0; j < n; j++){
                matrix[i][j] = matrix_2[i][j];
            }
        }

    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
        rotate(matrix);
        for (int i = 0; i < matrix.length; i++){
            System.out.println(Arrays.toString(matrix[i]));
        }
    }
}
