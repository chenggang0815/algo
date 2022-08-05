package LeetCode._0166_Fraction_to_Recurring_Decimal;

import java.util.HashMap;
import java.util.Map;

/*
166. Fraction to Recurring Decimal

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.
If the fractional part is repeating, enclose the repeating part in parentheses.
If multiple answers are possible, return any of them.
It is guaranteed that the length of the answer string is less than 104 for all the given inputs.

Example 1:
Input: numerator = 1, denominator = 2
Output: "0.5"

Example 2:
Input: numerator = 2, denominator = 1
Output: "2"

Example 3:
Input: numerator = 2, denominator = 3
Output: "0.(6)"
*/

/*
思路: 考虑所有的边界条件 => 正负号，int溢出，有无小数，小数是否循环
1. 先计算小数点前的数字
2. 然后计算小数点后的数字
3. 为了判断循环的产生，采用map+vector进行记录
4. vector记录所有出现过的numerator  map记录numerator在vector中的下标
5. 为什么要用两个数据结构， 因为小数点后有的位数不参与循环，而有的参与循环。
6. 如 1/6 = 0.1(6)

1 / 6 => res="0"
remainder = 1 % 6 = 1
res.append(".") => res="0."


map<remainder, res.length()> => [1,2]
remainder = 1 * 10 = 10
res.append(10/6) => res="0.1"
remainder = 10 % 6 = 4

map => [1,2] [4,3]
remainder = 4 * 10 = 40
res.append(40/6) => res="0.16"
remainder = 40 % 6 = 4  => remainder in map => res.insert(3, "("); => res.append(")"); => res="0.1(6)"
*/
public class Solution {
    static String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder res = new StringBuilder();
        //If either one is negative (not both)
        if (numerator < 0 ^ denominator < 0){
            res.append("-");
        }
        //Convert to Long or else abs(-2147483648) overflows
        // -2147483648 / 1 这个用例， -2147483648无法使用abs，必须使用long long存储
        long dividend = Math.abs(Long.valueOf(numerator));
        long divisor = Math.abs(Long.valueOf(denominator));
        res.append(String.valueOf(dividend / divisor));

        long remainder = dividend % divisor;
        //余数为0，表示整除了，直接返回结果
        if (remainder == 0) return res.toString();

        res.append("."); //余数不为0，添加小数点
        Map<Long, Integer> map = new HashMap<>(); //map用来记录出现重复数的下标，然后将'('插入到重复数前面就好了
        while (remainder != 0){
            if (map.containsKey(remainder)){
                res.insert(map.get(remainder), "(");
                res.append(")");
                break;
            }

            map.put(remainder, res.length());
            remainder = remainder * 10; //余数扩大10倍，然后求商
            res.append(String.valueOf(remainder / divisor));
            remainder = remainder % divisor;
        }

        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(1, 6));
    }
}
