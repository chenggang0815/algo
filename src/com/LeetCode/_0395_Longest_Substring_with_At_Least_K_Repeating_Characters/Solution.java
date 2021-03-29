package com.LeetCode._0395_Longest_Substring_with_At_Least_K_Repeating_Characters;

import java.util.HashMap;

/*
395. Longest Substring with At Least K Repeating Characters

Given a string s and an integer k, return the length of the longest substring of s such that the frequency of each character in this substring is greater than or equal to k.

Example 1:
Input: s = "aaabb", k = 3
Output: 3
Explanation: The longest substring is "aaa", as 'a' is repeated 3 times.

Example 2:
Input: s = "ababbc", k = 2
Output: 5
Explanation: The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.

Constraints:
1 <= s.length <= 104
s consists of only lowercase English letters.
1 <= k <= 105
*/

/*
思路1: 分治算法 - 递归实现 time:o(N*26) N为字符串的长度 space:o(26*26)递归的深度为26，每次递归又需要26的额外空间
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
1. 递归最基本的是记住递归函数的含义（务必牢记函数定义）：本题的 longestSubstring(s, k) 函数表示的就是题意，即求一个最长的子字符串的长度，该子字符串中每个字符出现的次数都最少为k
2. 函数入参s是表示源字符串；k是限制条件，即子字符串中每个字符最少出现的次数；函数返回结果是满足题意的最长子字符串长度
3. 递归的终止条件（能直接写出的最简单 case）：如果字符串s的长度少于 kk，那么一定不存在满足题意的子字符串，返回0
4. 调用递归（重点）：如果一个字符c在s中出现的次数少于k次，那么s中所有的包含c的子字符串都不能满足题意。
5. 所以，应该在s的所有不包含c的子字符串中继续寻找结果 => 把s按照c分割（分割后每个子串都不包含c），得到很多子字符串t
6. 下一步要求t作为源字符串的时候，它的最长的满足题意的子字符串长度（到现在为止，我们把大问题分割为了小问题(s → t))
7. 此时我们发现，恰好已经定义了函数longestSubstring(s, k)就是来解决这个问题的！所以直接把 longestSubstring(s, k) 函数拿来用，于是形成了递归
8. 未进入递归时的返回结果：如果s中的每个字符出现的次数都大于k次 => 那么s就是我们要求的字符串，直接返回该字符串的长度 => return s.length

作者：fuxuemingzhu
链接：https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/jie-ben-ti-bang-zhu-da-jia-li-jie-di-gui-obla/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

思路2: 滑动窗口

https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/xiang-jie-mei-ju-shuang-zhi-zhen-jie-fa-50ri1/
 */
public class Solution {
    static int longestSubstring(String s, int k) {
        if (s.length() < k) return 0;

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }

        for (Character ch : map.keySet()){
            if (map.get(ch) < k){
                int res = 0;
                for (String str: s.split(String.valueOf(ch))){
                    res = Math.max(res, longestSubstring(str, k));
                }

                return res;
            }
        }

        return s.length();
    }

    public static void main(String[] args) {

    }
}
