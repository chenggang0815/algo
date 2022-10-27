package LeetCode._0150_Evaluate_Reverse_Polish_Notation;
import java.util.Stack;

/*
150. Evaluate Reverse Polish Notation
Evaluate the value of an arithmetic expression in Reverse Polish Notation.
Valid operators are +, -, *, and /. Each operand may be an integer or another expression.

Example 1:
Input: tokens = ["2","1","+","3","*"]
Output: 9
Explanation: ((2 + 1) * 3) = 9

Example 2:
Input: tokens = ["4","13","5","/","+"]
Output: 6
Explanation: (4 + (13 / 5)) = 6
*/

/*
思路：用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中

逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。

逆波兰表达式主要有以下两个优点：
1. 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
2. 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中
*/

/*
“==”比较两个变量本身的值，即两个对象在内存中的首地址。
“equals()”比较字符串中所包含的内容是否相同。
比如：
String s1,s2,s3 = "abc", s4 ="abc" ;
s1 = new String("abc");
s2 = new String("abc");
那么：
s1==s2 是 false //两个变量的内存地址不一样，也就是说它们指向的对象不 一样，
故不相等。
s1.equals(s2) 是 true //两个变量的所包含的内容是abc，故相等

if (tokens[i] == "+" || tokens[i] == "-" || tokens[i] == "*" || tokens[i] =="/")

*/

/*
@beOkWithAnything
String实例是对象，判断String相等就应该用equals()而不是简单的==。
== 是判断两个引用指向的是不是一个对象。至于 为什么ide能用 == 判断，肯定是因为你们用了
String[] s = {"1"};
的形式创建的字符串数组，此时"1"被放在常量池，在evalRPN函数再用"1"会直接指向常量池里之前创建的"1"，
即两个引用指向的是同一个对象，所以能相等。
而leetcode上tokens里的字符串都是通过 new String("1");方式创建的对象，所以==不相等必须要equals，至于jdk版本，，，，依我看不是jdk的问题
 */
public class Solution {
    // ["4","13","5","/","+"]
    // [4, 13, 5]
    // "/" => 13/5=2 => [4,2]
    // "+" => 4+2 => 6

    // ["10","6","9","3","+","-11","*","/","*","17","+","5","+"]
    // [10, 6, 9, 3]
    // "+" => [10, 6, 12]
    // [10, 6, 12,-11]
    // "*" => [10, 6, -132]
    // "/" => 6/-132=0 => [10, 0]
    // "*" => 10*0 = 0 =>[0]
    // [0, 17]
    // "+" => 0 + 17 = 17 => [17]
    // [17, 5]
    // "+" => 17 + 5 => 22
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for(String token: tokens){
            if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                if(token.equals("+")) stack.push(b + a);
                else if(token.equals("-")) stack.push(b - a);
                else if(token.equals("*")) stack.push(b * a);
                else stack.push(b / a);
            }else stack.push(Integer.valueOf(token));
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        //System.out.println(evalRPN(new String[]{"2","1","+","3","*"}));
    }
}
