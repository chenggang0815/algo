package LeetCode._0122_Best_Time_to_Buy_and_Sell_Stock_II;
/*
122. Best Time to Buy and Sell Stock II

Say you have an array prices for which the ith element is the price of a given stock on day i.
Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:
Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 */
/*
思路1：动态规划 time：o(n) space:o(n) => time:o(n) space:o(1)
1. 定义变量含义
    1.1 dp[i][0]表示第i+1天交易完之后手里没有股票的最大利润
    1.2 dp[i][1]表示第i+1天交易完之后手里持有股票的最大利润

2. 转移方程
    2.1当天交易完之后手里没有股票可能有两种情况，
        2.1.1 一种是当天没有进行任何交易，又因为当天手里没有股票，所以当天没有股票的利润只能取前一天手里没有股票的利润
        2.1.2 一种是把当天手里的股票给卖了，既然能卖，说明手里是有股票的，所以这个时候当天没有股票的利润要取前一天手里有股票的利润加上当天股票能卖的价格。
    这两种情况我们取利润最大的即可，所以可以得到 => dp[i][0]=max(dp[i-1][0],dp[i-1][1]+prices[i]);

    2.2 当天交易完之后手里持有股票也有两种情况，
        2.2.1 一种是当天没有任何交易，又因为当天手里持有股票，所以当天手里持有的股票其实前一天就已经持有了
        2.2.2 还一种是当天买入了股票，当天能卖股票，说明前一天手里肯定是没有股票的
    我们取这两者的最大值，所以可以得到 => dp[i][1]=max(dp[i-1][1],dp[i-1][0]-prices[i]);

3. 初始值
第一天
    3.1 如果买入：dp[0][1]=-prices[0];
    3.2 如果没买：dp[0][0]=0;

链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/dong-tai-gui-hua-he-tan-xin-suan-fa-jie-p96h2/

思路2：贪心算法 time:o(n) space:o(1)
遍历整个股票交易日价格列表 price，策略是所有上涨交易日都买卖（赚到所有利润），所有下降交易日都不买卖（永不亏钱）。
1. 设 tmp 为第 i-1 日买入与第 i 日卖出赚取的利润，即 tmp = prices[i] - prices[i - 1] ；
2. 当该天利润为正 tmp > 0，则将利润加入总利润 profit；当利润为 00 或为负，则直接跳过；
3. 遍历完成后，返回总利润 profit

 */
public class Solution {
    //time：o(n) space:o(n)
    static int maxProfit(int[] prices){
        if (prices == null || prices.length < 2) return 0;

        int length = prices.length;
        int[][] dp = new int[length][2];
        //初始条件
        dp[0][1] = -prices[0];
        dp[0][0] = 0;
        for (int i = 1; i < length; i++){
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //最后一天肯定是手里没有股票的时候，利润才会最大,只需要返回dp[length - 1][0]即可

        return dp[length - 1][0];
    }
    /*
    上面计算的时候我们看到当天的利润只和前一天有关，没必要使用一个二维数组，只需要使用两个变量，
    一个记录当天交易完之后手里持有股票的最大利润
    一个记录当天交易完之后手里没有股票的最大利润
     */
    static int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2)
            return 0;
        int length = prices.length;
        //初始条件
        int hold = -prices[0];//持有股票
        int noHold = 0;//没持有股票
        for (int i = 1; i < length; i++) {
            //递推公式转化的
            noHold = Math.max(noHold, hold + prices[i]);
            hold = Math.max(hold, noHold - prices[i]);
        }
        //最后一天肯定是手里没有股票的时候利润才会最大，
        //所以这里返回的是noHold
        return noHold;
    }

    //贪心算法
    static int maxProfit3(int[] prices){
        int profit = 0;
        for (int i = 1; i < prices.length; i++){
            int temp = prices[i] - prices[i - 1];
            if (temp > 0 ){
                profit += temp;
            }
        }

        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit3(new int[]{7,1,5,3,6,4}));
    }
}
