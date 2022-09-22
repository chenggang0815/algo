package LeetCode._0424_Longest_Repeating_Character_Replacement;
/*
424. Longest Repeating Character Replacement

You are given a string s and an integer k.
You can choose any character of the string and change it to any other uppercase English character.
You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:
Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.

Example 2:
Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.

Constraints:
1 <= s.length <= 105
s consists of only uppercase English letters.
0 <= k <= s.length
*/
/*
Solution:
Approach 1: two pointer - sliding window
1. maintain a window, make sure this can change no more than k characters to become a valid string
2. if the length of current window - the number of most frequent character > k, which means it is not a valid window
3. we need to contract the window and update frequency
4. else it is a valid window, we can expend the window and update frequency
https://leetcode.com/problems/longest-repeating-character-replacement/discuss/358879/Java-Solution-Explained-and-Easy-to-Understand-for-Interviews
*/
public class Solution {
    int characterReplacement(String s, int k){
        int[] cnt = new int[26];
        int left = 0;
        int maxFreq = 1;
        int res = 1;
        for(int i = 0; i < s.length(); i++){
            cnt[s.charAt(i) - 'A']++;
            maxFreq = Math.max(maxFreq, cnt[s.charAt(i) - 'A']);
            // length = 5 maxFreq = 3 2 >k
            if(i - left + 1 - maxFreq > k){
                cnt[s.charAt(left) - 'A']--;
                left++;
            }
            res = Math.max(res, i - left + 1);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
