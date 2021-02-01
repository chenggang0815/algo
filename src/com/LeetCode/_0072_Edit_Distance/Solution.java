package com.LeetCode._0072_Edit_Distance;
/*
72. 编辑距离

给你两个单词 word1 和 word2，请你计算出将 word1 转换成 word2 所使用的最少操作数

你可以对一个单词进行如下三种操作：
    1. 插入一个字符
    2. 删除一个字符
    3. 替换一个字符

word1 插入 dp[i, j] = dp[i, j-1] + 1
word1 删除 dp[i, j] = dp[i-1, j] + 1
word1 替换 dp[i, j] = dp[i-1, j-1] + 1

思路：动态规划
从最后一个字符考虑，假设源字符串为S，目标字符串为T，则S和T的最后一个字符 s[i] 和 t[j] 对应四种情况：
    1.字符--字符
    2.字符--空白
    3.空白--字符
    4.空白--空白
显然第四种情况是多余的编辑操作。下面来逐个分析以上3种情况。

第一种情况：

    S + 空白
    T + 字符X

S变成T，S => T 最后，在S的末尾插入“字符X”，若以dp矩阵来表示两个字符串之间的距离，则dp[i, j] = dp[i, j-1]+1。

第二种情况：

    S + 字符X
    T + 字符Y

S变成T，最后，把X修改成Y，如果X==Y，说明不需要操作变化，dp[i, j]=dp[i-1, j-1]，
否则，需要在原来的基础上加1，dp[i, j]=dp[i-1, j-1]+1。

第三种情况：

    S + 字符X
    T + 空白

S变成T，最后，X被删除。dp[i, j]=dp[i-1, j]+1。

 */
public class Solution {

    //time: o(n^2) space: o(2n)
    static int minDistance(String s1, String s2){
        int m = s1.length();
        int n = s2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++){
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++){
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++){
            for (int j = 1; j <= n; j++){
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else{
                    dp[i][j] = Math.min(dp[i - 1][j - 1] + 1, Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1));
                }
            }
        }

        return dp[m][n];
    }



    public static void main(String[] args) {
        System.out.println(minDistance("rad", "apple"));
    }
}
