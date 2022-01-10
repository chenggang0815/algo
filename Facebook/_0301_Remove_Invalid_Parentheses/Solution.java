package Facebook._0301_Remove_Invalid_Parentheses;
import java.util.ArrayList;
import java.util.List;
/*
301. Remove Invalid Parentheses
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
Return all the possible results. You may return the answer in any order.

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Input: s = ")("
Output: [""]

Constraints:
1 <= s.length <= 25
s consists of lowercase English letters and parentheses '(' and ')'.
There will be at most 20 parentheses in s.
*/
/*
https://www.youtube.com/watch?v=2k_rS_u6EBk

Solution
Approach 1: dfs
1. count we there are how many '(' and ')' we need to delete in the string
    1.1 use l denote the number of '('  we need to delete
    1.2 use r denote the number of ')' we need to delete
2. dfs delete the extra parentheses and if l == 0 and r == 0 and the current string is a valid parentheses string, we add s to the res
3. we just remove the first character, if we have two same continues character, to avoid duplicate
*/
public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int l = 0;
        int r = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                l++;
            }else if(s.charAt(i) == ')'){
                if(l == 0) r++;
                else l--;
            }
        }

        List<String> res = new ArrayList<>();
        dfs(s, res, l, r, 0);

        //System.out.print(isValid(s));

        return res;
    }

    boolean isValid(String s){
        int count = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                count++;
            }else if(s.charAt(i) == ')'){
                count--;
                if(count < 0) return false;
            }
        }

        return count == 0;
    }

    void dfs(String s, List<String> res, int l, int r, int start){
        if(l == 0 && r == 0 && isValid(s)){
            res.add(s);
            return;
        }

        for(int i = start; i < s.length(); i++){
            // skip duplicate, we just remove the first character, if we have two same continues character
            if(i != start && s.charAt(i) == s.charAt(i - 1)){
                continue;
            }

            if(l > 0 && s.charAt(i) == '('){
                dfs(s.substring(0, i) + s.substring(i + 1, s.length()), res, l - 1, r, i);
            }
            if(r > 0 && s.charAt(i) == ')'){
                dfs(s.substring(0, i) + s.substring(i + 1, s.length()), res, l, r - 1, i);
            }
        }
    }
    public static void main(String[] args) {

    }
}
