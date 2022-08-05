package Facebook._1011_Capacity_To_Ship_Packages_Within_D_Days;
/*
1011. Capacity To Ship Packages Within D Days

A conveyor belt has packages that must be shipped from one port to another within days days.
The ith package on the conveyor belt has a weight of weights[i]. Each day, we load the ship with packages on the conveyor belt (in the order given by weights). We may not load more weight than the maximum weight capacity of the ship.
Return the least weight capacity of the ship that will result in all the packages on the conveyor belt being shipped within days days.

Example 1:
Input: weights = [1,2,3,4,5,6,7,8,9,10], days = 5
Output: 15
Explanation: A ship capacity of 15 is the minimum to ship all the packages in 5 days like this:
1st day: 1, 2, 3, 4, 5
2nd day: 6, 7
3rd day: 8
4th day: 9
5th day: 10
Note that the cargo must be shipped in the order given, so using a ship of capacity 14 and splitting the packages into parts like (2, 3, 4, 5), (1, 6, 7), (8), (9), (10) is not allowed.

Example 2:
Input: weights = [3,2,2,4,1,4], days = 3
Output: 6
Explanation: A ship capacity of 6 is the minimum to ship all the packages in 3 days like this:
1st day: 3, 2
2nd day: 2, 4
3rd day: 1, 4

Example 3:
Input: weights = [1,2,3,1,1], days = 4
Output: 3
Explanation:
1st day: 1
2nd day: 2
3rd day: 3
4th day: 1, 1

Constraints:
1 <= days <= weights.length <= 5 * 104
1 <= weights[i] <= 500
*/
/*
Solution:
假定「D 天内运送完所有包裹的最低运力」为 ans，那么在以 ans 为分割点的数轴上具有「二段性」：
数值范围在 −∞,ans) 的运力必然「不满足」 D 天内运送完所有包裹的要求
数值范围在 [ans,+∞) 的运力必然「满足」 D天内运送完所有包裹的要求
即我们可以通过「二分」来找到恰好满足 D天内运送完所有包裹的分割点 ans。

接下来我们要确定二分的范围，由于不存在包裹拆分的情况，考虑如下两种边界情况：
理论最低运力：只确保所有包裹能够被运送，自然也包括重量最大的包裹，此时理论最低运力为 max，max 为数组 weights 中的最大值
理论最高运力：使得所有包裹在最短时间（一天）内运送完成，此时理论最高运力为 sum，sum 为数组 weights 的总和

Approach 1: find the first need <= days

*/
public class Solution {

    public int shipWithinDays(int[] weights, int days) {
        int left = 0;
        int right = 0;
        for(int weight : weights){
            left = Math.max(left, weight);
            right += weight;
        }

        while(left < right){
            int mid = left + (right - left) / 2;
            int need = 1, cur = 0;
            for(int weight : weights){
                if(cur +  weight > mid){
                    need++;
                    cur = 0;
                }
                cur += weight;
            }
            //find the first need <= days
            if(need <= days) right = mid;
            else left = mid + 1;
            //if(need > days) left = mid + 1;
            //else right = mid;
        }

        return left;
    }

    public static void main(String[] args) {

    }
}
