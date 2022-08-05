package LeetCode._0258_Add_Digits;
/*
258. Add Digits

Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.

Example:
Input: 38
Output: 2
Explanation: The process is like: 3 + 8 = 11, 1 + 1 = 2.
             Since 2 has only one digit, return it.
Follow up:
Could you do it without any loop/recursion in O(1) runtime?
*/
/*
除了传统的单纯循环，还可以找规律。假如一个三位数'abc'，其值大小为s1 = 100 * a + 10 * b + 1 * c，经过一次各位相加后，
变为s2 = a + b + c，减小的差值为(s1 -s2) = 99 * a + 9 * b，差值可以被9整除，每一个循环都这样，缩小了9的倍数。
1. 当num小于9，即只有一位时，直接返回num
2. 大于9时，
   2.1 如果能被9整除，则返回9（因为不可能返回0也不可能返回两位数及以上的值）=> eg:90 81 27 => return 9
   2.2 如果不能被整除，就返回被9除的余数
*/

/*
其实这里求的就是数根 参见wiki https://zh.wikipedia.org/wiki/數根

这里采用一种简单推导规律来实现：
数字 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
树根 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9 1 2 3 4 5 6 7 8 9

发现规律
按照9来翻转，那么就是用 (i - 1)%9 +1

具体数学证明等参见知乎的高赞回答 如何证明一个数的数根(digital root)就是它对9的余数？ - 曙方的回答 - 知乎
https://www.zhihu.com/question/30972581/answer/50203344

作者：ffreturn
链接：https://leetcode-cn.com/problems/add-digits/solution/cshuang-bai-de-jian-dan-gui-lu-tui-dao-b-kk3r/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    //
    static int addDigits1(int num) {
        int res = num;
        while (res > 9){
            String s = String.valueOf(res);
            int temp = 0;
            for (int i = 0; i < s.length(); i++){
                temp = temp + (s.charAt(i) - '0');
            }
            res = temp;
        }

        return res;
    }

    static int addDigits2(int num) {
        if (num > 9){
            num = num % 9;
            if (num == 0) return 9;
        }
        return num;
    }

    public static void main(String[] args) {
        System.out.println(addDigits1(38));
    }
}
