package Amazon._0001_Two_Sum;

import java.util.Arrays;
import java.util.HashMap;

/*
1. Two Sum
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
You may assume that each input would have exactly one solution, and you may not use the same element twice.
You can return the answer in any order.

Example 1:
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Output: Because nums[0] + nums[1] == 9, we return [0, 1].

Example 2:
Input: nums = [3,2,4], target = 6
Output: [1,2]

Example 3:
Input: nums = [3,3], target = 6
Output: [0,1]

Constraints:
2 <= nums.length <= 104
-109 <= nums[i] <= 109
-109 <= target <= 109
Only one valid answer exists.
*/

/*
solution 1 : Brute Force solution
two for loop

solution 2: hashmap + two pass

HashMap<num, index>
for example [3, 3] target = 6  =>  map.containsKey(target - nums[i]) && i != map.get(target - nums[i])

solution 3: one pass + hashmap

 */
public class Solution {
    // O(n^2)
    static int[] findTwoSum1(int[] nums, int target){
        int[] res = new int[2];
        for(int i = 0; i < nums.length - 1; i++){
           for (int j = i + 1; j < nums.length; j++){
               if (nums[i] + nums[j] == target){
                   res[0] = i;
                   res[1] = j;
               }
           }
        }

        return res;
    }

    // time: O(n) space: O(n)
    static int[] findTwoSum2(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++){
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])){
                res[0] = i;
                res[1] = map.get(target - nums[i]);
            }
        }

        return res;
    }

    // time: O(n) space: O(n)
    static int[] findTwoSum3(int[] nums, int target){
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++){
            if (map.containsKey(target - nums[i]) && i != map.get(target - nums[i])){
                res[0] = i;
                res[1] = map.get(target - nums[i]);
            }
            map.put(nums[i], i);
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findTwoSum3(new int[]{2,7,11,15}, 9)));
    }
}
