package LeetCode._0139_Word_Break;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
139. Word Break
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:
The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.

Example 1:
Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".

Example 2:
Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
             Note that you are allowed to reuse a dictionary word.

Example 3:
Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false
 */

/*
Solution:

Approach 1: Recursion - Brute Force
                                wordDict=[l ,ee, t]
                                      wb(leet)
                      /                 \                 \            \
             l&(wb(eet))                le&wb(et)       lee&wb(t)    leetwb()
           /      |       \             /       \            \
   le&(wb(et)) lee&wb(t) leet&wb()    lee&wb(t)  leet&wb()   leet&wb()
        /    \        \                 /
   lee&wb(t) leet&wb() leet&wb()    leet&wb()
     /                  return true;
  leet&wb()

n = s.length()=4 => time:O(2^4)=16


Approach 2: Recursion - memoization  => time:O(n^3)=16

for above example, we call the two times wb(eb), so if we can remember from index = 3 we can just return false;

Approach 3: Dynamic Programing
https://leetcode.com/problems/word-break/solution/
*/
public class Solution {
    static boolean backtrack1(String s, List<String> wordDict){
        if (s.length() == 0){
            return true;
        }
        for (int i = 0; i < s.length(); i++){
            String word = s.substring(0, i + 1);
            if (wordDict.contains(word)){
              //  backtrack(s.substring(i + 1), wordDict) 错误
                if (backtrack1(s.substring(i + 1), wordDict)) return true; // 正确，有啥区别？ 参考79题
            }
        }

        return false;
    }

    static boolean wordBreak1(String s, List<String> wordDict) {
        return backtrack1(s, wordDict);
    }

    static boolean backtrack2(String s, List<String> wordDict, int[] flag, int startIndex){
        if (s.length() == 0){
            return true;
        }
        if (flag[startIndex] == 1) return false; //回溯剪枝，表示s.substring(0，startIndex)的这部分字符串都不能通过，可以不用在向后对比了

        for (int i = 0; i < s.length(); i++){
            String word = s.substring(0, i + 1);
            if (wordDict.contains(word)){
                //  backtrack(s.substring(i + 1), wordDict) 错误
                if (backtrack2(s.substring(i + 1), wordDict, flag, startIndex + i + 1)) return true; // 正确，有啥区别？ 参考79题
            }
        }

        flag[startIndex] = 1;

        return false;
    }

    static boolean wordBreak2(String s, List<String> wordDict) {
        int[] flag = new int[s.length()];
        return backtrack2(s, wordDict, flag, 0);
    }


    static boolean wordBreak3(String s, List<String> wordDict) {
        HashMap<String, String> map = new HashMap<>();
        for (String item : wordDict){
            map.put(item, item);
        }

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for (int i = 1; i <= s.length(); i++){
            for (int j = 0; j < i; j++){
                if (map.containsKey(s.substring(j, i)) && dp[j]){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }


    public static void main(String[] args) {
        List<String> wordDict = new ArrayList<>();
        wordDict.add("aaaa");
        wordDict.add("aaa");
     //    wordDict.add("leet");
     //    wordDict.add("code");
        System.out.println(wordBreak3("aaaaaaa",  wordDict));

    }
}
