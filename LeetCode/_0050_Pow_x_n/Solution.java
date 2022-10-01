package LeetCode._0050_Pow_x_n;
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
1. 在方法一中，我们也提到过，从左到右进行推导是不容易的，因为不知道是否需要额外乘x。
2. 但我们不妨找一找规律，看看哪些地方额外乘了x，并且它们对答案产生了什么影响
x^77 => x -> x^2 -> x^4 -> x^9 -> x^19 -> x^38 -> x^77 => 77的二进制1001101中的每个1

每个二进制数位都有一个权值，权值如下图所示，最终结果就等于所有二进制位为1的权值之积，, 例如上述 x^77次方对应的二进制 (1001101) 和每个二进制位的权值如下

1	     0	      0	     1	  1	     0	  1
x^64	x^32	x^16	x^8	 x^4	x^2	 x^1

最终结果就是所有二进制位为1的权值之积：x^1 * x^4 * x^8 * x^64 = x^77

x^77 => 77 => 1001101 => x^(2^0)*x^(2^2)*x^(2^3)*x^(2^6) => x^(1+2^2+2^3+2^6) => x^(77)


 */
public class Solution {

    static double myPow(double x, int n) {

        if (n == 0 || x == 1.0) return 1.0;

        double res = 1;
        long num = n; // when n = Integer.MIN_VALUE , -n = Integer.MIN_VALUE, so we need to use long first,
        num = num > 0 ? num : -num;
        for (long i = 0; i < num; i++){
            res *= x;
        }

        return n > 0 ? res : 1 / res;

        /*
        if (n == 0 || x == 1.0) return 1.0;

        double res = 1;

        int num = n > 0 ? n : -n;
        System.out.println(num);
        for (long i = 0; i < num; i++){
            res *= x;
        }

        System.out.println(res);

        return n > 0 ? res : 1 / res;*/
    }

    static double quickMul(double x, long N) {
        if (N == 0) return 1.0;

        double y = quickMul(x, N / 2);

        return N % 2 == 0 ? y * y : y * y * x;
    }

    // 快速幂 + 递归
    static double myPow1(double x, int n) {
        long N = n; // if n < 0 => 1 / x^(-n)

        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }


    static double quickMu2(double x, long N) {
        double ans = 1.0;

        // 贡献的初始值为 x
        double current_product = x;
        // 在对 N 进行二进制拆分的同时计算答案
        while (N > 0) {
            // 77 = 1	  0     0	   1	 1	   0	 1
            //    x^64	 x^32  x^16   x^8	x^4   x^2	x^1
            //最终结果就是所有二进制位为1的权值之积：x^1 * x^4 * x^8 * x^64 = x^77
            if (N % 2 == 1) ans *= current_product; // 如果 N 二进制表示的最低位为 1，那么需要计入贡献
            // 将贡献不断地平方
            current_product = current_product * current_product;
            // 舍弃 N 二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2; // N / 2 => 1001101 => 1001100
        }
        return ans;
    }

    // 快速幂 + 迭代
    static double myPow2(double x, int n) {
        long N = n;

        return N >= 0 ? quickMu2(x, N) : 1.0 / quickMu2(x, -N);
    }

    // 2022-10-01
    public double myPow3(double x, int n) {
        long num = n;
        if(num < 0){
            x = 1/x;
            num = -num;
        }

        double res = 1.0;
        double product = x;

        while(num > 0){
            if(num % 2 == 1) res *= product;
            product *= product;
            //n /= 2;
            // 1001 => 0100 => right shift
            num = num >> 1;
        }

        return res;
    }

    public static void main(String[] args) {
        //System.out.println(myPow(2,-2147483648));
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.MIN_VALUE);

        long N = Integer.MIN_VALUE;
        if (N < 0) N = -N;
        System.out.println(N);

        int n = Integer.MIN_VALUE;
        long num = n;
       // System.out.println(num);
        //long num = n > 0 ? n : -n;
        if(num < 0) num = -num;
        System.out.println(num);

    }
}
