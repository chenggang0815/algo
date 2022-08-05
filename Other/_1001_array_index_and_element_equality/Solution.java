package Other._1001_array_index_and_element_equality;
/*
Array Index & Element Equality

Given a sorted array arr of distinct integers,
write a function indexEqualsValueSearch that returns the lowest index i for which arr[i] == i.
Return -1 if there is no such index.
Analyze the time and space complexities of your solution and explain its correctness.

Examples:
input: arr = [-8,0,2,5] output: 2 # since arr[2] == 2
input: arr = [-1,0,3,6] output: -1 # since no index in arr satisfies arr[i] == i.
*/
/*
使用「二分」进行查值，需要确保序列本身满足「二段性」
=> 当选定一个端点（基准值）后，结合「一段满足 & 另一段不满足」的特性来实现“折半”的查找效果
=> 只需要有三岐性即可，大于小于等于 相当于这里的上坡下坡和顶峰

Solution 1:
because
1. all the number on the left side of target index => meet requirement => nums[mid] < mid
2. all the number on the right side of target index => meet requirement => nums[mid] > mid
3. find the number => meet number[mid] == mid
    // -3 -2 -1         3    6 7 9
    // 0  1  2          3    4 5 6
    // nums[mid] < mid     nums[mid] > mid
    // find nums[mid] == mid

Solution 2:
1. all the number on the left side of target index => meet requirement => nums[mid] < mid
2. all the number on the right side of target index => meet requirement => nums[mid] > mid
3. find the number => meet number[mid] == mid
    // -3 -2 -1           3 6 7 9
    // 0  1  2            3 4 5 6
    // nums[mid] < mid    nums[mid] >= mid
    // find first nums[mid] >= mid
*/
public class Solution {
    // solution1
    static int search1(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (mid == nums[mid]) return mid;
            if (nums[mid] < mid){
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }

        return -1;
    }

    static int search2(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        int res = -1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (nums[mid] >= mid){
                right = mid - 1;
                res = mid;
            }else {
                left = mid + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(search2(new int[]{-3, -2, -1, 3, 6, 7, 9}));
    }
}
