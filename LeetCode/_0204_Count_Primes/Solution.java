package LeetCode._0204_Count_Primes;
/*
204. Count Primes
Count the number of prime numbers less than a non-negative number, n.

Example 1:
Input: n = 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

Example 2:
Input: n = 0
Output: 0

Example 3:
Input: n = 1
Output: 0

质数：大于1且只能整除1和它本身
 */
//2 3 4 5 6 7 8 9 10 11 12
//2，3，5，7，11，12 质数
/*
思路1：暴力 => 不能通过
思路2：暴力优化 => 能通过 time：O(n*n^(1/2)) space:o(1)
1. 对正整数n，如果用2到sqrt(n)之间(包含边界)的所有整数去除，均无法整除，则n为质数
    for example: 7 % i !=0  i=[0, 1, 2] => 7 is prime
                 9 & i ==0  i=[0,1,2,3] => 9 is not prime
2. 一切非2偶数一定不可能为质数

思路3：埃氏筛 time:o(n) space:o(n)
枚举没有考虑到数与数的关联性，因此难以再继续优化时间复杂度。接下来我们介绍一个常见的算法该算法简称埃氏筛。
1. 如果x是质数，那么大于x的x的倍数2x,3x,...一定不是质数，因此我们可以从这里入手
2. 我们设isPrime[i] 表示数i是不是质数，如果是质数则为0，否则为1
3. 从小到大遍历每个数，如果这个数为质数，则将其所有的倍数都标记为合数（除了该质数本身)
 */

public class Solution {
    //思路1
    static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++){
             if (isPrimes(i)) count++;
        }
        return count;
    }

    static boolean isPrimes(int n){
        for (int i = 2; i < n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    //思路2
    static int countPrimes2(int n) {
        if (n < 3) return 0;
        int count = 1; //2为质数
        for (int i = 3; i < n; i++){
            if(i % 2 == 0) continue;
            if(isPrimes2(i)) count++;
        }
        return count;
    }

    static boolean isPrimes2(int n){
        for (int i = 2; i*i <= n; i++){
            if (n % i == 0){
                return false;
            }
        }
        return true;
    }

    //思路3
    static int countPrimes3(int n) {
        int[] isPrime = new int[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == 0) {
                res++;
                if ((long) i * i < n) {
                    for (int j = i * i; j < n; j += i) {
                        isPrime[j] = 1;
                    }
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(countPrimes3(10));
       // System.out.println(isPrimes(7));

    }
}
