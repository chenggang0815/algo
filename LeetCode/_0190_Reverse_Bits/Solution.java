package LeetCode._0190_Reverse_Bits;
/*
190. Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

Note:
Note that in some languages such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.
Follow up:

If this function is called many times, how would you optimize it?

Example 1:
Input: n = 00000010100101000001111010011100
Output:    964176192 (00111001011110000010100101000000)
Explanation: The input binary string 00000010100101000001111010011100 represents the unsigned integer 43261596, so return 964176192 which its binary representation is 00111001011110000010100101000000.

Example 2:
Input: n = 11111111111111111111111111111101
Output:   3221225471 (10111111111111111111111111111111)
Explanation: The input binary string 11111111111111111111111111111101 represents the unsigned integer 4294967293, so return 3221225471 which its binary representation is 10111111111111111111111111111111.
 */

/*
00001010->01010000

00000000 << 1 => 00000000
00001010 & 1 => 00001010 & 00000001 => 00000000



00001010 >> 1 => 00000101



 */
public class Solution {
    // you need treat n as an unsigned value
    static int reverseBits(int n) {
        int res = 0;
        for(int i = 0; i<32; ++i){
            res = res << 1;
            res = n & 1 | res;
            n = n >> 1;
        }
        return res;
    }

    static int reverseBits2(int n) {
        int res = 0;
        for(int i = 0; i<32; ++i){
            res = (res << 1) + (n & 1);
            n >>= 1;
        }
        return res;
    }


    public static void main(String[] args) {
        //System.out.println(reverseBits(00000000000000000000000000000001));
        System.out.println(1&1);
    }
}
