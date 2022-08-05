package LeetCode._0012_Integer_to_Roman;
/*
12. Integer to Roman
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000

I can be placed before V (5) and X (10) to make 4 and 9.
X can be placed before L (50) and C (100) to make 40 and 90.
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral.


Example 1:
Input: num = 3
Output: "III"

Example 2:
Input: num = 4
Output: "IV"

Example 3:
Input: num = 9
Output: "IX"

Example 4:
Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3.
 */

/*
thousands = {"", "M", "MM", "MMM"};
hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};


 */
public class Solution {
    public String intToRoman1(int num) {
        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[num / 1000] + hundreds[num % 1000 / 100] + tens[num % 100 / 10] + ones[num % 10];
    }
    /*
    for example num = 2654
    num >= 1000 => res = "M" num = num - 1000 = 1654
    num >= 1000 => res = "MM" num = 654
    num >= 500 => res = "MMD" num = 654-500=154
    num >= 100 => res="MMDC" num = 54
    num >= 50 => res="MMDCL" num=4
    num >=4 => res="MMDCLIV" num=0
     */
    static String intToRoman2(int num) {
        int[] values = {   1000, 900, 500, 400, 100, 90,  50,   40,  10,  9,   5,   4, 1};
        String[] symbols = {"M","CM", "D", "CD","C", "XC","L", "XL", "X","IX","V","IV","I"};

        StringBuilder sb = new StringBuilder();
        // Loop through each symbol, stopping if num becomes 0.
        for (int i = 0; i < values.length && num > 0; i++) {
            // Repeat while the current symbol still fits into num.
            while (values[i] <= num) {
                num -= values[i];
                sb.append(symbols[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman2(2654));
    }
}
