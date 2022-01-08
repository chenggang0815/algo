package Facebook._0282_Expression_Add_Operators;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
282. Expression Add Operators
Given a string num that contains only digits and an integer target, return all possibilities to insert the binary operators '+', '-', and/or '*' between the digits of num so that the resultant expression evaluates to the target value.
Note that operands in the returned expressions should not contain leading zeros.

Example 1:
Input: num = "123", target = 6
Output: ["1*2*3","1+2+3"]
Explanation: Both "1*2*3" and "1+2+3" evaluate to 6.

Example 2:
Input: num = "232", target = 8
Output: ["2*3+2","2+3*2"]
Explanation: Both "2*3+2" and "2+3*2" evaluate to 8.

Example 3:
Input: num = "3456237490", target = 9191
Output: []
Explanation: There are no expressions that can be created from "3456237490" to evaluate to 9191.

Constraints:
1. 1 <= num.length <= 10
2. num consists of only digits.
3. -231 <= target <= 231 - 1
*/
/*
Solution
Approach 1: backTracking
*/
public class Solution {
    public List<String> addOperators(String num, int target) {
        if (num.length() == 0) return new ArrayList<>();

        List<String> res = new LinkedList<>();
        dfs(res, num, target, "", 0, 0, 0);

        return res;
    }

    void dfs(List<String> res, String num, int target, String expr, long calcVal, long preNum, int pos){
        if (pos == num.length()){
            if (calcVal == target) res.add(expr);
            return;
        }

        for (int i = pos; i < num.length(); i++){
            if (i != pos && num.charAt(pos) == '0') break;

            long curNum = Long.parseLong(num.substring(pos, i + 1));
            if (pos == 0) dfs(res, num, target, expr + curNum, curNum, curNum, i + 1);
            else{
                dfs(res, num, target, expr + "+" + curNum, calcVal + curNum, curNum, i + 1);
                dfs(res, num, target, expr + "-" + curNum, calcVal - curNum, -curNum, i + 1);
                dfs(res, num, target, expr + "*" + curNum, calcVal - preNum + preNum * curNum, preNum * curNum, i + 1);
            }
        }
    }

    public static void main(String[] args) {

    }
}
