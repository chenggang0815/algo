package 剑指offer._10_矩形覆盖;
/*
矩形覆盖

我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？


假设一个2*n维的矩阵，有f(n)种情况，并且可以拆解成两种情况：
    最右边的2*1矩阵竖着放：即f(n-1)
    最右边的两个2*1矩阵横着放：即f(n-2) （如果最右边的两个2*1矩阵竖着放也是f(n-1)）
递推公式： f(n) = f(n-1)+f(n-2)
 */
public class Solution {
    //time:o(2^n) space:o(2^n)
    static public int RectCover(int target) {
        if (target == 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;

        return RectCover(target - 1) + RectCover(target - 2);
    }

    //time:o(n) space:o(n)
    static public int RectCover2(int target){
        if (target == 0) return 0;
        int[] dp = new int[target];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < target; i++){
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[target - 1];
    }

    //time:o(n) space:o(1)
    static public int RectCover3(int target){
        if (target==0) return 0;
        int first = 1;
        int second = 2;
        int result = 0;
        for (int i = 2; i < target; i++){
            result = first+second;
            first = second;
            second = result;
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(RectCover2(3));
    }
}
