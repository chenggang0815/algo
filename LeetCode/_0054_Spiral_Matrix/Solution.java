package LeetCode._0054_Spiral_Matrix;

import java.util.ArrayList;
import java.util.List;

/*
54. Spiral Matrix
Given an m x n matrix, return all elements of the matrix in spiral order.

Example 1:
Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,3,6,9,8,7,4,5]

[[1,2,3],
 [4,5,6],
 [7,8,9]]
*/
/*
Solution:
1. 为什么要在最后两个方向遍历时判断条件，bottom >= top 和 right >= left
2. 这是因为矩阵不一定是方阵，
    2.1 当 row > col
      [1,2, 3, 4]
      [5,6, 7, 8]
      [9,10,11,12]
      遍历一圈后，只剩一行 6,7
   2.3 当 col > row
      [1,2,3]
      [4,5,6]
      [7,8,9]
      [10,11,12]
      遍历一圈后，只剩一列 5
                        8
3. 当遍历完这一行或者一列后，如果继续遍历就会有重复值，如果是方阵，则不需要判断

4. 带入 matrix=[[1,2,3,4]] or matrix=[[1],[2],[3],[4]] 计算一遍就明白了！
*/
public class Solution {

    static List<Integer> generateMatrix(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;

        while(left <= right && top <= bottom){
            // left -> right
            for(int i = left; i <= right; i++){
                res.add(matrix[top][i]);
            }
            top++;
            // top -> bottom
            for(int i = top; i <= bottom; i++){
                res.add(matrix[i][right]);
            }
            right--;
            // right -> left
            for(int i = right; i >= left && bottom >= top; i--){ // The only tricky part is that when I traverse left or up I have to check whether the row or col still exists to prevent duplicates.
                res.add(matrix[bottom][i]);
            }
            bottom--;
            // bottom -> top
            for(int i = bottom; i >= top && right >= left; i--){ //The only tricky part is that when I traverse left or up I have to check whether the row or col still exists to prevent duplicates.
                res.add(matrix[i][left]);
            }
            left++;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(generateMatrix(new int[][]{{1,2,3,4}}));
    }
}
