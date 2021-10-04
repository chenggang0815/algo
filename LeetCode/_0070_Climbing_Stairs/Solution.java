package LeetCode._0070_Climbing_Stairs;
/*
Climbing Stairs 爬楼梯
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps

Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step
 */
public class Solution {

    //time: o(n) space: o(n)
    public static int climbStairs(int n){
        if (n <= 2) return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n - 1];
    }
    // time: o(n) space: o(1)
    public static int climbStairs2(int n) {
        if (n <= 2) return n;

        int first = 1;
        int second = 2;
        int res = 0;
        for (int i = 2; i < n; i++){
            res = first + second;
            first = second;
            second = res;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(climbStairs2(40));
    }
}
