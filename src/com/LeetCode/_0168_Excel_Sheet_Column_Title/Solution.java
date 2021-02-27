package com.LeetCode._0168_Excel_Sheet_Column_Title;
/*
168. Excel Sheet Column Title
Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB
    ...
Example 1:
Input: 1
Output: "A"

Example 2:
Input: 28
Output: "AB"

Example 3:
Input: 701
Output: "ZY"
 */

/*
输入数字转字母 与 171题相反

0进制转换为26进制。本身不难，但是因为其去掉了数字0，只有1-9因而增加了难度。实际上我们会发现，只要每次计算时将n先自减1（n -= 1）然后再取余数，除以26就刚好可以了

作者：jyj407
链接：https://leetcode-cn.com/problems/excel-sheet-column-title/solution/zhong-gui-zhong-ju-168-excelbiao-lie-min-802g/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    static String convertToTitle1(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0){
            char ch;
            if (n > 26){
                if (n % 26 == 0) ch = 'Z';
                else ch = (char)('A' + n % 26 - 1);
            }else {
                ch = (char) ('A' + n - 1);
            }
            if (n == 26){
                sb.append(ch);
                break;
            }
            n = n / 26;

            sb.append(ch);
            ///if (n == 1) break;
        }
        System.out.println(sb.toString());
        return sb.reverse().toString();
    }

    static String convertToTitle2(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0){
            n -= 1;
            int rem = n % 26;
            sb.append((char)(rem + 'A'));
            n /= 26;
        }
        return sb.reverse().toString();
    }
    public static void main(String[] args) {
        System.out.println(convertToTitle2(52));
    }
}
