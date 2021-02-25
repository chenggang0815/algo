package com.LeetCode._0338_Counting_Bits;

import java.util.Arrays;

/*
338. Counting Bits

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:
Input: 2
Output: [0,1,1]

Example 2:
Input: 5
Output: [0,1,1,2,1,2]

Follow up:
It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */

/*
思路1： 参考剑指offer 11题 time：O(n*sizeof(integer))

思路2：time：O(n)
<1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
<2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )

思路3：time：O(n)
奇数：二进制表示中，奇数一定比前面那个偶数多一个 1，因为多的就是最低位的 1。
     举例：
         0 = 0       1 = 1
         2 = 10      3 = 11
偶数：二进制表示中，偶数中 1 的个数一定和除以 2 之后的那个数一样多。因为最低位是 0，除以 2 就是右移一位，也就是把那个 0 抹掉而已，所以 1 的个数是不变的
     举例：
          2 = 10       4 = 100       8 = 1000
          3 = 11       6 = 110       12 = 1100

 */
public class Solution {
    // 思路1： 参考剑指offer 11题 time：O(n*sizeof(integer))
    static int[] countBits1(int num) {
        if (num == 0) return new int[]{0};

        int[] res = new int[num + 1];
        int index = 0;
        for (int i = 0; i <= num; i++) {
            int count = 0;
            int n = i;
            while (n != 0) {
                n = n & (n - 1);
                count++;
            }

            res[index++] = count;
        }

        return res;
    }
    /*
    eg:
    100011001
    1 & 1 = 1
    100011001 >> 1 => 10001100

    <1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
    <2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )
     */
    static int[] countBits2(int num){
        int[] res = new int[num + 1];

        for (int i = 1; i <= num; i++){
            res[i] = res[i >> 1] + (i & 1);
        }

        return res;
    }

    static int[] countBits3(int num){
        int[] res = new int[num + 1];

        for (int i = 1; i <= num; i++){
            if (i % 2 == 0){
                res[i] = res[i >> 1];
            }else {
                res[i] = res[i - 1] + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(countBits3(5)));

    }

}
