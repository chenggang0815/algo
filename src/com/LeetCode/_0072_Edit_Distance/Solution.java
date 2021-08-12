package com.LeetCode._0072_Edit_Distance;
/*
72. Edit Distance
Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
You have the following three operations permitted on a word:
* Insert a character
* Delete a character
* Replace a character

Example 1:
Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation:
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

思路：动态规划
dp(i, j) => minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2
从最后一个字符考虑，假设源字符串为S，目标字符串为T，则S和T的最后一个字符s[i]和t[j]对应四种情况：
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
    //为什么dp矩阵的大小为new int[m + 1][n + 1]?
    //因为dp[i][j]表示word1字符串的前i个字符转换到word2字符串的前j的字符所需要的最少步骤
    //所以最后答案为dp[word1.length][word2.length]
    //并且在判断第i个和第j个字符是否相等的时候是 => if (s1.charAt(i - 1) == s2.charAt(j - 1)) 因为charAt(i - 1)表示的就是第i个字符（下标从0开始）
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
                if (s1.charAt(i - 1) == s2.charAt(j - 1)){ // 表示的是s1的第i个字符和s2的第j个字符，下标从0开始
                    // dp(i, j) := minimum cost (or steps) required to convert first i characters of word1 to first j characters of word2
                    dp[i][j] = dp[i - 1][j - 1]; //表示的是s1的前i个字符转换到s2的前j个字符所需要的最小步骤
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
