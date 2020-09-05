package com.LeetCode._0009_回文数;
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

    // time complexity: O(log10(n))
    // space complexity: O(n)
    static public boolean isPalindrome(int x){
     if(x<0) return false;
     ArrayList<Integer> array = new ArrayList<>();
     while (x!=0){
         array.add(x%10);
         x = x /10;
     }
     for (int i=0;i<array.size()/2;i++){
         if (array.get(i)!=array.get(array.size()-i-1)){
             return false;
         }
     }
     System.out.println(array);
     return true;
    }

    // time complexity: O(log10(n))
    // space complexity: O(1)
    static public boolean isPalindrome2(int x){
        if (x<0 || x%10==0&&x!=0) return false;
        int temp = 0;
        while (x>temp){
            temp = temp*10 + x % 10;
            x = x / 10;
        }
        System.out.println("x: "+x);
        System.out.println("temp: "+temp);
        return x==temp || x==temp/10;
    }

    public static void main(String[] args) {
        int x = 10;
        System.out.println(isPalindrome2(x));
    }
}
