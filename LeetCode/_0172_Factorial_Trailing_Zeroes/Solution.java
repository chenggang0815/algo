package LeetCode._0172_Factorial_Trailing_Zeroes;
/*
172. Factorial Trailing Zeroes

Given an integer n, return the number of trailing zeroes in n!.
Follow up: Could you write a solution that works in logarithmic time complexity?

Example 1:
Input: n = 3
Output: 0
Explanation: 3! = 6, no trailing zero.

Example 2:
Input: n = 5
Output: 1
Explanation: 5! = 120, one trailing zero.

Example 3:
Input: n = 0
Output: 0
 */
/*
思路：
首先肯定不能依赖于把阶乘算出来再去判断有多少个零了，因为阶乘很容易就溢出了，所以先一步一步理一下思路吧
1. 首先末尾有多少个 0 ，只需要给当前数乘以一个 10 就可以加一个 0
2. 2 和 5 相乘构成了一个 10。而对于 10 的话，其实也只有 2 * 5 可以构成，所以我们只需要找有多少对 2/5
3. 含有2的因子每两个出现一次，含有5的因子每5个出现一次，所有 2 出现的个数远远多于 5，换言之找到一个 5，一定能找到一个 2 与之配对。所以我们只需要找有多少个 5
4. 判断每个累乘的数有多少个 5 的因子即可

优化：
1. 每5个数计算一下，跳过肯定没有5的 => time o(n)
2. 每隔 5 个数，出现一个 5，每隔 25 个数，出现 2 个 5，每隔 125 个数，出现 3 个 5... 以此类推，最终 5 的个数就是 n / 5 + n / 25 + n / 125 ...
   写程序的话，如果直接按照上边的式子计算，分母可能会造成溢出。所以算 n / 25 的时候，我们先把 n 更新，n = n / 5，然后再计算 n / 5 即可
   => time: o(log5(n))
 */
public class Solution {
    static int numfive(int i){
        int res = 0;
        while (i > 0){
            if (i % 5 == 0) res++;
            i = i / 5;
        }

        return res;
    }

    static int numfive2(int i){
        int res = 0;
        while (i % 5 == 0){
            res++;
            i = i / 5;
        }

        return res;
    }
    // time o(n) 大部分数字都只包含一个因子5，即numfive2只用计算一次
    static int trailingZeroes(int n) {
        int res = 0;
        for (int i = n; i > 0; i--){
            int temp = numfive2(i);
            if (temp > 0) System.out.println(i);
            res = res + temp;

        }
        return res;
    }

    // time o(n)
    static int trailingZeroes2(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i += 5) {
            int currentFactor = i;
            while (currentFactor % 5 == 0) {
                zeroCount++;
                currentFactor /= 5;
            }
        }
        return zeroCount;
    }
    //time: o(log5(n))
    static int trailingZeroes3(int n){
        int res = 0;
        while (n > 0){
            res += n / 5;
            n /= 5;
        }

        return res;
    }

    public static void main(String[] args) {
    System.out.println(trailingZeroes(60));
    System.out.println(trailingZeroes3(60));

    }
}
