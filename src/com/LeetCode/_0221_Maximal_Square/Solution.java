package com.LeetCode._0221_Maximal_Square;
/*
221. Maximal Square

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Input: matrix = [['1','0','1','0','0'],
                 ['1','0','1','1','1'],
                 ['1','1','1','1','1'],
                 ['1','0','0','1','0']]
Output: 4

 */
/*
思路1：暴力法 time:o(m*n*min(m,n)^2) 遍历每个元素：m*n，每个元素遍历最大可能的正方形：min(m,n)^2
首先需要找到最大正方形的边长，然后计算最大边长的平方
1. 遍历矩阵中的每个元素，每次遇到1，则将该元素作为正方形的左上角
2. 根据左上角所在的行和列计算可能的最大正方形的边长 => maxUseSide = Math.min(rows - i, columns - j) => 正方形的范围不能超出矩阵的行数和列数
3. 在该边长范围内寻找只包含1的最大正方形 => for(int k = 0; k < maxUseSide; k++)
4. 每次在下方新增一行（matrix[i+m][j + k]）以及在右方新增一列(matrix[i + k][j + m])
    4.1 判断新增的这一行和这一列是否满足所有元素都是1 => for(m = 0; m < k; m++)

思路2：动态规划
1. dp[i + 1][j + 1] 表示 「以第 i 行、第 j 列为右下角的正方形的最大边长」
2. 若某格子值为1，则以此为右下角的正方形的最大边长为：上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格
    => dp(i,j)=min(dp(i−1,j),dp(i−1,j−1),dp(i,j−1))+1
3. 对于第一行或者第一列的数字，因为i-1行和j-1列已经没有数字了，所以需要特殊处理 dp[i][j] = 1;

dp一般两种思路出递推方程，一种是画表推导（记得有一题硬币啥的是这样），一种是直接理解转移方程的含义（可用中文直接表达）。在压缩二维状态空间时都可再借表理解
 */
public class Solution {
    //Runtime: 18 ms, faster than 5.15% of Java online submissions for Maximal Square.
    static int maximalSquare1(char[][] matrix) {
        int maxSide = 0;
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0){
            return maxSide;
        }

        int rows = matrix.length, columns = matrix[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (matrix[i][j] == '1'){
                    maxSide = Math.max(maxSide, 1);
                    int maxUseSide = Math.min(rows - i, columns - j); //计算当前位置满足到边界的最大的正方形边长
                    for (int k = 1; k < maxUseSide; k++){
                        boolean flag = true;
                        if (matrix[i + k][j + k] == '0') break; //如果对角线元素不为1，则不用继续判断对角线元素所在的行和列的情况
                        for (int m = 0; m < k; m++){ //如果对角线元素为1，对新增的对角线元素所在的行和列的情况进行判断
                            if (matrix[i + k][j + m] == '0' || matrix[i + m][j + k] == '0'){
                                flag = false;
                                break;
                            }
                        }
                        if (flag) maxSide = Math.max(maxSide, k + 1); //更新最大的正方形边长，k+1表示当前的左上角的1和满足新增的k个行列
                        else break;
                    }
                }
            }
        }

        return maxSide * maxSide;
    }

    // Runtime: 7 ms, faster than 20.25% of Java online submissions for Maximal Square
    static int maximalSquare2(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int columns = matrix[0].length;
        int maxSide = 0;

        int[][] dp = new int[rows][columns];

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns; j++){
                if (matrix[i][j] == '1'){
                    if (i == 0 || j == 0) { //这一行非常重要，对于第一行或者第一列的数字，因为i-1行和j-1列已经没有数字了，所以需要特殊处理，否则数组会越界index=-1
                        dp[i][j] = 1;
                    }else{
                        dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    }
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }

    // Runtime: 7 ms, faster than 20.25% of Java online submissions for Maximal Square
    static int maximalSquare3(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;

        int rows = matrix.length;
        int columns = matrix[0].length;
        int maxSide = 0;

        int[][] dp = new int[rows][columns];
        for (int i = 0; i < rows; i++){
            if (matrix[i][0] == '1') {
                dp[i][0] = 1;
                maxSide = 1;
            }
        }

        for (int i = 0; i < columns; i++){
            if (matrix[0][i] == '1') {
                dp[0][i] = 1;
                maxSide = 1;
            }
        }

        for (int i = 1; i < rows; i++){
            for (int j = 1; j < columns; j++){
                if (matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]) + 1;
                    maxSide = Math.max(maxSide, dp[i][j]);
                }
            }
        }

        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] matrix = new char[][]{{'1','1','1','1','0'},
                                       {'1','0','1','1','1'},
                                       {'1','1','1','1','1'},
                                       {'1','0','0','1','0'}};

        System.out.println(maximalSquare3(matrix));
    }
}
