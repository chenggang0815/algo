package LeetCode._0067_Add_Binary;
/*
二进制求和
67. Add Binary Easy
Given two binary strings, return their sum (also a binary string).
The input strings are both non-empty and contains only characters 1 or 0.

Example 1:
Input: a = "11", b = "1"
Output: "100"

Example 2:
Input: a = "1010", b = "1011"
Output: "10101"

Constraints:
    Each string consists only of '0' or '1' characters.
    1 <= a.length, b.length <= 10^4
    Each string is either "0" or doesn't contain any leading zero.
 */
/*
Approach 1
1. add two digits from the last digit to the first digit (from right to left)
2. use an variable 'carry' to record the carry
3. don't forget append carry at last, if carry not equal 0
*/
public class Solution {
    static public String addBinary(String a, String b) {
        StringBuilder s = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0){
            int num1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int num2 = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = num1 + num2 + carry;
            s.append(sum % 2);
            carry = sum / 2;
            i--;
            j--;
        }

        return carry == 0 ? s.reverse().toString() : s.append(carry).reverse().toString();
    }

    public static void main(String[] args) {
        String a = "11", b = "1";
        addBinary(a,b);
    }
}
