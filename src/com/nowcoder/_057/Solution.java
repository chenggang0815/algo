package com.nowcoder._057;

import java.util.ArrayList;
import java.util.Arrays;

/*
把字符串转换成整数

将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 数值为0或者字符串不是一个合法的数值则返回0
 */

/*
思路：

在每轮数字拼接前，判断res在此轮拼接后是否超过 2147483647，若超过则加上符号位直接返回。
设数字拼接边界 binary = 2147483647 // 10 = 214748364，则以下两种情况越界：

1. res > binary  情况一：执行拼接：10 × res ≥ 2147483650越界
2. res = binary, x > 7  情况二：拼接后是：2147483648 或 2147483649越界
 */
public class Solution {

    // time: o(n)  space: o(n)
    static int StrToInt(String str) {
        if (str.length() == 0) return 0;

        ArrayList<Character> array = new ArrayList<>(Arrays.asList('+', '-', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        int sum = 0;
        int label = 1;
        int binary = Integer.MAX_VALUE / 10;
        for (int i = 0; i < str.length(); i++){
            if (array.contains(str.charAt(i))){
                if (str.charAt(i) == '+'){
                    label = 1;
                }else if (str.charAt(i) == '-'){
                    label = -1;
                }else {
                    if(sum > binary || sum == binary && str.charAt(i) > '7') return label == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                    sum = sum * 10 + str.charAt(i) - '0';
                }
            }else {
                return 0;
            }
        }

        return label * sum;
    }

    public static void main(String[] args) {
        String str = "-2147483649";
        //String str = "-123";
        System.out.println(StrToInt(str));

    }
}
