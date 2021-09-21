package Amazon._1167_Minimum_Cost_to_Connect_Sticks;

import java.util.PriorityQueue;

/*
1167. Minimum Cost to Connect Sticks

You have some number of sticks with positive integer lengths. These lengths are given as an array sticks, where sticks[i] is the length of the ith stick.
You can connect any two sticks of lengths x and y into one stick by paying a cost of x + y. You must connect all the sticks until there is only one stick remaining.
Return the minimum cost of connecting all the given sticks into one stick in this way.

Example 1:
Input: sticks = [2,4,3]
Output: 14
Explanation: You start with sticks = [2,4,3].
1. Combine sticks 2 and 3 for a cost of 2 + 3 = 5. Now you have sticks = [5,4].
2. Combine sticks 5 and 4 for a cost of 5 + 4 = 9. Now you have sticks = [9].
There is only one stick left, so you are done. The total cost is 5 + 9 = 14.
*/

/*
solution1: time complexity O(n^2)
always find the two smallest number in the array
sort => sum the two smallest number, and the result to array, sort again

solution2:
time complexity: O(nlog(n))
*/
public class Solution {
    static int findMinimumCost(int[] sticks){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int stick: sticks){
            pq.add(stick);
        }

        int cost = 0;
        while (pq.size() > 1){
            int stick1 = pq.poll();
            int stick2 = pq.poll();

            cost += (stick1 + stick2);
            pq.add(stick1+stick2);
        }

        return cost;
    }

    public static void main(String[] args) {
        System.out.println(findMinimumCost(new int[]{3,7,1,10,8}));
    }
}
