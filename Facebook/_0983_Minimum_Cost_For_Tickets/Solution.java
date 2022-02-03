package Facebook._0983_Minimum_Cost_For_Tickets;

import java.util.HashSet;

/*
983. Minimum Cost For Tickets

You have planned some train traveling one year in advance. The days of the year in which you will travel are given as an integer array days. Each day is an integer from 1 to 365.
Train tickets are sold in three different ways:
1. a 1-day pass is sold for costs[0] dollars,
2. a 7-day pass is sold for costs[1] dollars, and
3. a 30-day pass is sold for costs[2] dollars.
The passes allow that many days of consecutive travel.

For example, if we get a 7-day pass on day 2, then we can travel for 7 days: 2, 3, 4, 5, 6, 7, and 8.
Return the minimum number of dollars you need to travel every day in the given list of days.

Example 1:
Input: days = [1,4,6,7,8,20], costs = [2,7,15]
Output: 11
Explanation: For example, here is one way to buy passes that lets you travel your travel plan:
On day 1, you bought a 1-day pass for costs[0] = $2, which covered day 1.
On day 3, you bought a 7-day pass for costs[1] = $7, which covered days 3, 4, ..., 9.
On day 20, you bought a 1-day pass for costs[0] = $2, which covered day 20.
In total, you spent $11 and covered all the days of your travel.
*/
/*
Solution
Approach 1: dynamic programming

Approach 2:
*/
public class Solution {
    public int mincostTickets(int[] days, int[] costs) {
        HashSet<Integer> set = new HashSet<>();
        for(int day : days) set.add(day);
        int[] dp = new int[366];
        for(int i = 1; i < 366; i++){
            if(set.contains(i)){
                dp[i] = Math.min(Math.min(dp[i - 1] + costs[0], dp[Math.max(i - 7, 0)] + costs[1]), dp[Math.max(i - 30, 0)] + costs[2]);
            }else{
                dp[i] = dp[i - 1];
            }
        }

        return dp[365];
    }
    public static void main(String[] args) {

    }
}
