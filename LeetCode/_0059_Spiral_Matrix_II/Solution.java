package LeetCode._0059_Spiral_Matrix_II;

import java.util.Arrays;

/*
59. Spiral Matrix II
Given a positive integer n, generate an n x n matrix filled with elements from 1 to n2 in spiral order.

Example 1:
Input: n = 3
Output: [[1,2,3],[8,9,4],[7,6,5]]

[[1,2,3],
[8,9,4],
[7,6,5]]
*/
/*
Solution: Simulation time:O(n^2)
1. traverse from left -> right
                 top ->  bottom
                 right -> left
                 bottom -> top

2. initialize left=0 right=n-1 top=0 bottom=n-1

3.
   //left -> right
   for(int i = left; i <= right; i++) res[top][i] = cur++;
   top++;

   //top -> bottom
   for(int i = top; i <= bottom; i++) res[i][right] = cur++;
   right--;

   //right -> left
   for(int i = right; i >= left; i--) res[bottom][i] = cur++;
   bottom--;

   //bottom -> top
   for(int i = bottom; i >= top; i--) res[i][left] = cur++;
   left++;
*/
public class Solution {
    static int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        int cur = 1;

        while(left <= right && top <= bottom){
            // left -> right
            for(int i = left; i <= right; i++){
                res[top][i] = cur++;
            }
            top++;
            // top -> bottom
            for(int i = top; i <= bottom; i++){
                res[i][right] = cur++;
            }
            right--;
            // right -> left
            for(int i = right; i >= left; i--){
                res[bottom][i] = cur++;
            }
            bottom--;
            for(int i = bottom; i >= top; i--){
                res[i][left] = cur++;
            }
            left++;
        }

        return res;
    }


    public static void main(String[] args) {
        int[][] nums = generateMatrix(3);
        for (int[] num : nums){
            System.out.println(Arrays.toString(num));
        }
    }
}
