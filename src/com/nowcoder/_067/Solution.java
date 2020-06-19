package com.nowcoder._067;
/*
二叉搜索树的后序遍历序列

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
如果是则输出Yes,否则输出No。
假设输入的数组的任意两个数字都互不相同。

思路1: 递归
后续遍历是 left -> right -> root ； 又因此是二叉搜索树，left < right
1. 从left开始遍历，找到一个大于root结点（index=right）的值 mid， [left, mid-1]为根结点的左子树，[mid, right - 1] 为右子树
2. 递归遍历左右子树，直到 left >= right ，表示子结点数 <=1
3. 因为左子树全部小于右子树，还需要满足[left, mid - 1] < root && [mid. right - 1] > root

思路2: 单调栈
 */
public class Solution {
    // time: o(n^2) space: o(n)
    static boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) return false;

        return helper(sequence, 0, sequence.length - 1);
    }

    static boolean helper(int[] sequence, int left, int right){
        if (left >= right) return true;

        int index = left;
        while (sequence[index] < sequence[right]) index++;
        int mid = index;
        while (sequence[index] > sequence[right]) index++;
        // right == index 满足[left, mid - 1] < root && [mid. right - 1] > root
        return right == index && helper(sequence, left, mid - 1) && helper(sequence, mid, right - 1);
    }

    public static void main(String[] args) {

    }
}
