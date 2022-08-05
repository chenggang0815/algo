package Facebook._0921_Minimum_Add_to_Make_Parentheses_Valid;
/*
921. Minimum Add to Make Parentheses Valid

A parentheses string is valid if and only if:
It is the empty string,
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.
You are given a parentheses string s. In one move, you can insert a parenthesis at any position of the string.

For example, if s = "()))", you can insert an opening parenthesis to be "(()))" or a closing parenthesis to be "())))".
Return the minimum number of moves required to make s valid.

Example 1:
Input: s = "())"
Output: 1

Example 2:
Input: s = "((("
Output: 3

Constraints:
1 <= s.length <= 1000
s[i] is either '(' or ')'.
*/
/*
Solution
Approach time:O(n) space:O(1)
1. we use two variable to record the number of left and right parentheses
2. if current char == '(' => left++
3. if current char == ')':
    if left > 0 => we can make a valid parentheses, left--;
    else right++ => we have a extra right parentheses
return the left+right is the extra parentheses

for example:
    ( ) ) ( ( (
    left=3
    right=1
*/
public class Solution {
    public int minAddToMakeValid(String s) {
        //Stack<Character> stack = new Stack<>();
        int right = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '(') left++;
            else{
                if(left > 0) left--;
                else right++;
            }
        }

        return left + right;
    }

    public static void main(String[] args) {

    }
}
