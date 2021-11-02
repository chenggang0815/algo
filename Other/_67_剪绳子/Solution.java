package Other._67_剪绳子;
/*
剪绳子

给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
每段绳子的长度记为k[0],k[1],...,k[m]。
请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 */

/*
思路一 ： 暴力递归

思路二： 动态规划
1. 设dp[i]代表数为i时，最大的乘积，所以对于dp[i] （i = 1~n）,共有i种分割的可能，2*dp[i-2] 3*dp[i-3] ... i*dp[0]
2. 并且对于每次分割来说，又有两种可能，比如 3*dp[i-3] 和 3*i-3， 分别表示在i的长度分割3时，i-3这段长度还分割或者不分割
3. 既有 dp[i] = Math.max(dp[i], Math.max(j*dp[i-j], j*(i-j)))
4. 因为有对于dp[i]来说有i中分割的可能，所以需要比较i种可能，选最大值。即比较两次（两个Math.max）,对某个j来说有两种，对某个i来说有j（j= 1 ~i）种

思路三： 数学归纳法
/**
 * 题目分析：
 * 先举几个例子，可以看出规律来。
 * 4 ： 2*2
 * 5 ： 2*3
 * 6 ： 3*3
 * 7 ： 2*2*3 或者4*3
 * 8 ： 2*3*3
 * 9 ： 3*3*3
 * 10：2*2*3*3 或者4*3*3
 * 11：2*3*3*3
 * 12：3*3*3*3
 * 13：2*2*3*3*3 或者4*3*3*3
 *
 * 下面是分析：
 * 首先判断k[0]到k[m]可能有哪些数字，实际上只可能是2或者3。
 * 当然也可能有4，但是4=2*2，我们就简单些不考虑了。
 * 5<2*3,6<3*3,比6更大的数字我们就更不用考虑了，肯定要继续分。
 * 其次看2和3的数量，2的数量肯定小于3个，为什么呢？因为2*2*2<3*3，那么题目就简单了。
 * 直接用n除以3，根据得到的余数判断是一个2还是两个2还是没有2就行了。
 * 由于题目规定m>1，所以2只能是1*1，3只能是2*1，这两个特殊情况直接返回就行了。
 *
 * 乘方运算的复杂度为：O(log n)，用动态规划来做会耗时比较多。
 */
public class Solution {

    //time: o(2^n) space: o(n)
    static int cutRope(int target) {
        if (target == 0 || target == 1) return 0;

        int res = 1;
        for (int i = 1; i < target; i++){
            res = Math.max(res, Math.max(i * (target - i), i * cutRope(target - i)));
        }

        return res;
    }

    //time: o(n^2) space: o(n)
    static int cutRope2(int target){
        if (target == 0 || target == 1) return 0;

        int[] dp = new int[target+1];
        dp[1] = 0;
        for (int i = 2; i <= target; i++){
            for (int j = 1; j <= i; j++){
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[target];
    }

    // time: log(n) space: o(1)
    static int cutRope3(int target){
        if (target == 0 || target == 1) return 0;
        if (target == 2) return 1;
        if (target == 3) return 2;

        int x = target % 3;
        int y = target / 3;
        int res = 1;
        if (x == 0){
            for (int i = 0; i < y; i++){
                res *= 3;
            }
        }else if (x == 1){
            for (int i = 0; i < y - 1; i++){
                res *= 3;
            }
            res = 2 * 2 * res;
        }else {
            for (int i = 0; i < y; i++){
                res *= 3;
            }

            res = 2* res;
        }

        return res;
    }

    public static void main(String[] args) {
        int target = 8;
        System.out.println(cutRope(8));

    }
}
