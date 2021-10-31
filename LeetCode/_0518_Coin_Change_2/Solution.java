package LeetCode._0518_Coin_Change_2;
/*

Solution:
1. dp[i][j] : the number of combinations to make up amount j by using the first i types of coins
2.  there are two case:
    2.1 don't use the i-th coin => dp[i - 1][j]: means the number of combinations if we don't use the ith coin
    2.2 use the i-th coin => dp[i][j - coins[i - 1]]: we must use at least one of the ith coin, so we expell the ith coin from j (Exclusive, opposite to the upper condition)

dp[i][j] =
   don't consider using first i-th coins, the number of combinations that sum up j
   +
   after considering using first i-th coins, the number of combinations that sum up j - ith coin
 */
public class Solution {

    public int change1(int amount, int[] coins) {
        int[][] dp = new int[coins.length+1][amount+1];
        dp[0][0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i-1][j] + (j >= coins[i-1] ? dp[i][j-coins[i-1]] : 0);
            }
        }
        return dp[coins.length][amount];
    }

    public int change2(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i-coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {


    }
}
