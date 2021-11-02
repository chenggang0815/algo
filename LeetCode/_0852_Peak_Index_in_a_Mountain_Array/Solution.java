package LeetCode._0852_Peak_Index_in_a_Mountain_Array;
/*
852. Peak Index in a Mountain Array
Example 1:
Input: arr = [0,1,0]
Output: 1

Example 2:
Input: arr = [0,2,1,0]
Output: 1

Example 3:
Input: arr = [0,10,5,2]
Output: 1

Example 4:
Input: arr = [3,4,5,1]
Output: 2

Example 5:
Input: arr = [24,69,100,99,79,78,67,36,26,19]
Output: 2

Constraints:
3 <= arr.length <= 104
0 <= arr[i] <= 106
arr is guaranteed to be a mountain array.
 */

/*
Solution 1:
    0, 1, 3,             10,  5,   2         target=10
    nums[i]<nums[i+1]   nums[i]>nums[i+1] => find the first number => meet nums[i]>nums[i+1]

写法1：用一个变量代替答案
写法2：最后left==right就是要找的变量

Solution 2:
    0, 1, 3,             10,  5,   2         target=10
    nums[i]<nums[i+1]   nums[i]>nums[i+1] => find the number => meet nums[i]>nums[i+1] && nums[i-1]<nums[i]
*/
public class Solution {
    // solution 1
    static int search1(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        int res = 0;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]){
                right = mid - 1;
                res = mid;
            }else {
                left = mid + 1;
            }
        }

        return res;
    }

    // solution 2
    // 0 3 2 1 0
    //
    static int search2(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid - 1] < nums[mid] && nums[mid] > nums[mid + 1]) return mid;
            if (nums[mid] > nums[mid + 1]){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }

        return -1;
    }
/*
    0, 1, 3,             10,  5,   2         target=10
    nums[i]<nums[i+1]   nums[i]>nums[i+1] => find the first number => meet nums[i]>nums[i+1]
 */
    // solution 1
    static int search3(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]){ // find the first number => meet nums[i]>nums[i+1]
                right = mid;
            }else {
                left = mid + 1;
            }
        }

        return left;
    }

    public static void main(String[] args) {
   //     System.out.println(search1(new int[]{18,29,38,59,98,100,99,98,90}));
        System.out.println(search3(new int[]{0,1,0}));
    }
}
