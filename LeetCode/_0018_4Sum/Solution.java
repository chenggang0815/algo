package LeetCode._0018_4Sum;
/*
18. 4Sum

Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
    1. 0 <= a, b, c, d < n
    2. a, b, c, and d are distinct.
    3. nums[a] + nums[b] + nums[c] + nums[d] == target
You may return the answer in any order.

Example 1:
Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

Example 2:
Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]

Constraints:
1 <= nums.length <= 200
-109 <= nums[i] <= 109
-109 <= target <= 109
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
Solution:
As you see, 3Sum just wraps Two Sum in an outer loop.
As it iterates through each value v, it finds all pairs whose sum is equal to target - v using one of these approaches:
1. Two Sum uses a hash set to check for a matching value.
2. Two Sum II uses the two pointers pattern in a sorted array.

Following a similar logic, we can implement 4Sum by wrapping 3Sum in another loop.
But wait - there is a catch. If an interviewer asks you to solve 4Sum, they can follow-up with 5Sum, 6Sum, and so on.
What they are really expecting at this point is a kSum solution.
Therefore, we will focus on a generalized implementation here.

Approach 1: Two Pointers
1. the two pointers approach requires sorted array, so we do that first.
2. Also, it's easier to deal with duplicates if the array is sorted: repeated values are next to each other and easy to skip.
3. For 3Sum, we enumerate each value in a single loop, and use the two pointers pattern for the rest of the array.
4. For kSum, we will have k - 2 nested loops to enumerate all combinations of k - 2 values.


We can implement k - 2 loops using a recursion.
We will pass the starting point and k as the parameters.
When k == 2, we will call twoSum, terminating the recursion.

For the main function:
1. Sort the input array nums.
2. Call kSum with start = 0, k = 4, and target, and return the result.

For kSum function:
1. Check if the sum of k smallest values is greater than target, or the sum of k largest values is smaller than target.
   Since the array is sorted, the smallest value is nums[start], and largest - the last element in nums.
   1.1 If so, no need to continue - there are no k elements that sum to target.

2. If k equals 2, call twoSum and return the result.
3. Iterate i through the array from start:
    3.1 If the current value is the same as the one before, skip it.
    3.2 Recursively call kSum with start = i + 1, k = k - 1, and target - nums[i].
    3.3 For each returned subset of values:
        3.3.1 Include the current value nums[i] into subset.
        3.3.2 Add subset to the result res.
4. Return the result res.

For twoSum function:
1. Set the low pointer lo to start, and high pointer hi to the last index.
2. While low pointer is smaller than high:
    2.1 If the sum of nums[lo] and nums[hi] is less than target, increment lo.
        2.1.1 Also increment lo if the value is the same as for lo - 1.
    2.2 If the sum is greater than target, decrement hi.
        2.2 Also decrement hi if the value is the same as for hi + 1.
    2.3 Otherwise, we found a pair:
        2.3.1 Add it to the result res.
        2.3.2 Decrement hi and increment lo.
3. Return the result res.
 */

/*
target = 0
对n=4来说，递归到调用twoSum时，target = 0 -（a+b）=> 找到left和right后，res.add([a,b,left,right])
对n=5来说，递归到调用twoSum时，target = 0 -（a+b+c）=> 找到left和right后，res.add([a,b,c,left,right])
对n=6来说，递归到调用twoSum时，target = 0 -（a+b+c+d）=> 找到left和right后，res.add([a,b,c,d,left,right])
对n=7来说，递归到调用twoSum时，target = 0 -（a+b+c+d+e）=> 找到left和right后，res.add([a,b,c,d,e,left,right])
...
所以不可能把所有的a,b,c,d,e...传到twoSum这一层，只能在递归的上层+twoSum返回的left和right
*/
public class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return kSum(nums, target, 0, 4);
    }

    List<List<Integer>> kSum(int[] nums, long target, int start, int k) {
        List<List<Integer>> res = new ArrayList<>();
        long average_value = target / k;
        if (start == nums.length || nums[start] > average_value || average_value > nums[nums.length - 1]) return res;

        if (k == 2) return twoSum(nums, target, start);

        for (int i = start; i < nums.length; i++){
            if (i == start || nums[i - 1] != nums[i]){
                for (List<Integer> subset : kSum(nums, target - nums[i], i + 1, k - 1)) {
                    res.add(new ArrayList<>(Arrays.asList(nums[i])));
                    res.get(res.size() - 1).addAll(subset);
                }
            }
        }

        return res;
    }

    List<List<Integer>> twoSum(int[] nums, long target, int start) {
        List<List<Integer>> res = new ArrayList<>();
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < target || (left > start && nums[left] == nums[left - 1])) left++;
            else if (sum > target || (right < nums.length - 1 && nums[right] == nums[right + 1])) right--;
            else res.add(Arrays.asList(nums[left++], nums[right--]));
        }

        return res;
    }

    /*
    static List<List<Integer>> fourSum(int[] nums, int target){
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        fourSum(nums, target, 0,4, res);

        return res;
    }

    static void fourSum(int[] nums, int target, int start, int n, List<List<Integer>> res){
        if (n == 2){
            twoSum(nums, target, start, res);
            return;
        }

        for (int i = start; i < nums.length; i++){
            if (i == start || nums[i - 1] != nums[i]){
            fourSum(nums, target - nums[i], i + 1, n - 1, res);
            }
        }
    }

    static void twoSum(int[] nums, int target, int start, List<List<Integer>> res){
        int left = start;
        int right = nums.length - 1;
        while (left < right){
            int sum = nums[left] + nums[right];
            if (sum > target && (right < nums.length - 1 && nums[right] == nums[right + 1])){
                right--;
            }else if (sum < target && (left > start && nums[left] == nums[left - 1])){
                left++;
            }else {
                res.add(Arrays.asList(target, start, nums[left], nums[right]));
                left++;
                right--;
            }
        }
    }*/

    public static void main(String[] args) {
        int[] nums = new int[]{1,0,-1,0,-2,2};
        int target = 0;
    }
}
