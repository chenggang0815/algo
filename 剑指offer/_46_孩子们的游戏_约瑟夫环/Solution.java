package 剑指offer._46_孩子们的游戏_约瑟夫环;

import java.util.ArrayList;

/*
孩子们的游戏(圆圈中最后剩下的数)

0,1,,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字，再从下一个数字开始。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，
则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。


思路1：用数组模拟环，每次前进m的长度（不包括已经删除的元素），直到删除所有元素，返回最后删除的元素

思路2：用链表模拟，不过可以直接算出下一次要删除的元素的位置：
假设当前删除的位置是 x，下一个删除的数字的位置是 x + m 。但是，由于把当前位置的数字删除了，后面的数字会前移一位，
所以实际的下一个位置是x + m - 1。由于数到末尾会从头继续数，所以最后取模一下，就是 (x + m - 1) % n

 */
public class Solution {

    //time: o(n*m) n个数，每次循环m的长度 space: o(n)
    static int LastRemaining_Solution(int n, int m) {
        if (n == 0 || m == 0) return -1;

        int index = -1;
        int step = 0;
        int count = n;
        int[] nums = new int[n];
        while (count > 0){
            index++;
            if (index >= n) index = 0;
            if (nums[index] == -1) continue;
            step++;
            if (step == m){
                nums[index] = -1;
                step = 0;
                count--;
            }
        }

        return index;
    }

    //time: o(n*n) space: o(n)
    static int LastRemaining_Solution2(int n, int m) {
        if (n == 0 || m == 0) return -1;

        ArrayList<Integer> array = new ArrayList<>();
        for(int i = 0; i< n; i++){
            array.add(i);
        }
        int index = 0;
        while (n > 1){
            index = (index + m -1) % n;
            array.remove(index);
            n--;
        }

        return array.get(0);
    }

    public static void main(String[] args) {
        System.out.println(LastRemaining_Solution2(5, 3));
    }
}
