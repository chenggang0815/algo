package Other._42_和为S的两个数字;
/*

和为S的两个数字

输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。

输出描述:
对应每个测试案例，输出两个数，小的先输出。
 */

import java.util.ArrayList;

/*
思路： 双指针

第一个就是乘积最小的，证明：周长相同的正方形和长方形，长方形面积总小于正方形
eg： 1 2 3 4 5 6  s=7  (1, 6) (2,
5) (3, 4)
 */
public class Solution {

    // time:o(n) space: o(1)
    static ArrayList<Integer> FindNumbersWithSum(int [] array, int sum) {
        if (array.length == 0 || array[array.length - 1] * 2 < sum || array[0] * 2 > sum) {
            return  new ArrayList<>();
        }

        int left = 0;
        int right = array.length - 1;
        ArrayList<Integer> res = new ArrayList<>();
        while (left < right){
            if (array[left] + array[right] == sum){
                res.add(array[left]);
                res.add(array[right]);
                break;
            }else if (array[left] + array[right] > sum){
                right--;
            }else {
                left++;
            }
        }

        return res;
    }
        public static void main(String[] args) {
            //int[] nums = new int[]{1,2,3,4,5,6};
            int[] nums = new int[0];
            System.out.println(FindNumbersWithSum(nums, 7));
    }
}
