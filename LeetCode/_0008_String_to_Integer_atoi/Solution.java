package LeetCode._0008_String_to_Integer_atoi;
/*
8. String to Integer (atoi)

Implement the myAtoi(string s) function,
which converts a string to a 32-bit signed integer (similar to C/C++'s atoi function).
 */
public class Solution {

    static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            idx++; // 去掉最前面的空格
        }
        if (idx == n) return 0; //去掉前导空格以后到了末尾了

        boolean negative = false;
        if (chars[idx] == '-') { //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') idx++;
          else if (!Character.isDigit(chars[idx])) return 0;

        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative ? -ans : ans;
    }

    public static void main(String[] args) {
        System.out.println(myAtoi("-+12"));
    }
}
