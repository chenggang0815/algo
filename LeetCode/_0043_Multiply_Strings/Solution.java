package LeetCode._0043_Multiply_Strings;
/*
43. Multiply Strings

Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2, also represented as a string.
Note: You must not use any built-in BigInteger library or convert the inputs to integer directly.

Example 1:
Input: num1 = "2", num2 = "3"
Output: "6"

Example 2:
Input: num1 = "123", num2 = "456"
Output: "56088"

Constraints:
1 <= num1.length, num2.length <= 200
num1 and num2 consist of digits only.
Both num1 and num2 do not contain any leading zero, except the number 0 itself.
 */
import java.util.Arrays;

/*
Approach 1:
        1 2 3 => index(0,1,2)
          4 5 => index(0,1)
-------------------
          1 5
        1 0
      0 5
        1 2
      0 8
    0 4
--------------------
    0 1 2 3 4 => index
=> 3*5 => i=2 * j=1 => [i+j, i+j+1] => [3, 4] => 1 5

Approach 2:

*/
public class Solution {
    static String multiply(String num1, String num2){
        int m = num1.length();
        int n = num2.length();
        int[] nums = new int[m + n];
        for (int i = m - 1; i >= 0; i--){
            for (int j = n - 1; j >= 0; j--){
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                int p1 = i + j;
                int p2 = i + j + 1;
                int sum = mul + nums[p2];
                nums[p1] += sum / 10;
                nums[p2] = sum % 10;
            }
        }

       // System.out.println(Arrays.toString(nums));
        StringBuilder sb = new StringBuilder();
        for (int num: nums){
            if (sb.length() == 0 && num == 0) continue;
            sb.append(num);
        }

        return sb.length() == 0 ? "0" : sb.toString();
    }

    // https://leetcode-cn.com/problems/multiply-strings/solution/zi-fu-chuan-xiang-cheng-by-leetcode-solution/
    public String multiply2(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        String ans = "0";
        int m = num1.length(), n = num2.length();
        for (int i = n - 1; i >= 0; i--) {
            StringBuffer curr = new StringBuffer();
            int add = 0;
            for (int j = n - 1; j > i; j--) {
                curr.append(0);
            }
            int y = num2.charAt(i) - '0';
            for (int j = m - 1; j >= 0; j--) {
                int x = num1.charAt(j) - '0';
                int product = x * y + add;
                curr.append(product % 10);
                add = product / 10;
            }
            if (add != 0) {
                curr.append(add % 10);
            }
            ans = addStrings(ans, curr.reverse().toString());
        }
        return ans;
    }

    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1, j = num2.length() - 1, add = 0;
        StringBuffer ans = new StringBuffer();
        while (i >= 0 || j >= 0 || add != 0) {
            int x = i >= 0 ? num1.charAt(i) - '0' : 0;
            int y = j >= 0 ? num2.charAt(j) - '0' : 0;
            int result = x + y + add;
            ans.append(result % 10);
            add = result / 10;
            i--;
            j--;
        }
        ans.reverse();
        return ans.toString();
    }


    public static void main(String[] args) {
        System.out.println(multiply("12", "12"));
    }
}
