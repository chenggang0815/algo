package LeetCode._0096_Unique_Binary_Search_Trees;
/*
96. Unique Binary Search Trees

Given an integer n, return the number of structurally unique BST's (binary search trees)
which has exactly n nodes of unique values from 1 to n.
 */

/*
动态规划：
1. 给定一个有序序列1⋯n，为了构建出一棵二叉搜索树，我们可以遍历每个数字i，将该数字作为root
2. 将1⋯(i−1)序列作为左子树，将(i+1)⋯n序列作为右子树
3. 接着我们可以按照同样的方式递归构建左子树和右子树。
4. 在上述构建的过程中，由于根的值不同，因此我们能保证每棵二叉搜索树是唯一的

G(n): 长度为n的序列能构成的不同二叉搜索树的个数
F(i,n): 以i为根、序列长度为n的不同二叉搜索树个数(1≤i≤n)。

5. G(n)，是对遍历所有i(1≤i≤n)的F(i,n)之和
6. F(i,n) = G(i−1)⋅G(n−i) => 根为i的所有二叉搜索树的集合是左子树集合和右子树集合的笛卡尔积
7. 递推公式: G(n)是对遍历所有i(1≤i≤n)的G(i−1)⋅G(n−i)之和

 */
public class Solution {
    // time：o(n^2) space:o(n)
    static int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < n + 1; i++){
            for (int j = 1; j <= i; j++){
                dp[i] = dp[i] + dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];

    }

    public static void main(String[] args) {
        System.out.println(numTrees(4));
    }
}
