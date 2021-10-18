package Amazon._1648_Sell_Diminishing_Valued_Colored_Balls;
/*
1648. Sell Diminishing-Valued Colored Balls
Example 1:
Input: inventory = [2,5], orders = 4
Output: 14
Explanation: Sell the 1st color 1 time (2) and the 2nd color 3 times (5 + 4 + 3).
The maximum total value is 2 + 5 + 4 + 3 = 14.

Example 3:
Input: inventory = [2,8,4,10,6], orders = 20
Output: 110
*/
/*
Approach 1:
1. exists a price T(T = [0, max(inventory)]),
   sum = 0
   iterate the array, if nums[i] > T => sum nums[i] - T
2. find the T, make sum(T) <= order <= sum(T+1)
3. use binary search to find the T,
   3.0  T = (left + right)/2
   3.1 sum(T) >= order => decrease sum(T),increase T => left = mid + 1
   3.2 else => sum(T) < order => right = mid - 1 => res = mid
*/
public class Solution {
    static boolean sum(int[] nums, int target, int orders){
        int sum = 0;
        for (int num: nums){
            if (num > target) sum += (num - target);
            if (sum >= orders) {
                return true;
            }
        }

        return false;
    }

    static int binarySearch(int[] nums, int orders){
        int left = 0;
        int right = 0;
        for (int num: nums) right = Math.max(num, right);

        int res = 0;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (sum(nums, mid, orders)){
                left = mid + 1;
            }else{
                right = mid - 1;
                res = mid;
            }
        }

        return res;
    }

    static int maxProfit(int[] nums, int orders){
        int m = binarySearch(nums, orders);
        int mod = (int) (1e9 + 7);

        long res = 0;
        for (int num: nums){
            if (num > m){
                res += (long)(num + m + 1) * (long)(num - m ) / 2;
                System.out.println(res);
                orders = orders - (num - m);
            }
        }

        res += (long) orders * (long) m;

        return (int) res % 1000000007;
    }


    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1000000000}, 1000000000));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(1000000000);
    }
}
