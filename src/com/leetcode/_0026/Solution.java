package com.leetcode._0026;

import java.util.ArrayList;

/*
9. Palindrome Number Easy

Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:  Input: 121  Output: true
Example 2:  Input: -121 Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:  Input: 10   Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.

Follow up: Coud you solve it without converting the integer to a string?
*/
public class Solution {
    static private boolean isPalindrome(int x){
     if(x<0) return false;
     ArrayList<Integer> array = new ArrayList<>();
     while (x!=0){
         array.add(x%10);
         x = x /10;
     }
     System.out.println(array);
     return true;
    }
    public static void main(String[] args) {
        int x = 1221;
        isPalindrome(x);
    }
}
