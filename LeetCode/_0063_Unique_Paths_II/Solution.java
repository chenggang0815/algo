package LeetCode._0063_Unique_Paths_II;
/*
63. Unique Paths II
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
Now consider if some obstacles are added to the grids. How many unique paths would there be?
An obstacle and space is marked as 1 and 0 respectively in the grid.
*/
/*
Solution: Dynamic Programing time:O(m*n) space:o(m*n)
1. because robot can only move either down or right
2. for a specific position (i,j), there are only two way to reach it, (i-1, j) and (i, j-1) => dp[i][j] = dp[i-1][j] + dp[i][j-1]
3. if matrix[i][j]=1, then we just ignore this position, we can't reach there, dp[i][j] = 0
   if matrix[i][j]!=1 => dp[i][j] = dp[i-1][j] + dp[i][j-1]
4. initialize:
    4.1 because there is only one way to reach the first row,
       dp[0][i]=0 until matrix[i][j]=1
    4.2 because there is only one way to reach the first col,
       dp[i][0]=0 until matrix[i][j]=1

ps: third solution is most concise
*/
public class Solution {
    public int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for(int i = 0; i < col; i++){
            if(obstacleGrid[0][i] == 1){
                for(int j = i; j < col; j++){
                    dp[0][j] = 0;
                }
                break;
            }else{
                dp[0][i] = 1;
            }
        }

        for(int i = 0; i < row; i++){
            if(obstacleGrid[i][0] == 1){
                for(int j = i; j < row; j++){
                    dp[j][0] = 0;
                }
                break;
            }else{
                dp[i][0] = 1;
            }
        }

        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[row-1][col-1];

    }

    public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for(int i = 0; i < col; i++){
            if(obstacleGrid[0][i] == 1){
                break;
            }else{
                dp[0][i] = 1;
            }
        }

        for(int i = 0; i < row; i++){
            if(obstacleGrid[i][0] == 1){
                break;
            }else{
                dp[i][0] = 1;
            }
        }

        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[row-1][col-1];

    }

    public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        for(int i = 0; i < col; i++){
            if(obstacleGrid[0][i] == 1) break;
            dp[0][i] = 1;
        }

        for(int i = 0; i < row; i++){
            if(obstacleGrid[i][0] == 1) break;
            dp[i][0] = 1;
        }

        // dp[i][j] = dp[i-1][j] + dp[i][j-1]
        for(int i = 1; i < row; i++){
            for(int j = 1; j < col; j++){
                if(obstacleGrid[i][j] != 1){
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[row-1][col-1];

    }

    public static void main(String[] args) {

    }
}
