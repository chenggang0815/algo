package com.LeetCode._0131_Palindrome_Partitioning;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.
Return all possible palindrome partitioning of s.
A palindrome string is a string that reads the same backward as forward.

Example 1:
Input: s = "aab"
Output: [["a","a","b"],["aa","b"]]

Example 2:
Input: s = "a"
Output: [["a"]]

Constraints:
1 <= s.length <= 16
s contains only lowercase English letters.
*/
/*
思路1：回溯算法
看到题目要求「所有可能的结果」，而不是「结果的个数」，一般情况下，我们就知道需要暴力搜索所有的可行解了，可以用「回溯法」

思路2：回溯优化（使用动态规划得到所有子串是否是回文
 */
public class Solution {
    static List<List<String>> partition(String s) {
        int length = s.length();
        List<List<String>> res = new ArrayList<List<String>>();
        List<String> ans = new ArrayList<String>();

        boolean[][] f = new boolean[length][length];
        for (int i = 0; i < length; i++){
            Arrays.fill(f[i], true);
        }

        for (int i = length - 1; i >= 0; i--){
            for (int j = i + 1; j < length; j++){
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0, res, ans, f);

        return res;
    }

    static void dfs(String s, int i, List<List<String>> res, List<String> ans, boolean[][] f) {
        if (i == s.length()) { // 一条深度路径走到头了，尝试收集答案
            res.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < s.length(); ++j) {
            if (f[i][j]) { // s[i ... j]是回文串，可以继续这条dfs路径
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1, res, ans, f);
                ans.remove(ans.size() - 1);
            }
        }
    }


    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }
}
