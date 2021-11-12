package Facebook._1249_Minimum_Remove_to_Make_Valid_Parentheses;

import java.util.HashSet;
import java.util.Stack;

/*
1249. Minimum Remove to Make Valid Parentheses

Given a string s of '(' , ')' and lowercase English characters.
Your task is to remove the minimum number of parentheses ( '(' or ')', in any positions ) so that the resulting parentheses string is valid and return any valid string.
Formally, a parentheses string is valid if and only if:
It is the empty string, contains only lowercase characters, or
It can be written as AB (A concatenated with B), where A and B are valid strings, or
It can be written as (A), where A is a valid string.

Example 1:
Input: s = "lee(t(c)o)de)"
Output: "lee(t(c)o)de"
Explanation: "lee(t(co)de)" , "lee(t(c)ode)" would also be accepted.

Example 2:
Input: s = "a)b(c)d"
Output: "ab(c)d"

Example 3:
Input: s = "))(("
Output: ""
Explanation: An empty string is also valid.

Example 4:
Input: s = "(a(b(c)d)"
Output: "a(b(c)d)"
*/

/*
solution:
1. when current char is ( => push it's index into stack
2. when current char is ) => 2.1 stack is not empty => stack.pop()
                          => 2.2 stack is empty => delete.add(i)
3. all the '(' in the stack need to be deleted
4. build a string builder, return

ex1: )() => ()
ex2: (())) => (())
when number of ) > number of ( => delete )

ex3: ((() => ()
sometime number of ( > number of ) => use a stack,
when meet a ( push it to the stack,
when meet a ) and the number of ) <= (, pop the top ( of stack, the remained ( is need to be deleted


stack = [0,1,2]
when meet )
stack = [0,1]
so we need to delete the index of 0 and 1

time complexity is O(n)
 */
public class Solution {
    static String minRemoveToMakeValid1(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        HashSet<Integer> delete = new HashSet<>();
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) == '('){
                stack.push(i);
            }else if(s.charAt(i) == ')'){
                if(!stack.isEmpty()){
                    stack.pop();
                    continue;
                }else{
                    delete.add(i);
                }
            }
        }
        while(!stack.isEmpty()) delete.add(stack.pop());
        for(int i = 0; i < s.length(); i++){
            if(delete.contains(i)) continue;
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }

    static String minRemoveToMakeValid2(String s) {
        Stack<Integer> stack = new Stack<>();
        int leftParentheses = 0;
        int rightParentheses = 0;

        HashSet<Integer> removeIndex = new HashSet<>();
        for (int i = 0; i < s.length(); i++){
            if (s.charAt(i) == '('){
                stack.push(i);
                leftParentheses++;
            }else if (s.charAt(i) == ')'){
                rightParentheses++;
                if (rightParentheses <= leftParentheses){
                    stack.pop();
                    leftParentheses--;
                    rightParentheses--;
                }else {
                    removeIndex.add(i);
                    rightParentheses--;
                }
            }
        }

        while (!stack.isEmpty()){
            removeIndex.add(stack.pop());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++){
            if (!removeIndex.contains(i)){
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();

    }

    public static void main(String[] args) {
        System.out.println(minRemoveToMakeValid1("(()))"));
    }
}
