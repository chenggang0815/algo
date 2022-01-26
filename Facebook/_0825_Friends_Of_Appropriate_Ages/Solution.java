package Facebook._0825_Friends_Of_Appropriate_Ages;
import java.util.Arrays;
/*
825. Friends Of Appropriate Ages
There are n persons on a social media website. You are given an integer array ages where ages[i] is the age of the ith person.

A Person x will not send a friend request to a person y (x != y) if any of the following conditions is true:
    1. age[y] <= 0.5 * age[x] + 7
    2. age[y] > age[x]
    3. age[y] > 100 && age[x] < 100

Otherwise, x will send a friend request to y.

Note that if x sends a request to y, y will not necessarily send a request to x. Also, a person will not send a friend request to themself.
Return the total number of friend requests made.

Example 1:
Input: ages = [16,16]
Output: 2
Explanation: 2 people friend request each other.

Example 3:
Input: ages = [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.

Constraints:
1. n == ages.length
2. 1 <= n <= 2 * 104
3. 1 <= ages[i] <= 120
*/
/*
Solution
Approach 1
1. first, sort the ages array
2. for example
   sorted array = 20 30 100 110 120
                      *       67
         index = 0   1  2   3   4
3. from right to left,
for x = 120 , 120 * 0.5 + 7 = 67
based on the conditions, the y have => 120 >= y > 67

for x = 110, 110*0.5+7 = 62
110 >= y > 62

for x = 100, 100*0.5+7=57
100 >= y > 57

so we can use binary search find the first number > 67, then we have the number of the request for 120

4. corner case 1, because we have to meet
    1. age[y] <= 0.5 * age[x] + 7
    2. age[y] > age[x]
when x = y <= 14 => we can't meet 1 and 2 at the same time, so we can skip when ages[i] <= 14

5. corner case 2, duplicate number
if we can 18 18 18, the cnt  = number of 18 is 3
from right to left, we have 3 request, from left to right, we also have 3 request.

if cnt = 4,  from right to left, we have 4*3/2 = 6, from left to right, we also have 6
*/
public class Solution {
    public int numFriendRequests(int[] ages) {
        int res = 0;
        Arrays.sort(ages);
        System.out.print(Arrays.toString(ages) + "\n");
        int cnt = 1;
        for(int i = ages.length - 1; i >= 0; i--){
            if(ages[i] <= 14) continue;
            // if have duplicate number, we need add extra result of cnt*(cnt - 1)/ 2
            if(i > 0 && ages[i] == ages[i - 1]) cnt++;
            else{
                res += cnt * (cnt - 1) / 2;
                cnt = 1;
            }
            int x = ages[i];
            double min = x * 0.5 + 7;
            int index = binarySearch(ages, i, min);
            //System.out.print(x + " " + min + " " + index + " " + (i - index) + "\n");
            res += (i - index);
        }


        return res;
    }

    int binarySearch(int[] nums, int right, double target){
        int left = 0;
        while(left < right){
            int mid = left + (right - left) / 2;
            if(nums[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return right;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
