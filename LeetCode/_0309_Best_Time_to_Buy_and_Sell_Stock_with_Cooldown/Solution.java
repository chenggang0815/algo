package LeetCode._0309_Best_Time_to_Buy_and_Sell_Stock_with_Cooldown;
/*
309. Best Time to Buy and Sell Stock with Cooldown

给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​

设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:

你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。

示例:
输入: [1,2,3,0,2]
输出: 3
解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]


对于力扣平台上的股票类型的题目：
121. 买卖股票的最佳时机
122. 买卖股票的最佳时机 II
123. 买卖股票的最佳时机 III
188. 买卖股票的最佳时机 IV
309. 最佳买卖股票时机含冷冻期
714. 买卖股票的最佳时机含手续费
剑指 Offer 63. 股票的最大利润
 */

/*
思路1 动态规划
1.1 定义状态
dp[i][j] 表示[0,i]区间内，在下标为i这一天状态为j时，手上拥有的金钱数。
这里 j 可以取3个值（下面的定义非常重要）：
0 表示：不持股状态：今天不是卖出了股票的不持股状态；
1 表示：持股状态；
2 表示：不持股状态：今天由于卖出了股票的不持股状态

1.2 初始化
dp[0][0]=0;//本来就不持有，啥也没干
dp[0][1]=-prices[0];//第0天只买入
dp[0][2]=0;//可以理解成第0天买入又卖出，那么第0天就是“不持股且当天卖出了”这个状态了，其收益为0，所以初始化为0是合理的

1.3 状态转移
1.3.1 第i天不持股且没卖出的状态dp[i][0]，也就是我没有股票，而且还不是因为我卖了它才没有的，那换句话说是从i-1天到第i天转移时，它压根就没给我股票！
      所以i-1天一定也是不持有，那就是不持有的两种可能：
      i-1天不持股且当天没有卖出dp[i-1][0]；
      i-1天不持股但是当天卖出去了dp[i-1][2]；
      所以： dp[i][0]=max(dp[i-1][0], dp[i-1][2])

1.3.2 第i天持股dp[i][1]，今天我持股，来自两种可能：
      要么是昨天我就持股，今天继承昨天的，也就是dp[i-1][1]，这种可能很好理解；
      要么是昨天我不持股，今天我买入的，但前提是昨天我一定没卖！因为如果昨天我卖了，那么今天我不能交易！也就是题目中所谓“冷冻期”的含义，只有昨天是“不持股且当天没卖出”这个状态，我今天才能买入！所以是dp[i-1][0]-p[i]
      所以： dp[i][1]=max(dp[i-1][1], dp[i-1][0]-p[i])

1.3.3 第i天不持股且当天卖出了，这种就简单了，那就是说昨天我一定是持股的，要不然我今天拿什么卖啊，而持股只有一种状态，昨天持股的收益加上今天卖出得到的新收益，就是dp[i-1][1]+p[i]啦
      所以：dp[i][2]=dp[i-1][1]+p[i]

1.4 最后一天的最大收益有两种可能，而且一定是不持有状态下的两种可能，把这两种不持有比较一下大小，返回即可

作者：jin-ai-yi
链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/fei-zhuang-tai-ji-de-dpjiang-jie-chao-ji-tong-su-y/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

思路2 动态规划 + 空间优化
2.1 注意到上面的状态转移方程中，dp[i][..]只与dp[i-1][..]有关，而与dp[i-2][..]及之前的所有状态都无关
2.2 因此我们不必存储这些无关的状态。也就是说，我们只需要将dp[i−1][0]，dp[i-1][1]，dp[i-1][2]存放在三个变量中，
2.3 通过它们计算出dp[i][0]，dp[i][1]，dp[i][2]，并存回对应的变量，以便于第i+1天的状态转移即可

 */
public class Solution {
    // time:o(n) space:o(n)
    static int maxProfit1(int[] prices) {
        if (prices.length == 0) return 0;

        int[][] dp = new int[prices.length][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        for (int i = 1; i < prices.length; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0]-prices[i]);
            dp[i][2] = dp[i-1][1]+prices[i];
        }

        return Math.max(dp[prices.length - 1][0], dp[prices.length - 1][2]);
    }

    // time:o(n) space:o(1)
    static int maxProfit2(int[] prices) {
        if (prices.length == 0) return 0;
        int f1 = 0;
        int f2 = -prices[0];
        int f3 = 0;
        for (int i = 1; i < prices.length; i++){
            int res1 = Math.max(f1, f3);
            int res2 = Math.max(f2, f1 - prices[i]);
            int res3 = f2 + prices[i];
            f1 = res1;
            f2 = res2;
            f3 = res3;
        }

        return Math.max(f1, f3);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit2(new int[]{1,2,3,0,2}));
    }
}
