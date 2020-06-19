package com.nowcoder._065;
/*
把数组排成最小的数
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。

思路：
1. 对于a b两个数，如果有 ab > ba 则应该把b排在a前面
2. 对于所有元素，冒泡排序比较
 */
public class Solution {
    // time:o(n^2) space:o(1)
    static String PrintMinNumber(int [] nums) {
        for (int i = 0; i < nums.length; i++){
            for (int j = 0; j < nums.length - 1; j++){
                String left = nums[j] + "" + nums[j + 1];
                String right = nums[j + 1] + "" + nums[j];
                if (left.compareTo(right) > 0){
                    int temp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        String res = "";
        for (int i = 0; i < nums.length; i++){
            res = res + "" + nums[i];
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(PrintMinNumber(new int[]{13, 6, 44}));
    }
}
