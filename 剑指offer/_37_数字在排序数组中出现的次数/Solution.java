package 剑指offer._37_数字在排序数组中出现的次数;
/*
数字在排序数组中出现的次数

思路1 : 双指针
1. 分别从左右遍历，直到 nums[left] = nums[right] 代表遍历完所有数字，有两种情况
 a. 如果 nums[left] = nums[right] = k => return right - left + 1
 b. 如果 nums[left] = nums[right] != k => return 0


思路2 ： 二分法

分别用二分法找出目标值的left和right的临界坐标， return right - left - 1；

例如  1, 2, 2, 2, 5 left的index = 0；right的index = 4
1. 找右边时，有 if(mid >= target) i = mid + 1 需要把等号放在左移i的这边
2. 找左边时，有 if(mid <= target) j = mid - 1 需要把等号放在右移j的这边
 */
public class Solution {
    //time:o(n) space:o(1)
    static int GetNumberOfK(int [] array , int k) {
    if(array.length == 0) return 0;
    if(array[0] > k || array[array.length - 1] < k) return 0;

    int left = 0;
    int right = array.length - 1;
    while(left < right){
        if(array[left] < k ){
            left++;
        }
        if(array[right] > k && left < right){
            right--;
        }
        if (array[left] == array[right] && array[left] == k){
            break;
        }else if (array[left] == array[right] && array[left] != k){
            return 0;
        }
    }

    return right - left + 1;
    }

    // time: o(log(n)) space: o(1)
    static int GetNumberOfK2(int [] array , int k) {
        if(array.length == 0) return 0;
        if(array[0] > k || array[array.length - 1] < k) return 0;

        int i = 0;
        int j = array.length - 1;
        while (i <= j){
            int mid = i + (j - i) / 2;
            if (k >= array[mid]){
                i = mid + 1;
            }else {
                j = mid - 1;
            }
        }

        int right = i;
        i = 0;
        while (i <= j){
            int mid = i + (j - i) / 2;
            if (k <= array[mid]){
                j = mid - 1;
            }else{
                i = mid + 1;
            }
        }

        int left = j;

     //   System.out.println(right);
    //    System.out.println(left);
        return right - left - 1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 2, 2, 5};
        System.out.println(GetNumberOfK2(nums, 2));

    }
}
