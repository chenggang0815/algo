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
/*
Solution
Approach1: in-place time:O(n) space:O(n)
1. Does in-place mean constant space complexity?
    No. By definition, an in-place algorithm is an algorithm which transforms input using no auxiliary data structure.
    The tricky part is that space is used by many actors, not only by data structures. The classical example is to use recursive function without any auxiliary data structures.
2. Is it in-place?
    Yes.
Approach2: two-pointer time:O(n) space:O(1)
*/
public class Solution {
    static void reverseString1(char[] s){
        reverse(s, 0, s.length - 1);
    }
    static void reverse(char[] s, int left, int right){
        if (left >= right) return;
        char c = s[left];
        s[left] = s[right];
        s[right] = c;
        left++;
        right--;

        reverse(s, left, right);
    }
    static void reverseString2(char[] s){
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
