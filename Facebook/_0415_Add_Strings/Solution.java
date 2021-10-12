package Facebook._0415_Add_Strings;
/*
415. Add Strings

Given two non-negative integers, num1 and num2 represented as string, return the sum of num1 and num2 as a string.
You must solve the problem without using any built-in library for handling large integers (such as BigInteger).
You must also not convert the inputs to integers directly.

Example 1:
Input: num1 = "11", num2 = "123"
Output: "134"

Example 2:
Input: num1 = "456", num2 = "77"
Output: "533"

Example 3:
Input: num1 = "0", num2 = "0"
Output: "0"

Constraints:
1. 1 <= num1.length, num2.length <= 104
2. num1 and num2 consist of only digits.
3. num1 and num2 don't have any leading zeros except for the zero itself.
 */

/*
Solution:
num1: 1   2    3
num2: 1   2    3
             sum=3+3+carry
             sb.append(sum % 10)
             carry = sum / 10

i=num1.length()-1 j=num2.length()-1
carry=0 sum=0 sb=""

while(i >= 0 || j >=0 || carry == 1)
edge case: num1= 199 num2=99 => i>=0
           num1=99 num2=199 => j>=0
           num1=9 num2=0  => carry ==1



*/
public class Solution {
    static String addString(String num1, String num2){
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;
        StringBuilder sb = new StringBuilder();
        while (i >= 0 || j >=0 || carry == 1){
            int digit1 = i < 0 ? 0: num1.charAt(i) - '0';
            int digit2 = j < 0 ? 0: num2.charAt(j) - '0';
            int sum = digit1 + digit2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }

        //if (i >= 0) sb.append(num1.substring(0, i + 1));
        //if (j >= 0) sb.append(num2.substring(0, j + 1));

        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(addString("199", "99"));
    }
}
