package LeetCode._0010_Regular_Expression_Matching;
/*
10. Regular Expression Matching

Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
    '.' Matches any single character.​​​​
    '*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

Example 1:
Input: s = "aa", p = "a"
Output: false
Explanation: "a" does not match the entire string "aa".

Example 2:
Input: s = "aa", p = "a*"
Output: true
Explanation: '*' means zero or more of the preceding element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".

Example 3:
Input: s = "ab", p = ".*"
Output: true
Explanation: ".*" means "zero or more (*) of any character (.)".

Example 4:
Input: s = "aab", p = "c*a*b"
Output: true
Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore, it matches "aab".

Example 5:
Input: s = "mississippi", p = "mis*is*p*."
Output: false
*/

/*
动态规划：time:o(m*n) space:o(m*n)

dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配

所以需要比较s[i-1] 和 p[j-1]的情况：
1. s[i-1] == p[j-1] || p[j-1] == '.' => s[i-1]和p[j-1]可以匹配 => dp[i][j] = dp[i-1][j-1]

2. p[j - 1] == '*'
    2.1 模式串*的前一个字符能够跟文本串的末位匹配上
        2.1.1 dp[i][j] = dp[i][j - 2] // *匹配0次的情况
        2.1.2 dp[i][j] = dp[i - 1][j]; // *匹配1次或多次的情况
    2.2 模式串*的前一个字符不能够跟文本串的末位匹配
        2.2.1 dp[i][j] = dp[i][j - 2];  // *只能匹配0次

3. 其他情况 => 不能匹配 => 不用处理dp[i][j]，默认为false

ps:需要特别理解的是2.1.2情况

参考剑指offer 52题正则表达式匹配
*/
public class Solution {
    static boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        // dp[i][j]:表示s的前i个字符，p的前j个字符是否能够匹配
        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1]; //默认为false
        // s为空，p为空，能匹配上
        dp[0][0] = true;
        // p为空，s不为空，必为false(boolean数组默认值为false，无需处理)
        // s为空，p不为空，由于*可以匹配0个字符，所以有可能为true
        for (int j = 1; j <= p.length(); j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= s.length(); i++){
            for (int j = 1; j <= p.length(); j++){
                if (p.charAt(j - 1) == '*'){//该位置为*
                    if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.'){ // 模式串*的前一个字符能够跟文本串的末位匹配上
                        dp[i][j] = dp[i][j - 2] // *匹配0次的情况
                                    || dp[i - 1][j]; // *匹配1次或多次的情况
                    }else{// 模式串*的前一个字符不能够跟文本串的末位匹配
                        dp[i][j] = dp[i][j - 2];     // *只能匹配0次
                    }
                }else if (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.'){ //这个位置可以匹配
                        dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }

        return dp[s.length()][p.length()];

    }


    public static void main(String[] args) {
        System.out.println(isMatch("aa","a*"));
    }
}
