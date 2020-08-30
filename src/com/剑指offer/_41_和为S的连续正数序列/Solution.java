package com.剑指offer._41_和为S的连续正数序列;
import java.util.ArrayList;

/*
和为S的连续正数序列

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。
（序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。）
eg1:
input: 5
output: 23

思路一 ： 双指针
因为是连续的数，采用双指针方法，两个指针都从left = right = 1 开始，每次移动指针都更新序列和sum
1. 如果sum < target , 需要right++ 再 sum = sum + right
2. sum > target , 需要sum = sum - left 再 left++
3. 如果sum == target 将[left，right]的值记下，sum = sum - left; left++
4. 指针只能从左往右移动，并且left <= target / 2
 */
public class Solution {
    // time: o(target) space: o(1)
    static ArrayList<ArrayList<Integer>> FindContinuousSequence(int target) {
        int left = 1;
        int right = 1;
        int sum = 1;
        ArrayList<ArrayList<Integer>> array = new ArrayList<>();
        while (left <= target / 2){
            if (sum < target){
                right++;
                sum = sum + right;
            }else if (sum > target){
                sum = sum - left;
                left++;
            }else {
                ArrayList<Integer> temp = new ArrayList<>();
                for (int i = left; i <= right; i++){
                    temp.add(i);
                }
                array.add(temp);
                sum = sum - left;
                left++;
            }
        }
        return array;
    }

    public static void main(String[] args) {

        System.out.println(FindContinuousSequence(100));
    }
}
