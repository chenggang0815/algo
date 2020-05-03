package com.leetcode._0036;
/*
67. Add Binary  Easy

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
public class Solution {
    /*
方法一：逐位计算
这是一种古老的经典算法，无需把数字转换成十进制，直接逐位计算和与进位即可。
初始进位 carry=0，如果数字 a 的最低位是 1，则将 1 加到进位 carry；同理如果数字 b 的最低位是 1，则也将 1 加到进位。此时最低位有三种情况：(00)2​，(01)2​或者 (10)2。
然后将 carry的最低位作为最低位的值，将 carry的最高位移至下一位继续计算。
重复上述步骤，直到数字 a 和 b的每一位计算完毕。最后如果 carry的最高位不为 0，则将最高位添加到计算结果的末尾。最后翻转结果得到求和结果。
作者：LeetCode
链接：https://leetcode-cn.com/problems/add-binary/solution/er-jin-zhi-qiu-he-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */

    //时间复杂度：O(max(length(a),length(b)))
    //空间复杂度：O(max(length(a),length(b)))

    static public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length()-1, j = b.length()-1, carry = 0;
        while (i>=0 || j>=0){
            int sum = carry;
            if (i>=0) sum += a.charAt(i--) - '0';
            if (j>=0) sum += a.charAt(j--) - '0';
            sb.append(sum%2);
            carry = sum/2;
        }

        if (carry!=0)sb.append(carry);
        return sb.reverse().toString();
    }


    public static void main(String[] args) {
        String a = "11", b = "1";

        System.out.println(addBinary(a,b));

    }
}
