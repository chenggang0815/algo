package LeetCode._0074_Search_a_2D_Matrix;
/*
74. Search a 2D Matrix
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true


 */
/*
solution 1: binary search time:o(log(m*n)) space:o(1)
1. 根据矩阵的规律，由于每一行从左到右递增，并且每一行的行首大于上一行的行尾 => 整个矩阵元素从左到右从上到下是递增的，因此可以看成一个递增的数组，用二分查找求解
2. 根据规律可算出数组的left 和 right边界值，根据mid的index也可算出元素的横纵坐标
            int left = 0;
            int right = matrix.length * matrix[0].length - 1;
            int i = mid / col_nums;
            int j = mid % col_nums;
3. 剩下的就是正常的二分查找
4. 相关题目 240
*/
public class Solution {
    static boolean searchMatrix(int[][] matrix, int target){
        if(matrix.length == 0) return false;

        int left = 0;
        int right = matrix.length * matrix[0].length - 1;
        int col_nums = matrix[0].length;
        while (left <= right){
            int mid = left + (right - left) / 2;
            int i = mid / col_nums;
            int j = mid % col_nums;
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return false;
    }
    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,60}};
        int[][] matrix = new int[][]{{}};
        System.out.println(searchMatrix(matrix, 15));
    }
}
