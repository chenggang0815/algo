package LeetCode._0029_Divide_Two_Integers;

/*
29. Divide Two Integers

Given two integers dividend and divisor,
divide two integers without using multiplication, division, and mod operator.

Return the quotient after dividing dividend by divisor.
The integer division should truncate toward zero, which means losing its fractional part. For example, truncate(8.345) = 8 and truncate(-2.7335) = -2.

Note: Assume we are dealing with an environment that could only store integers within the 32-bit signed integer range:
[−2^31, 2^31 − 1]. For this problem, assume that your function returns 2^31 − 1 when the division result overflows.

Example 1:
Input: dividend = 10, divisor = 3
Output: 3
Explanation: 10/3 = truncate(3.33333..) = 3.

Example 2:
Input: dividend = 7, divisor = -3
Output: -2
Explanation: 7/-3 = truncate(-2.33333..) = -2.

Example 3:
Input: dividend = 0, divisor = 1
Output: 0

Example 4:
Input: dividend = 1, divisor = 1
Output: 1
*/

/*
Java int类的最大值(Integer.MAX_VALUE) = 2^31 - 1 = 2147483648 - 1 = 2147483647
     int类的最小值(Integer.MIN_VALUE) = -2^31 = Integer.MAX_VALUE + 1 = -2147483648
 */
public class Solution {
/*
https://leetcode.com/problems/divide-two-integers/discuss/142849/C%2B%2BJavaPython-Should-Not-Use-%22long%22-Int

思路： time:o(log(n))
100/3
100>3 100>6 100>12 100>24 100>48 100>96 100<192 ---- 使用了 2^5 = 32 个3，还剩 100 - 96 = 4 需要被除
4>3 4<6                                         ---- 使用了 2^0 = 1  个3，还剩 4   - 3  = 1 需要被除
1<3                                             ---- 被除数小于除数，递归结束，总计使用了 33 个 3
*/
    static int divide(int dividend, int divisor) {
        if (Integer.MIN_VALUE == dividend && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        int a = Math.abs(dividend);
        int b = Math.abs(divisor);
        while (a - b >= 0) {
            int tmp = b;
            int count = 1;
            while (a - (tmp << 1) >= 0) {
                tmp <<= 1;
                count <<= 1;
            }
            a -= tmp;
            res += count;
        }
        return (dividend > 0) == (divisor > 0) ? res : -res;
    }


    public static void main(String[] args) {
        System.out.println(divide(100, 3));
    }
}
