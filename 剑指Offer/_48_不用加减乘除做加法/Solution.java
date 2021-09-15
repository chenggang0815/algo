package 剑指Offer._48_不用加减乘除做加法;
/*
不用加减乘除做加法
leetcode 371. Sum of Two Integers

eg 13 + 9 = 2
13的二进制：1101 9的二进制：1001
第一轮
1. 计算不进位的和 => 1101 ^ 1001 => 0100
2. 计算进位 => 1101 & 1001 => 1001 <<1 => 10010
第二轮
1. 计算不进位的和 00100 ^ 10010 => 10110
2. 计算进位 => 00100 & 10010 => 00000 <<1 => 000000

进位为0，return 10110 => 2^4+2^2+2^1=16+4+2=22

思路：
1）分析上面对二进制的计算过程，不难发现：
1.计算不进位的和，相当于对两个数进制异或：1101^1001=0100；
2.计算进位，第1位相当于对两个数求与：1101&1001=1001，然后再对其进行左移1位：1001<<1=10010。
然后再重复以上两个步骤。这里再异或一次就得到结果了，没进位：0100^10010=10110=22。

2）计算a+b，等价于(a^b)+((a&b)<<1)。
由于公式中又出现了+号，因此要再重复2）这个等价的计算过程。
结束条件是：没有进位了。

链接：https://leetcode-cn.com/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/er-jin-zhi-qiu-he-chao-xiang-xi-da-bai-10000yong-h/

 tip：
 在计算机系统中，数值一律用补码来表示和存储。
 补码的优势： 加法、减法可以统一处理（CPU只有加法器）。因此，以上方法同时适用于正数和负数的加法
 */
public class Solution {
    //时间复杂度 O(1)：最差情况下,需循环31次 => int32
    //空间复杂度 O(1)
    static public int Add(int num1,int num2) {
        while (num2 != 0){
            int temp = (num1 ^ num2); //求和（不计进位）. 相同位置0，相反位置1
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }

        return num1;
    }
    public static void main(String[] args) {
        System.out.println(Add(4,6));
    }
}
