package Facebook._0766_Toeplitz_Matrix;
/*
766. Toeplitz Matrix
Given an m x n matrix, return true if the matrix is Toeplitz. Otherwise, return false.
A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same elements.

Example 1:
Input: matrix = [[1,2,3,4],
                 [5,1,2,3],
                 [9,5,1,2]]
Output: true
Explanation:
In the above grid, the diagonals are:
"[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
In each diagonal all elements are the same, so the answer is True.
*/
/*
Solution:
1 2 3 4
5 1 2 3
9 5 1 2

1: (0,0) (1,1) (2,2)
2: (0,1) (1,2) (2,3)
3: (0,2) (1,3)
4: (0,3)
5: (1,0) (2,1)

1. iterate the matrix, check each number, if(matrix[i][j] != matrix[i+1][j+1]) return false;
*/
public class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(i + 1 >= rows || j + 1 >= cols) continue;
                if(matrix[i][j] != matrix[i + 1][j + 1]) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

    }
}
