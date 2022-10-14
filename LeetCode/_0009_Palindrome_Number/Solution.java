package LeetCode._0009_Palindrome_Number;
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
    public boolean isPalindrome1(int x) {
        //121
        String s = String.valueOf(x);
        for(int i = 0; i < s.length() / 2; i++){
            if(s.charAt(i) != s.charAt(s.length() - i - 1)){
                return false;
            }
        }

        return true;
    }

    // time complexity: O(log10(n))
    // space complexity: O(n)
    static public boolean isPalindrome2(int x) {
        if(x < 0) return false;

        ArrayList<Integer> arr = new ArrayList<>();
        while(x != 0){
            arr.add(x % 10);
            x /= 10;
        }

        for(int i = 0; i < arr.size() / 2; i++){
            if(arr.get(i) != arr.get(arr.size() - i - 1)){
                return false;
            }
        }

        return true;
    }

    // time complexity: O(log10(n))
    // space complexity: O(1)
    /*
    x = 1221
    temp=1 x=122
    temp=12 x=12 => x=temp

    x = 12121
    temp=1 x=1212
    temp=12 x=121
    temp=121 x=12 => x=temp/10

    x = 10
    temp=0 x=1
    temp=10 x=0 => x==temp/10, so if the last digit is 0, we need return false
    * */
    // 2022-10-14
    // 1234321
    // 123321
    public boolean isPalindrome(int x) {
        if(x < 0) return false;
        // 10, 100, 1000 =>lastHalf=10 x=0
        if(x % 10 == 0 && x != 0) return false;

        int lastHalf = 0;
        while(x > lastHalf){
            int digit = x % 10;
            lastHalf = lastHalf * 10 + digit;
            x = x / 10;
        }

        return lastHalf == x || lastHalf/10 == x;
    }

    public static void main(String[] args) {
        int x = 10;
        System.out.println(isPalindrome2(x));
    }
}
