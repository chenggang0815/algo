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

solution 1: two pointers time:O(nlog(n) + n^2) => O(n^2)
input = [-1,0,1,2,-1,-4], target = 0
1. first, sort => [-4,-1,-1,0,1,2]
2. target=0-(-4)=4          -1  -1  0  1  2
                           left         right
3. target = 4  => then use two pointers to search the [left, right] part of the array => make nums[left] + nums[right] = - target
4. for loop + two_sum_II(two_pointers)

for example :
edge case 1:
inputs = 0 0 0 0 0
         i l     r => l=1, r=4, find a solution =>  step 1 => while(l < r && nums[l] == nums[l+1]) l++ => l=3,r=4
                                                   step 2 => l++, r-- => l=4 r=3 => break out while
edge case 2:
inputs = -2 -1 0 1 2 3 4 5
                 i => if nums[i] > 0 => break => the number on the right side of k is all > 0, we can not find a solution make nums[i]+nums[j]+nums[k]=0

edge case 3:
inputs = -1 -1 0 1 2
            i => if i > 0 && nums[i] == nums[i-1] => continue =>  skip the duplicate solution

solution 2: hashSet

solution 3: No-sort

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
/*
time: O(nlog(n) + n^2) => O(nlog(n))
space: O(log(n))  depending on the implementation of the sorting algorithm
*/
public class Solution {
    static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++){
            if (nums[i] > 0) break; // if k > 0 => the number on the right side of k is all > 0, we can not find a solution
            if(i != 0 && nums[i] == nums[i- 1]) continue; // if we have two duplicate number, we need to skip the second duplicate number not the first one, because the first number may use second number to generate an answer
            int left = i + 1;
            int right = nums.length - 1;
            while(left < right){
                if(nums[left] + nums[right] > -nums[i]){
                    right--;
                }else if(nums[left] + nums[right] < -nums[i]){
                    left++;
                }else{
                    res.add(Arrays.asList(nums[left], nums[right], nums[i]));
                    while(left < right && nums[left] == nums[left + 1]) left++;
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

        System.out.println(Arrays.asList("a","b","c"));
    }
}
