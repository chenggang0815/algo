package com.剑指offer._33_丑数;
/*
丑数

把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。

思路：
1. 已知所有丑数肯定都是都2,3,5的乘积得来的，并且每个丑数都可以乘以2,3,5再得到三个丑数
2. 根据第一点，从1开始，可以得到三个丑数队列，分别是每个丑数乘以 2 3 5 ，既包含了所有的丑数
3. 返回值是三个丑数队列中，从小到大排序且去重的结果。
4. 即每次取3个队列中的最小值，取完后需要更新队列。
5. 又因为3个队列中可能有相同值，所以需要用三个if 而不是if else

https://leetcode-cn.com/problems/chou-shu-lcof/comments/

*/

public class Solution {

    //time : o(n) space:o(n)
    static int GetUglyNumber_Solution(int index){
        if (index < 7) return index;

        int[] dp = new int[index];
        dp[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        for (int i = 1; i < index; i++){
            dp[i] = Math.min(dp[i2] * 2, Math.min(dp[i3] * 3, dp[i5] * 5));
            if (dp[i] == dp[i2] * 2) i2++;
            if (dp[i] == dp[i3] * 3) i3++;
            if (dp[i] == dp[i5] * 5) i5++;
        }

        return dp[index - 1];
    }

    public static void main(String[] args) {
        System.out.println(GetUglyNumber_Solution(10));
    }
}
