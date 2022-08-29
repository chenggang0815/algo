package LeetCode._1347_Minimum_Number_of_Steps_to_Make_Two_Strings_Anagram;

import java.util.HashMap;

/*
1347. Minimum Number of Steps to Make Two Strings Anagram

You are given two strings of the same length s and t. In one step you can choose any character of t and replace it with another character.
Return the minimum number of steps to make t an anagram of s.
An Anagram of a string is a string that contains the same characters with a different (or the same) ordering.

Example 1:
Input: s = "bab", t = "aba"
Output: 1
Explanation: Replace the first 'a' in t with b, t = "bba" which is anagram of s.

Example 2:
Input: s = "leetcode", t = "practice"
Output: 5
Explanation: Replace 'p', 'r', 'a', 'i' and 'c' from t with proper characters to make t anagram of s.
*/
/*
Solution
Approach 1: hash

Approach 2
*/
public class Solution {
    // approach 1
    public int minSteps1(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(char ch: s.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // l:1 e:3 t:1 c:1 d:1
        // b:2 a:1
        int res = 0;
        for(char ch: t.toCharArray()){
            if(map.containsKey(ch)){
                if(map.get(ch) - 1 == 0){
                    map.remove(ch);
                }else{
                    map.put(ch, map.get(ch) - 1);
                }
            }else{
                res++;
            }
        }

        return res;
    }

    // approach 2
    public int minSteps2(String s, String t) {
        int[] cnt = new int[26];
        // b:2 a:1
        // a:2 b:1
        for(int i = 0; i < s.length(); i++){
            cnt[s.charAt(i) - 'a']++;
            cnt[t.charAt(i) - 'a']--;
        }

        int res = 0;
        for(int i = 0; i < cnt.length; i++){
            if(cnt[i] < 0) res -= cnt[i];
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
