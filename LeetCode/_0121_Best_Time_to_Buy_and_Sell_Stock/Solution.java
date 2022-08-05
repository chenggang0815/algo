package LeetCode._0121_Best_Time_to_Buy_and_Sell_Stock;
/*
121. Best Time to Buy and Sell Stock

Say you have an array for which the ith element is the price of a given stock on day i.
If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:
Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.

Example 2:
Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/
/*
Solution:
1. maintain a minimum buy price => buyPrice
2. if current price > buyPrice => maxProfit = max(maxProfit, price - buyPrice)
*/
public class Solution {

    // Time Complexity: O(n^2)
    // Space Complexity:O(1)
    static int maxProfit(int[] prices) {
        int length = prices.length;
        int maxProfit = 0;
        for (int i = 0; i < length; i++){
            for (int j = i + 1; j < length; j++){
                int profit = prices[j] - prices[i];
                if (profit > maxProfit){
                    maxProfit = profit;
                }
            }
        }

        return maxProfit;
    }

    //Time Complexity:O(n)
    //Space Complexity:O(1)
    static int maxProfit2(int[] prices) {
        if(prices.length == 1) return 0;

        int buyPrice = prices[0];
        int maxProfit = 0;
        for(int i = 1; i < prices.length; i++){
            buyPrice = Math.min(buyPrice, prices[i]);
            if(prices[i] > buyPrice){
                maxProfit = Math.max(maxProfit, prices[i] - buyPrice);
            }
        }

        return maxProfit;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit2(nums));
    }
}
