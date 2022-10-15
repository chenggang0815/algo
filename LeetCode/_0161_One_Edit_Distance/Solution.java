package LeetCode._0161_One_Edit_Distance;
/*
161. One Edit Distance

Given two strings s and t, return true if they are both one edit distance apart, otherwise return false.

A string s is said to be one distance apart from a string t if you can:

Insert exactly one character into s to get t.
Delete exactly one character from s to get t.
Replace exactly one character of s with a different character to get t.

Example 1:
Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.

Example 2:
Input: s = "", t = ""
Output: false
Explanation: We cannot get t from s by only one step.
*/

/*
Solution:
1. check if two string are same, if they are same, return false
2. use two pointers to iterate the string, until s.charAt(i) != t.charAt(j)
    2.1 if s.length() > t.length() => which means we need to delete the i-th char of s, and compare s[i+1, s.length()] == t[j, t.length()]
    2.2 if s.length() > t.length() => which means we need to delete the j-th char of t, and compare s[i, s.length()] == t[j+1, t.length()]
    2.3 if s.length() == t.length() => which means we need to replace the i-th of s, so we can just compare s[i+1, s.length()] == t[j+1, t.length()]
*/
public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s.equals(t)) return false;

        int i = 0;
        int j = 0;
        int lenS = s.length();
        int lenT = t.length();
        while (i < lenS && j < lenT && s.charAt(i) == t.charAt(j)){
            i++;
            j++;
        }

        if (s.length() == t.length()) return s.substring(i + 1, lenS).equals(t.substring(j + 1, lenT));
        else if (s.length() > t.length()) return s.substring(i + 1, lenS).equals(t.substring(j, lenT));
        else return s.substring(i, lenS).equals(t.substring(j + 1, lenT));
    }

    public static void main(String[] args) {

    }
}
