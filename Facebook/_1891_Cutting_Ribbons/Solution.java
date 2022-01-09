package Facebook._1891_Cutting_Ribbons;
/*
1891. Cutting Ribbons
You are given an integer array ribbons, where ribbons[i] represents the length of the ith ribbon, and an integer k.
You may cut any of the ribbons into any number of segments of positive integer lengths, or perform no cuts at all.
For example, if you have a ribbon of length 4, you can:
Keep the ribbon of length 4,
Cut it into one ribbon of length 3 and one ribbon of length 1,
Cut it into two ribbons of length 2,
Cut it into one ribbon of length 2 and two ribbons of length 1, or
Cut it into four ribbons of length 1.
Your goal is to obtain k ribbons of all the same positive integer length. You are allowed to throw away any excess ribbon as a result of cutting.

Return the maximum possible positive integer length that you can obtain k ribbons of, or 0 if you cannot obtain k ribbons of the same length.

Example 1:
Input: ribbons = [9,7,5], k = 3
Output: 5
Explanation:
- Cut the first ribbon to two ribbons, one of length 5 and one of length 4.
- Cut the second ribbon to two ribbons, one of length 5 and one of length 2.
- Keep the third ribbon as it is.
Now you have 3 ribbons of length 5.

Example 3:
Input: ribbons = [5,7,9], k = 22
Output: 0
Explanation: You cannot obtain k ribbons of the same positive integer length.

Constraints:
1 <= ribbons.length <= 105
1 <= ribbons[i] <= 105
1 <= k <= 109
*/

/*
Solution
Approach 1: binary search
for example, we can iterate from 1 to max[ribbons],
Input: ribbons = [9,7,5], k = 3
1: 9+7+5=21 >= 3 => if we cut each ribbon to 1 length, we can get 21 ribbons
2: 4+3+2=9 >= 3
3: 3+2+1=6 >= 3
4: 2+1+1=4 >= 3
5: 1+1+1=3 >= 3
6: 1 + 1 < 3
7: 1 < 3
8: 1 < 3
9: 1 < 3
so, we can use binary search to search the last value meet count >= 3,
or, we can use binary search to search the first value meet count < 3, then return left - 1
*/
public class Solution {
    public int maxLength(int[] ribbons, int k) {
        int max = 0;
        for(int num : ribbons) max = Math.max(max, num);

        int left = 1;
        int right = max;
        int res = 0;
        while(left <= right){
            int mid = left + (right - left) / 2;
            int count = 0;
            for(int num : ribbons) count+= num / mid;
            if(count >= k){
                left = mid + 1;
                res = mid;
            }else{
                right = mid - 1;
            }
        }

        return res;
    }

    public int maxLength2(int[] ribbons, int k) {
        int left = 1;
        int right = (int) 1e5 + 1; // corner case [10000]*49 k=10000
        while(left < right){
            int mid = left + (right - left) / 2;
            int count = 0;
            for(int num : ribbons) count+= num / mid;
            if(count < k){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return left - 1;
    }


    public static void main(String[] args) {

    }
}
