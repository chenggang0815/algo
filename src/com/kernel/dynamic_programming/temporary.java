package com.kernel.dynamic_programming;
/*
每日复习专用
 */
public class temporary {

    /*
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

    /*
    dp[i][j] = max(nums[i]-dp[i+1][j],nums[j]-dp[i][j-1])
    dp[0][1] dp[1][2] ... dp[n-2][n-1]
    dp[0][2]              dp[n-3][n-1]
    return dp[0][n-1] >0
     */
    static boolean stonegame(int[] nums){
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int n=0;n<length;n++){
            dp[n][n] = nums[n];
        }

        for (int j=1;j<length;j++){
            for (int i=0;i<length-j;i++){
                dp[i][i+j] = Math.max(nums[i]-dp[i+1][i+j],nums[i+j]-dp[i][i+j-1]);
            }
        }

        System.out.println(dp[0][length-1]);

        return dp[0][length-1]>0;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{5,2,4,5};
        System.out.println(stonegame(nums));


    }
}
