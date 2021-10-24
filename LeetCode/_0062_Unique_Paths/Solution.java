package LeetCode._0062_Unique_Paths;
/*
62. Unique Paths

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
How many possible unique paths are there?

*/
/*
Solution:
Approach 1: DFS  time limit exceeded

Approach 2: Dynamic Programing time:O(m*n) space:o(m*n)
1. because robot can only move either down or right
2. for a specific position (i,j), there are only two way to reach it, (i-1, j) and (i, j-1)
3. dp[i][j] = dp[i-1][j] + dp[i][j-1]
4. dp[0][0]...dp[row][0]=1 => the first row is always is 1
5. dp[0][0]...dp[0][col]=1 => the first col is always is 1
*/
public class Solution {
    public int uniquePaths1(int m, int n) {
        int[] res = new int[1];

        dfs(0, 0, m, n, res);

        return res[0];
    }

    void dfs(int i,int j, int row, int col, int[] res){
        if(i > row - 1 || j > col - 1) return;

        if(i == row - 1 && j == col - 1) res[0]++;
        dfs(i + 1, j, row, col, res);
        dfs(i, j + 1, row, col, res);
    }

    public int uniquePaths2(int m, int n) {
        int res = 0;
        int[][] dp = new int[m][n];
        for(int i = 0; i < m; i++) dp[i][0] = 1;
        for(int i = 0; i < n; i++) dp[0][i] = 1;

        for(int i = 1; i < m; i++){
            for(int j = 1; j < n; j++){
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m-1][n-1];
    }

    public static void main(String[] args) {

    }
}
