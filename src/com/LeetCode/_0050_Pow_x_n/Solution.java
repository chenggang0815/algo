package com.LeetCode._0050_Pow_x_n;
/*
50. Pow(x, n)

Implement pow(x, n), which calculates x raised to the power n (i.e. xn).

Example 1:
Input: x = 2.00000, n = 10
Output: 1024.00000

Example 2:
Input: x = 2.10000, n = 3
Output: 9.26100

Example 3:
Input: x = 2.00000, n = -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25

Constraints:
-100.0 < x < 100.0
-2^31 <= n <= 2^31-1
-10^4 <= x^n <= 10^4
*/

/*
思路：乘法，每次乘以x time:(o(n)) => 超时

思路1 ： 快速幂 + 递归 time: o(log(n)) space: o(log(n))

1. 一个例子，如果要计算x^77 => x -> x^2 -> x^4 -> x^9 -> x^19 -> x^38 -> x^77
2. 在x -> x^2 -> x^4，x^19 -> x^38这些步骤中，我们直接把上一次的结果进行平方
3. 而在 x^4 -> x^9 -> x^19，x^38 -> x^77 这些步骤中，我们把上一次的结果进行平方后，还要额外乘一个x

直接从左到右进行推导看上去很困难，因为在每一步中，我们不知道在将上一次的结果平方之后，还需不需要额外乘x
但如果我们从右往左看，分治的思想就十分明显了：
    3.1 当我们要计算x^n时，我们可以先递归地计算出y=x⌊n/2⌋ => ⌊a⌋ 表示对a进行下取整；
    3.2 根据递归计算的结果，如果n为偶数，那么x^n = y^2
    3.3 如果n为奇数，那么 x^n = y^2*x
    3.4 递归的边界为n=0，任意数的0次方均为1

思路2 快速幂 + 迭代 time: o(log(n)) space: o(1)

在方法一中，我们也提到过，从左到右进行推导是不容易的，因为我们不知道是否需要额外乘x。但我们不妨找一找规律，看看哪些地方额外乘了x，并且它们对答案产生了什么影响
x^77 => x -> x^2 -> x^4 -> x^9 -> x^19 -> x^38 -> x^77
=>
77的二进制1001101中的每个1


每个二进制数位都有一个权值，权值如下图所示，最终结果就等于所有二进制位为1的权值之积，, 例如上述 x^77次方对应的二进制 (1001101) 和每个二进制位的权值如下

1	      0	      0	     1	  1	     0	  1
x^64	x^32	x^16	x^8	 x^4	x^2	 x^1

最终结果就是所有二进制位为1的权值之积：x^1 * x^4 * x^8 * x^64 = x^77
而且我们不必预先把每一个二进制位的权值计算出来，我们可以一边计算结果，一边计算权值
 */
public class Solution {

    static double quickMul(double x, long N) {
        if (N == 0) {
            return 1.0;
        }
        double y = quickMul(x, N / 2);
        return N % 2 == 0 ? y * y : y * y * x;
    }

    // 快速幂 + 递归
    static double myPow1(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }


    static double quickMu2(double x, long N) {
        double ans = 1.0;
        // 贡献的初始值为 x
        double x_contribute = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            if (N % 2 == 1) {
                // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            // 将贡献不断地平方
            x_contribute *= x_contribute;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }

    // 快速幂 + 迭代
    static double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMu2(x, N) : 1.0 / quickMu2(x, -N);
    }

    public static void main(String[] args) {
        System.out.println(myPow1(2.1,3));
    }
}
