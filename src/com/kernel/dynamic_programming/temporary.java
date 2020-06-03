package com.kernel.dynamic_programming;

import java.util.HashMap;

/*
每日复习专用
 */
public class temporary {

    /*
Example 1:
Input: [5,3,4,5]
Output: true
Explanation:
Alex starts first, and can only take the first 5 or the last 5.
Say he takes the first 5, so that the row becomes [3, 4, 5].
If Lee takes 3, then the board is [4, 5], and Alex takes 5 to win with 10 points.
If Lee takes the last 5, then the board is [3, 4], and Alex takes 4 to win with 9 points.
This demonstrated that taking the first 5 was a winning move for Alex, so we return true.

Note:
    2 <= piles.length <= 500
    piles.length is even.
    1 <= piles[i] <= 500
    sum(piles) is odd.
     */

    /*
    dp[i][j] = max(nums[i]-dp[i+1][j],nums[j]-dp[i][j-1])
    dp[0][1] dp[1][2] ... dp[n-2][n-1]
    dp[0][2]              dp[n-3][n-1]
    return dp[0][n-1] >0
     */
    static boolean stonegame(int[] nums){
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int n=0;n<length;n++){
            dp[n][n] = nums[n];
        }

        for (int j=1;j<length;j++){
            for (int i=0;i<length-j;i++){
                dp[i][i+j] = Math.max(nums[i]-dp[i+1][i+j],nums[i+j]-dp[i][i+j-1]);
            }
        }

        System.out.println(dp[0][length-1]);

        return dp[0][length-1]>0;
    }

    static public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<array.length; i++){
            if(map.containsKey(array[i])){
                map.put(array[i], map.get(array[i]) + 1);
            }else{
                map.put(array[i], 1);
            }
        }
        int[] nums = new int[2];
        for(int j=0; j<array.length; j++){
            int n=0;
            if(map.get(array[j]) == 1){
                System.out.println(array[j]);
                nums[n] = array[j];
                n++;
            }
        }

        num1 = new int[]{nums[0]};
        num2 = new int[]{nums[1]};
    }

    static public double Power(double base, int exponent) {
        if(exponent == 0) return 1;

        double res = 1;
        while(exponent != 0){
            if((exponent & 1) == 1){
                res = res * base;
            }
            base *= base;
            exponent >>= 1;
            System.out.println(exponent);
        }

        return res;

    }


    public static void main(String[] args) {
        int[] nums = new int[]{2,4,3,6,3,2,5,5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        //FindNumsAppearOnce(nums,num1,num2);

        System.out.println(Power(2,4));

    }
}
