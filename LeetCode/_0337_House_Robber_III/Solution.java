package LeetCode._0337_House_Robber_III;
/*
在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。
除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。
如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。

计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。

 */
/*
树形动态规划

     3
    / \
   2   3
    \   \
     3   1

输出: 7
解释: 小偷一晚能够盗取的最高金额 = 3 + 3 + 1 = 7.

 */
public class Solution {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    static int money(TreeNode root){
        if (root == null) return 0;

        int res1 = 0;
        int res2 = root.val;
        if (root.left != null){
            res1 += root.left.val;
            if (root.left.left != null) res2 += root.left.left.val;
            if (root.left.right != null) res2 += root.left.right.val;
        }
        if (root.right != null){
            res1 += root.right.val;
            if (root.right.left != null) res2 += root.right.left.val;
            if (root.right.right != null) res2 += root.right.right.val;
        }

        return Math.max(res1, res2);
    }

    static void dfs(TreeNode root){
        if (root == null) return;
        res = Math.max(res, money(root));
        dfs(root.left);
        dfs(root.right);
    }
    static int res = 0;
    static int rob(TreeNode root) {

        dfs(root);

        return res;
    }

    static int rob2(TreeNode root) {
        if (root == null) return 0;

        int money = root.val;
        if (root.left != null) {
            money += (rob(root.left.left) + rob(root.left.right));
        }

        if (root.right != null) {
            money += (rob(root.right.left) + rob(root.right.right));
        }

        return Math.max(money, rob(root.left) + rob(root.right));
    }

    static int rob3(TreeNode root) {
        int[] result = robInternal(root);
        return Math.max(result[0], result[1]);
    }

    static int[] robInternal(TreeNode root) {
        if (root == null) return new int[2];
        int[] result = new int[2];

        int[] left = robInternal(root.left);
        int[] right = robInternal(root.right);

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = left[0] + right[0] + root.val;

        return result;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);

        root.left = node1;
        root.right = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        node4.right = node6;


//        TreeNode root = new TreeNode(1);
//        TreeNode node1 = new TreeNode(2);
//        TreeNode node2 = new TreeNode(3);
//        root.left = node1;
//        root.right = node2;
        System.out.println(rob3(root));
    }
}
