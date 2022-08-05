package LeetCode._0402_Remove_K_Digits;

import java.util.LinkedList;

/*
402. Remove K Digits

Given string num representing a non-negative integer num, and an integer k, return the smallest possible integer after removing k digits from num.

Example 1:
Input: num = "1432219", k = 3
Output: "1219"
Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.

Example 2:
Input: num = "10200", k = 1
Output: "200"
Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.

Example 3:
Input: num = "10", k = 2
Output: "0"
Explanation: Remove all the digits from the number and it is left with nothing which is 0.

Constraints:
1 <= k <= num.length <= 105
num consists of only digits.
num does not have any leading zeros except for the zero itself.

Solution:
1. iterate the string, when current digit < pre_digit => can delete pre_digit
   //  num = 4  1  3
   1. initialize, list is empty, list.add(4)
   2. cur = 1, pre = 4, 4 > 1 => delete pre => num = 1 3
   // num = 1 2 3 0 , k=3
   // cur=0,pre=3 => delete 3; num=120 k=2
   // cur=0,pre=2 => delete 2; num=10 k=1
   // cur=0,pre=1 => delete 1; num=0 k=0
   // so we need a while condition to check if cur < pre

2. corner case, for number like 12345, k=2, we will not delete any number in step 1
   so if k > 0, we need to delete the last k number
   12345, delete 45 => 123

3. then we use a variable leadZero to check the lead zero,
   initialize the leadZero = true
   if(current char == '0' && leadZero) continue
   leadZero = false; => until we meet the first no-zero number

4. why we use LinkedList not stack, be cause we need to iterate the list from left to right
   // because we can iterate the char from first or last
 */
// 10001
//
public class Solution {
    static String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : num.toCharArray()){
            while (k > 0 && !stack.isEmpty() && ch < stack.getLast()){//注意要用while 而不是if, for example => num="1234567890" k=9
                stack.removeLast();
                k--;
            }
            stack.addLast(ch);
        }

        System.out.println(stack);

        for (int i = 0; i < k; i++){
            stack.removeLast();
        }

        System.out.println(stack);

        boolean leadZero = true;
        StringBuilder res = new StringBuilder();
        for (char ch : stack){
            if (ch == '0' && leadZero) continue;
            leadZero = false;
            res.append(ch);
        }

        if (res.length() == 0) return "0";

        return res.toString();
    }


    public static void main(String[] args) {
        //String num = "12345";
        //System.out.println(removeKdigits("1234567890", 9));
        LinkedList<String> stack = new LinkedList<>();
        stack.addLast("aa");
        stack.addLast("bb");
        stack.addFirst("cc");
        System.out.println(stack);
        System.out.println(stack.getFirst());
        System.out.println(stack.getLast());
        System.out.println(stack.peekLast());
    }

}




