package LeetCode._0007_Reverse_Integer;
/*
7. Reverse Integer Easy
Given a 32-bit signed integer, reverse digits of an integer.
Example 1:  Input: 123  Output: 321
Example 2:  Input: -123 Output: -321
Example 3:  Input: 120  Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1].
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

 */
public class Solution {
    // time complexity：O(log10(x))
    //space complexity：O(1)
    static public int reverse(int x){
        int res=0;
        while (x!=0){
            int mod = x % 10;
            //System.out.println("===mod====:"+mod);
            if (res > Integer.MAX_VALUE/10 || (res == Integer.MAX_VALUE / 10 && mod > 7)) return 0;
            if (res < Integer.MIN_VALUE/10 || (res == Integer.MIN_VALUE / 10 && mod < -8)) return 0;
            res = res*10 + mod;
            //System.out.println("===res====:"+res);
            x = x / 10;
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(reverse(-123));
    }
}

