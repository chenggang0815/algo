package LeetCode._0073_Set_Matrix_Zeroes;
/*
思路1：time:o(m*n) space:o(m+n)
遍历两次 => 如果矩阵中任意一个格子有零我们就记录下它的行号和列号，这些行和列的所有格子在下一轮遍历中全部赋为零


思路3：time:o(m*n) space:o(1)
用matrix第一行和第一列记录该行该列是否有0,作为标志位

对于矩阵第一行和第一列的元素来说，如果有出现0，会与矩阵matrix[i][j]（i属于[1,cols] j属于[1,rows]）中有元素为0的情况冲突

所以 => 对于第一行,和第一列要设置一个标志位,为了防止自己这一行(一列)也有0的情况
*/
public class Solution {
    /*
    0 1 1
    1 0 1
    1 1 1

*/
    public void setZeroes(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean rowFlag = false;
        boolean colFlag = false;

        // 记录第一行和第一列是否有0
        for(int i = 0; i < rows; i++){
            if(matrix[i][0] == 0) colFlag = true;
        }

        for(int i = 0; i < cols; i++){
            if(matrix[0][i] == 0) rowFlag = true;
        }

        // 从i=1 和 j=1 开始，如果当前为0，令首行和首列为0
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < cols; j++){
                if(matrix[i][j] == 0){
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }
        // 从i=1 和 j=1 开始，如果当前的首行或首列为0，当前为0
        for(int i = 1; i < rows; i++){
            for(int j = 1; j < cols; j++){
                if(matrix[i][0] == 0 || matrix[0][j] == 0) matrix[i][j] = 0;
            }
        }

        if(rowFlag){
            for(int i = 0; i < cols; i++) matrix[0][i] = 0;
        }
        if(colFlag){
            for(int i = 0; i < rows; i++) matrix[i][0] = 0;
        }
    }
}
