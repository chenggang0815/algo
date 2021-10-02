package LeetCode._0877_Stone_Game;
/*
877. Stone Game Medium
Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].
The objective of the game is to end with the most stones.  The total number of stones is odd, so there are no ties.
Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of stones from either the beginning or the end of the row.  This continues until there are no more piles left, at which point the person with the most stones wins.
Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.

Example 1:
Input: [5,3,4,5]
Output: true
Explanation:
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.

Note:
    2 <= piles.length <= 500
    piles.length is even.
    1 <= piles[i] <= 500
    sum(piles) is odd.

 */
public class Solution {
    //动态规划
    /*
    设dp[i][j]表示从第i堆石头到第j堆石头，先手者比对手多出的石子数
    则有dp[i][j] = max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1])
    表示如果先手者拿第i或者j堆石头的两种情况：

    初始状态：j-i=0 => dp[0][0]= piles[0] ...dp[n-1][n-1] = piles[n-1]

    依次计算: j-i=1 => dp[0][1] dp[1][2] ... dp[n-2][n-1] (需要依赖j-i=0的dp值)
             j-i=2 => dp[0][2] dp[1][3] ... dp[n-3][n-1] （需要依赖j-i=1的dp值）
             ...
             j-i=n-1 => dp[0][n-1] （需要依赖j-i=n-1的dp值，也是我们需要求的目标值）

     */

    //time:o(n^2) space:o(n^2)
    static boolean stoneGame(int[] piles){
        int length = piles.length;
        int[][] dp = new int[length][length];

        for (int n = 0;n<length;n++){
            dp[n][n] = piles[n];
        }

        //根据j-i=0的初始状态算出j-i=n-1的目标值
        for (int j=1;j<length;j++){
            for (int i=0;i<length-j;i++){
                dp[i][i+j] = Math.max(piles[i]-dp[i+1][i+j],piles[i+j]-dp[i][i+j-1]);
            }
        }

        System.out.println(dp[0][length-1]);

        return dp[0][length-1]>0;

    }

    //空间复杂度优化。画图:
    /*
     i   0            1          2      4
     j 0 dp[i][j-1]  dp[i][j]
       1             dp[i+1][j]
       2
       3
     可以发现dp[i][j]依赖的两个值是斜线上的值，可以推出只需要o(n)的空间保留斜线上的值即可求得目标值
     */
    static boolean stoneGame2(int[] piles){
        int length = piles.length;
        int[] dp = new int[length];
        for (int n=0;n<length;n++){
            dp[n] = piles[n];
        }
        /*
        for j in range(1,len(piles)):
        for i in range(j-1,-1,-1):
        dp[i]=max(piles[i]-dp[i+1],piles[j]-dp[i])
*/

//        for (int j=1;j<length;j++){
//            for (int i=j-1;i>-1;i--){
//                dp[i] = Math.max(piles[i]-dp[i+1],piles[j]-dp[i]);
//            }
//        }

        for(int j=2;j<=length;j++)
            for(int i=0;i<length-j+1;i++)
                dp[i] = Math.max(piles[i]-dp[i+1],piles[i+j-1]-dp[i]);

        System.out.println(dp[0]);

        return dp[0]>0;
    }
    public static void main(String[] args) {
        int[] piles = new int[] {5,1,4,5};
        System.out.println(stoneGame2(piles));


    }
}
