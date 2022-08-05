package LeetCode._0300_Longest_Increasing_Subsequence;
import java.util.ArrayList;
import java.util.Arrays;
/*
300. Longest Increasing Subsequence
Given an integer array nums, return the length of the longest strictly increasing subsequence.
A subsequence is a sequence that can be derived from an array by deleting some or no elements without changing the order of the remaining elements.
For example, [3,6,2,7] is a subsequence of the array [0,3,1,6,2,2,7].

Example 1:
Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
*/

/*
Approach 1： - time:O(n^2) space:O(n)
1. dp[i] represents the length of the longest increasing subsequence that ends with the i_th element  （ps：注意区分dp[i]的值代表nums前i个数字的最长子序列长度，前者需要求max(dp)）
2. base case => initialize every element in the array equal 1 => Arrays.fill(dp, 1)
3. recurrence relation:
    if we know dp[2], how can we get dp[3]? => if(nums[3] > nums[2]) => dp[3] = dp[2] + 1
    for dp[0], dp[1], dp[2],
    if(nums[3] > nums[2]) => dp[3] = dp[2] + 1
    if(nums[3] > nums[1]) => dp[3] = dp[1] + 1
    if(nums[3] > nums[0]) => dp[3] = dp[0] + 1
    for example => 1 2 1 3 => dp[0]=1 dp[1]=2 dp[2]=1 dp[3]=3
    so, we have:
    for every j < i => if nums[i] > nums[j] => dp[i] = Math.max(dp[i], dp[j] + 1)
4. so, we need to ues two for loop to iteration,
   4.1 at each iteration, we use a second for loop to iterate from j=0 to j=i-1, which means all the element before i
   4.2 for each element before i, we check if that element is smaller than nums[i]
   4.3 if so, set dp[i] = Math.max(dp[i], dp[j] + 1)
5. return the max value from dp

Approach 2: Intelligently Build a Subsequence time:O(n^2) space:O(n)
1. Initialize an array sub which contains the first element of nums.
2. Iterate through the input, starting from the second element. For each element num:
    2.1 If num is greater than any element in sub, then add num to sub.
    2.2 Otherwise, iterate through sub and find the first element that is greater than or equal to num. Replace that element with num.
3. Return the length of sub.
for example:
1. Consider the example nums = [8, 1, 6, 2, 3, 10]. Let's try to build an increasing subsequence starting with an empty one: sub = [].
2. At the first element 8, we might as well take it since it's better than nothing, so sub = [8].
3. At the second element 1, we can't increase the length of the subsequence since 8 >= 1,
   so we have to choose only one element to keep.
   Well, this is an easy decision, let's take the 1 since there may be elements later on that are greater than 1 but less than 8,
   now we have sub = [1].
4. At the third element 6, we can build on our subsequence since 6 > 1, now sub = [1, 6].
5. At the fourth element 2, we can't build on our subsequence since 6 >= 2,
   but can we improve on it for the future?
   Well, similar to the decision we made at the second element,
   if we replace the 6 with 2, we will open the door to using elements that are greater than 2 but less than 6 in the future,
   so sub = [1, 2].
6. At the fifth element 3, we can build on our subsequence since 3 > 2. Notice that this was only possible because of the swap we made in the previous step,
   so sub = [1, 2, 3].
7. At the last element 10, we can build on our subsequence since 10 > 3,
   giving a final subsequence sub = [1, 2, 3, 10]. The length of sub is our answer.

One thing to add: this algorithm does not always generate a valid subsequence of the input,
but the length of the subsequence will always equal the length of the longest increasing subsequence.
For example, with the input [3, 4, 5, 1], at the end we will have sub = [1, 4, 5],
which isn't a subsequence, but the length is still correct.
The length remains correct because the length only changes when a new element is larger than any element in the subsequence.
In that case, the element is appended to the subsequence instead of replacing an existing element.

Approach 3: Approach2 + binary search- time:o(nlog(n)) space:o(n)
1. Initialize an array sub which contains the first element of nums.
2. Iterate through the input, starting from the second element. For each element num:
    2.1 If num is greater than any element in sub, then add num to sub.
    2.2 Otherwise, perform a binary search in sub to find the smallest element that is greater than or equal to num. Replace that element with num.
3. Return the length of sub.

2.1 遍历数组nums，在遍历到nums[i]时：
    2.1.1 如果nums[i]>d[len] => 则直接加入到d数组末尾
    2.1.2 否则在d数组中二分查找，找到第一个比nums[i]大的数d[k]，并更新d[k]=nums[i]

以输入序列[0,8,4,12,2]为例：
第一步插入0 d=[0]
第二步插入8 d=[0,8]
第三步插入4 d=[0,4]
第四步插入12 d=[0,4,12]
第五步插入2 d=[0,2,12]
最终得到最大递增子序列长度为3
*/
public class Solution {
    static int lengthOfLIS1(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < i; j++){
                if (nums[i] > nums[j]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
        }
        int res = 0;
        for(int num: dp) res = Math.max(res, num);

        return res;
    }

    static int lengthOfLIS2(int[] nums) {
        ArrayList<Integer> res = new ArrayList<>();
        res.add(nums[0]);
        for( int num: nums){
            if (num > res.get(res.size() - 1)){
                res.add(num);
                continue;
            }

            int left = 0;
            int right = res.size() - 1;
            int index = 0;
            while (left <= right){ // 找出第一个大于target的位置 参考34题
                int mid = left + (right - left) / 2;
                if (res.get(mid) >= num){
                    right = mid - 1;
                    index = mid;
                }else{
                    left = mid + 1;
                }
            }

            res.set(index, num);
        }

        return res.size();
    }


    public static void main(String[] args) {
        System.out.println(lengthOfLIS2(new int[]{10,9,2,5,3,7,101,18}));
    }
}
