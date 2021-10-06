package LeetCode._0006_ZigZag_Conversion;

import java.util.ArrayList;
import java.util.List;

/*
6. ZigZag Conversion
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"
Write the code that will take a string and make this conversion given a number of rows:
string convert(string s, int numRows);

Example 1:
Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"

Example 2:
Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I

Example 3:
Input: s = "A", numRows = 1
Output: "A"

Constraints:
1 <= s.length <= 1000
s consists of English letters (lower-case and upper-case), ',' and '.'.
1 <= numRows <= 1000
*/
public class Solution {
    public String convert(String s, int numRows) {
        /*
        numRows = 3
        0   0   0
        1 1 1 1 1
        2   2   2
        i = > 0, 1, ..,numRows
        if(i == 0 || i == numRows - 1) flag = -flag

        */
        if(numRows == 1) return s; // corner case

        List<StringBuilder> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            list.add(new StringBuilder());
        }
        int i = 0;
        int flag = -1;
        for(char c: s.toCharArray()){
            list.get(i).append(c);
            if(i == 0 || i == numRows - 1) flag = - flag;
            i += flag;
        }

        StringBuilder res = new StringBuilder();
        for(StringBuilder str: list){
            res.append(str);
        }

        return res.toString();
    }
    public static void main(String[] args) {

    }
}
