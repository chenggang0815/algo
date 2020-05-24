package com.nowcoder._036;
/*
求出任意非负整数区间中1出现的次数（从1 到 n 中1出现的次数）
 */
public class Solution {
    /*
    依次遍历每个数字
     */
    //time: o(n^2) space: o(n)
    static int NumberOf1Between1AndN_Solution(int n)
    {
        if (n==1) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i=1; i<n; i++){
            dp[i] = dp[i-1] + number(i);
        }

        return dp[n-1];

    }

    static int number(int n){
        String str = String.valueOf(n);
        int count = 0;
        for (int i=0; i<str.length(); i++){
            if (str.charAt(i) == '1'){
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println(NumberOf1Between1AndN_Solution(13));

    }
}
