package LeetCode._0171_Excel_Sheet_Column_Number;
/*
171. Excel Sheet Column Number
Given a column title as appear in an Excel sheet, return its corresponding column number.
For example:
    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28
    ...
Example 1:
Input: "A"
Output: 1

Example 2:
Input: "AB"
Output: 28

Example 3:
Input: "ZY"
Output: 701
 */
/*
输入字母转数字 与168题相反

1. 标签：字符串遍历，进制转换
2. 初始化结果 ans = 0，遍历时将每个字母与 A 做减法，因为 A 表示 1，所以减法后需要每个数加 1，计算其代表的数值 num = 字母 - ‘A’ + 1
3. 因为有 26 个字母，所以相当于 26 进制，每 26 个数则向前进一位
4. 所以每遍历一位则ans = ans * 26 + num
5. 以 ZY 为例，Z 的值为 26，Y 的值为 25，则结果为 26 * 26 + 25=701
6. 时间复杂度：O(n)

作者：guanpengchn
链接：https://leetcode-cn.com/problems/excel-sheet-column-number/solution/hua-jie-suan-fa-171-excelbiao-lie-xu-hao-by-guanpe/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    static int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++){
            int nums = s.charAt(i) - 'A' + 1;
            res = res * 26 + nums;
        }

        return res;
    }
    public static void main(String[] args) {
        System.out.println(titleToNumber("ZY"));
    }
}
