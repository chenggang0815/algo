package LeetCode._0344_Reverse_String;
/*
344. Reverse String

Write a function that reverses a string. The input string is given as an array of characters char[].
Do not allocate extra space for another array, you must do this by modifying the input array in-place with O(1) extra memory.
You may assume all the characters consist of printable ascii characters.

Example 1:
Input: ["h","e","l","l","o"]
Output: ["o","l","l","e","h"]
 */

public class Solution {
    static void reverseString1(char[] s){
        int i = 0;
        int j = s.length - 1;
        while (i < j){
            char left = s[i];
            s[i] = s[j];
            s[j] = left;
            i++;
            j--;
        }

    }
    public static void main(String[] args) {
        char[] s = new char[]{'h','e','l','l','o'};
        reverseString1(s);
        System.out.println(s);
    }
}
