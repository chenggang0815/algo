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
Solution:
for example:
input=00000010100101000001111010011101
output=10111001011110000010100101000000

for loop step1 to step3 32 times, each time we move the last position of input to the first position of result
1. first step, find the the last position of input
=> n&1 => 00000010100101000001111010011101&00000000000000000000000000000001 => 00000000000000000000000000000001
2. move this value to the first position of res
=> (n&1) << 31 => 00000000000000000000000000000001 << 32 => 10000000000000000000000000000000
3. move the input toward right, update input, which means the second last position will be the new last position
=> n = n >> 1 => 00000010100101000001111010011101 >> 1 => 00000001010010100000111101001110
*/
public class Solution {

    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32 && n != 0; ++i) {
            res |= (n & 1) << (31 - i);
            n >>>= 1;
        }
        return res;
    }

    // you need treat n as an unsigned value
    static int reverseBits1(int n) {
        int res = 0;
        for(int i = 0; i<32; ++i){
            res = res << 1;
            System.out.println(res);
            res = n & 1 | res;
            System.out.println(res);
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
        System.out.println(reverseBits(9));
    }
}
