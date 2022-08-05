package LeetCode._0322_Coin_Change;

import java.util.ArrayList;
import java.util.Arrays;

/*
322. Coin Change

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin.

Example 1:
Input: coins = [1,2,5], amount = 11
Output: 3
Explanation: 11 = 5 + 5 + 1

Example 2:
Input: coins = [2], amount = 3
Output: -1
*/

/*
思路1： dfs+剪枝
https://leetcode-cn.com/problems/coin-change/solution/322-by-ikaruga/

思路2： 动态规划
时间复杂度：O(Sn)，其中S是金额，n是面额数。我们一共需要计算O(S)个状态，S为题目所给的总金额。对于每个状态，每次需要枚举n个面额来转移状态
空间复杂度：O(S)

2.1 采用自下而上的方式，定义F(i)为组成金额i所需最少的硬币数量，假设在计算F(i)之前，我们已经计算出F(0)至F(i−1)的值
2.2 则F(i)对应的转移方程应为：
    F(i)= min F(i−c_j)+1 其中c_j代表的是第j枚硬币的面值

eg：coins = [1, 2, 5], amount = 6
F(0) = 0
F(1) = min(F(1−1),F(1−2),F(1−5))+1 = F(1−1) + 1 = 1
F(2) = min(F(2−1),F(2−2),F(2−5))+1 = min(F(1),F(0)) + 1 = 1
F(3) = min(F(3−1),F(3−2),F(3−5))+1 = min(F(2),F(1)) + 1 = 2
F(4) = min(F(4−1),F(4−2),F(4−5))+1 = min(F(3),F(2)) + 1 = 2
F(5) = min(F(5−1),F(5−2),F(5−5))+1 = min(F(4),F(3),F(0)) + 1 = 1
F(6) = min(F(6−1),F(6−2),F(6−5))+1 = min(F(5),F(4),F(1)) + 1 = 2
 */
public class Solution {
    //dfs + 剪枝
    static int res = Integer.MAX_VALUE;

    static void dfs(int[] coins, int amount, int cnt, int index){
        if (amount == 0){
            res = Math.min(res, cnt);
            return;
        }
        if(index < 0 ) return;
        for (int i = amount / coins[index]; i >= 0 && i + cnt < res; i--){
            dfs(coins, amount - (i * coins[index]), cnt + i, index - 1);
        }
    }

    static int coinChange1(int[] coins, int amount) {
        Arrays.sort(coins);
        dfs(coins, amount, 0, coins.length - 1);
        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // 动态规划
    static int coinChange2(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); //如果不初始化 => dp[i]=0 => dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1)  => dp[i]将一直是0
        dp[0] = 0;
        for (int i = 1; i <= amount; i++){
            for (int j = 0; j < coins.length; j++){
                if (i >= coins[j]){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange1(new int[]{2}, 3));
    }
}
