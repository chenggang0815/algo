package LeetCode._0202_Happy_Number;

import java.util.HashSet;
/*
Example 1:

Input: n = 19
Output: true
Explanation:
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 02 = 1
 */
/*
思路1：用HashSet检测环
猜测会有以下三种可能：
1.最终会得到1。
2.最终会进入循环。
3.值会越来越大，最后接近无穷大（通过数学证明可以排除）

思路2：用快慢指针检测环
 */
public class Solution {

    static boolean isHappy1(int n) {
        HashSet<Integer> set = new HashSet<>();
        set.add(n);
        while (n != 1){
            n = helper(n); //每次生成链中的下一个数字时，我们都会检查它是否已经在 HashSet 中
            if (set.contains(n)) break; //如果它在 HashSet 中，这意味着我们处于一个循环中，因此应该跳出循环，判断当前数是否等于1
            set.add(n); //如果它不在 HashSet 中，我们应该添加它
        }

        return n == 1;
    }
    //数位分离，求平方和
    static int helper(int n){
        int res = 0;
        while (n > 0){
            int digits = n % 10;
            res = res + digits * digits;
            n = n / 10;
        }
        return res;
    }

    //思路2
    static boolean isHappy2(int n){
        int fast = n;
        int slow = n;
        while (true){
            slow = helper(slow);
            fast = helper(helper(fast));
            if (slow == fast) break;
        }

        return slow == 1;
    }

    public static void main(String[] args) {

        System.out.println(isHappy2(19));
    }
}
