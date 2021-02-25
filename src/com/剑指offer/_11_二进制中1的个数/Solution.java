package com.剑指offer._11_二进制中1的个数;
/*
二进制中1的个数
输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
*/

/*
如果一个整数不为0，那么这个整数至少有一位是1。
如果把这个整数减1 => 那么原来处在整数最右边的1就会变为0，原来在1后面的所有的0都会变成1(如果最右边的1后面还有0的话)。其余所有位将不会受到影响。

eg：
1. 二进制=1100 => 十进制=2^4+2^3=16+8=24

2. 减去1 => 十进制=23 => 二进制=1011 => 2^4+2^2+2^1+2^0=16+4+2+1=23

3. 与运算 => 1100 & 1011 => 1000 => 在1100的基础上少了一个1

4. 有多少1，就能重复多少次步骤2，3

=> 减去1后，第3位变成0，后面的两位0变成了1，而前面的1保持不变，因此得到的结果是1011.
=> 减1的结果是把最右边的一个1开始的所有位都取反了
=> 这个时候再把原来的整数和减去1之后的结果做与运算，从原来整数最右边一个1那一位开始所有位都会变成0。如1100&1011=1000.
=> 把一个整数减去1，再和原整数做与运算，会把该整数最右边一个1变成0
=> 那么一个整数的二进制有多少个1，就可以进行多少次这样的操作。
*/

public class Solution {
    static int NumberOf1(int n) {
           int count = 0;
           while(n!=0){
               n = n&(n-1);
               count++;
           }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(NumberOf1(3));
    }
}
