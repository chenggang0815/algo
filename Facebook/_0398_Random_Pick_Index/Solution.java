package Facebook._0398_Random_Pick_Index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/*
398. Random Pick Index
Given an integer array nums with possible duplicates, randomly output the index of a given target number. You can assume that the given target number must exist in the array.

Implement the Solution class:
    1. Solution(int[] nums) Initializes the object with the array nums.
    2. int pick(int target) Picks a random index i from nums where nums[i] == target. If there are multiple valid i's, then each index should have an equal probability of returning.

Example 1:
Input
["Solution", "pick", "pick", "pick"]
[[[1, 2, 3, 3, 3]], [3], [1], [3]]
Output
[null, 4, 0, 2]
Explanation
Solution solution = new Solution([1, 2, 3, 3, 3]);
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
solution.pick(1); // It should return 0. Since in the array only nums[0] is equal to 1.
solution.pick(3); // It should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
*/
/*
Solution
1. Approach 1: hashMap time: initialize:O(n) pick:O(1) space:O(n)

2. Approach 2: Reservoir sampling time: initialize:O(1) pick:O(n) space:O(1)
for example:
    [1, 2, 3, 3, 3] target=3
   i=0  1  2  3  4
   index=[2,3,4] for each index, the probability is 1/3

result = 0
count = 0
when i = 2, => count++ => rand.nextInt(1) => result = 2, p=1
when i = 3 => count++ => rand.nextInt(2) => result = 2, p=1/2; result = 3, p = 1/2
when i = 4 => count++ => rand.nextInt(2) => result = 2, p=2/3;result = 3, p=2/3; result = 4, p = 1/3
so,
for 2 => p=1*1/2*2/3=1/3
for 3 => p=1/2*2/3=1/3
for 4 => p=1/3

*/
public class Solution {
    HashMap<Integer, List<Integer>> map = new HashMap<>();;
    public Solution(int[] nums) {
        //System.out.print(Arrays.toString(nums));
        for(int i = 0; i < nums.length; i++){
            if(!map.containsKey(nums[i])) map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }
    }

    public int pick(int target) {
        List<Integer> list = map.get(target);
        double rand = Math.random();
        double p = 1.0 / list.size();
        //System.out.print(rand + "\n");
        //System.out.print(p + "\n");

        int index = (int) (rand / p);
        //System.out.print(index + "\n");

        return list.get(index);

    }

//    Random rand;
//    int[] nums;
//    public Solution(int[] nums) {
//        this.nums = nums;
//        rand = new Random();
//    }
//
//    public int pick(int target) {
//        int count = 0;
//        int result = 0;
//        for(int i = 0; i < nums.length; i++){
//            if(nums[i] != target) continue;
//            count++;
//            if(rand.nextInt(count) == 0){
//                result = i;
//            }
//        }
//
//        return result;
//    }
    public static void main(String[] args) {
        Random rand = new Random();
        System.out.println(rand.nextInt(1));
    }
}
