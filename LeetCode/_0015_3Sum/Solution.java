package LeetCode._0015_3Sum;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/*
15. 3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
Notice that the solution set must not contain duplicate triplets.

Example 1:
Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
 */
/*
思路：
1. 先将数组进行排序
2. 从左侧开始（k=0），选定一个值为 定值 ，右侧进行求解，获取与其相加为0的两个值
3. 类似于快排，定义首（k+1）和尾（len-1）
4. （首+尾）与 定值 相加
    4.1 等于 0，记录这三个值 => 需要跳过等于nums[left]和nums[right]的重复元素
    4.2 小于 0，首部右移 => 需要跳过等于nums[left]的重复元素
    4.3 大于 0，尾部左移 => 需要跳过等于nums[right]的重复元素
5. 定值右移，重复该步骤 => 需要跳过等于nums[k]的重复元素 => if(nums[k] = nums[k-1]) continue;

tips:
1. 排序好后，if nums[k] > 0 => break
2. 在跳过重复元素时，循环条件 => while(left < right && nums[left] == nums[left+1]) {left++}
    2.1 有重复元素：在跳出循环时，nums[left]还是之前的值，nums[left+1]才是新的边界，所以还需要left++
    2.2 无重复元素：不进入循环，left左移 => left++
*/


public class Solution {
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int k = 0; k < nums.length - 2; k++){
            if (nums[k] > 0) break;
            if (k > 0 && nums[k] == nums[k - 1]) continue;
            int left = k + 1, right = nums.length - 1;
            while (left < right){
                int sum = nums[k] + nums[left] + nums[right];
                if (sum < 0){
                    while (left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    left++;
                }else if (sum > 0){
                    while (left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    right--;
                }else{
                    res.add(Arrays.asList(nums[k], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]){
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
        public static void main(String[] args) {
        int[] nums = new int[]{-1,0,1,2,-1,-4,-1,-1};
        System.out.println(threeSum(nums));
    }
}
