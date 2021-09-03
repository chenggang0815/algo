package com.LeetCode._0901_Online_Stock_Span;
import java.util.*;

/*
901. Online Stock Span
Design an algorithm that collects daily price quotes for some stock and returns the span of that stock's price for the current day.
The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backward) for which the stock price was less than or equal to today's price.

For example,
if the price of a stock over the next 7 days were [100,80,60,70,60,75,85],
then the stock spans would be [1,1,1,2,1,4,6].
Implement the StockSpanner class:

StockSpanner() Initializes the object of the class.
int next(int price) Returns the span of the stock's price given that today's price is price.

Constraints:
1. 1 <= price <= 105
2. At most 104 calls will be made to next.
*/
/*

Example 1:
Input
["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
[[], [100], [80], [60], [70], [60], [75], [85]]
Output
[null, 1, 1, 1, 2, 1, 4, 6]

Explanation
StockSpanner stockSpanner = new StockSpanner();
stockSpanner.next(100); // return 1
stockSpanner.next(80);  // return 1
stockSpanner.next(60);  // return 1
stockSpanner.next(70);  // return 2
stockSpanner.next(60);  // return 1
stockSpanner.next(75);  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
stockSpanner.next(85);  // return 6
*/

/*
解题思路：
1. 暴力解法
time:o(n) space:o(n)
use a array stores all the number, every time, given a price, use a for loop find the first index => array[index] >= price

2. 单调栈
time: o(1) space:o(n)
     100 80 60 70 60 75 85
span  1  1  1  2  1  4  6

when price = 85 the stack is:
75  4
80  1
100 1
res = 1 + 4 + 1 = 6
stack is:
100 1

use a stack store the price and the spans, the initial spans = 1
define a stack => stack<int[]> => [price, span]
when price > stack.peek()[0] => res += stack.peek()[1]
 */
public class StockSpanner {

//    private Stack<Integer> stack;
//    private ArrayList<Integer> array;
//    public StockSpanner(){
//        array = new ArrayList<>();
//    }
//    int next(int num){
//        array.add(num);
//        stack = new Stack<>();
//        int res = 1;
//        for (int i = array.size() - 2; i >= 0; i--){
//            if (array.get(i) >= num) return res;
//            res++;
//        }
//
//        return res;
//    }

    private Stack<int[]> stack;
    public StockSpanner(){
       stack = new Stack<>();
    }

    int next(int price){
        int res = 1;
        while (!stack.isEmpty() && price > stack.peek()[0]){
            res += stack.pop()[1];
        }
        stack.push(new int[]{price, res});
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{100,80,60,70,60,75,85};

        StockSpanner stockSpanner = new StockSpanner();
        System.out.println(stockSpanner.next(100)); // 1
        System.out.println(stockSpanner.next(80)); // 1
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(70));  // return 2
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(75));  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner.next(85));  // return 6

    }
}
