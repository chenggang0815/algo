package LeetCode._0046_Permutations;
import java.util.ArrayList;
import java.util.List;
/*
46. Permutations
Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.

Example 1:
Input: nums = [1,2,3]
Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]

Example 2:
Input: nums = [0,1]
Output: [[0,1],[1,0]]

Example 3:
Input: nums = [1]
Output: [[1]]

Constraints:
1 <= nums.length <= 6
-10 <= nums[i] <= 10
All the integers of nums are unique.
*/

/*
Solution
Approach: backtracking

*/
/*
recursion tree
             []
          /  |   \
         1   2   3
      /  |  \
     1   2   3
     a / | \
      1  2 3
      b c  d

a,b,c => exists the repetitive number
d => a valid permutation

1. stop condition => temp.size() == nums.length => find a valid permutation
2. prune(early stopping to reduce computation)
   2.1 for each for loop, we iterate the array from left to right, and avoid exists the repetitive number
   2.2 so, for(int i = 0; i < nums.length; i++) //for each lead digit, iterate from left to right
   2.3 if(temp.contains(nums[i])) continue; //prune
3. backtracking => temp.remove(temp.size() - 1)
*/
public class Solution {

    static public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack1(nums, res, new ArrayList<>());
        //backtrack2(nums, res, new ArrayList<>(), new int[nums.length]);

        return res;
    }


    static void backtrack1(int[] nums, List<List<Integer>> res, List<Integer> temp){
        if (temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            // contains() method requires O(n) time
            if (temp.contains(nums[i])) continue;
            temp.add(nums[i]);
            System.out.println("    递归之前 => " + temp);
            backtrack1(nums, res, temp);
            temp.remove(temp.size() - 1);
            System.out.println("递归之后 => " + temp);
        }
    }

    static void backtrack2(int[] nums, List<List<Integer>> res, List<Integer> temp, int[] flag){
        if(temp.size() == nums.length){
            res.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(flag[i] == 1) continue;
            flag[i] = 1;
            temp.add(nums[i]);
            backtrack2(nums, res, temp, flag);
            flag[i] = 0;
            temp.remove(temp.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};
        System.out.println(permute(nums));
    }
}
