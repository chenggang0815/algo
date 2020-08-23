package com.剑指offer._036_从1到n中1出现的次数;
/*
整数中1出现的次数,从1到n中1出现的次数

求出任意非负整数区间中1出现的次数（从1到n中1出现的次数）
 */
public class Solution {
    /*
    依次遍历每个数字
     */
    //time: o(n^2) space: o(n)
    static int NumberOf1Between1AndN_Solution(int n)
    {
        if (n==1) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i=1; i<n; i++){
            dp[i] = dp[i-1] + number(i);
        }

        return dp[n-1];

    }

    static int number(int n){
        String str = String.valueOf(n);
        int count = 0;
        for (int i=0; i<str.length(); i++){
            if (str.charAt(i) == '1'){
                count++;
            }
        }

        return count;
    }
/*
首先，对于一个数，分别考虑它的个位，十位，百位...上1的个数；
其次，当考虑某个具体的位数x时，有3种情况：
    1. x >= 2 226当取十位数的时候
    2. x = 1 216当取十位数的时候
    3. x = 0 206当取十位数的时候

这是因为当取十位数且x=1时，有以下种可能：
[10 11 12 .. 19 110 111 112 ... 119] 210 211 212 213 214 215 216
     10        +            10      + 7 = 27
我们把n以十位数分成两部分： 216/10 = 21 216 % 10 = 6
可以得到 27 = ((21+8) / 10) * 10 + (21 % 10 == 1 ? b+1 : 0)

链接：https://www.nowcoder.com/questionTerminal/bd7f978302044eee894445e244c7eee6?f=discussion
来源：牛客网

主要思路：设定整数点（如1、10、100等等）作为位置点i（对应n的各位、十位、百位等等），分别对每个数位上有多少包含1的点进行分析
根据设定的整数位置，对n进行分割，分为两部分，高位n/i，低位n%i
当i表示百位，且百位对应的数>=2,如n=31456,i=100，则a=314,b=56，此时百位为1的次数有a/10+1=32（最高两位0~31），每一次都包含100个连续的点，即共有(a%10+1)*100个点的百位为1
当i表示百位，且百位对应的数为1，如n=31156,i=100，则a=311,b=56，此时百位对应的就是1，则共有a%10(最高两位0-30)次是包含100个连续点，当最高两位为31（即a=311），本次只对应局部点00~56，共b+1次，所有点加起来共有（a%10*100）+(b+1)，这些点百位对应为1
当i表示百位，且百位对应的数为0,如n=31056,i=100，则a=310,b=56，此时百位为1的次数有a/10=31（最高两位0~30）
综合以上三种情况，当百位对应0或>=2时，有(a+8)/10次包含所有100个点，还有当百位为1(a%10==1)，需要增加局部点b+1
之所以补8，是因为当百位为0，则a/10==(a+8)/10，当百位>=2，补8会产生进位位，效果等同于(a/10+1)
 */
    //
    static public int NumberOf1Between1AndN_Solution2(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    public static void main(String[] args) {

        System.out.println(NumberOf1Between1AndN_Solution2(216));

    }
}
