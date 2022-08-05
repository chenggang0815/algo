package Amazon._1120_Maximum_Average_Subtree;
import java.util.LinkedList;
import java.util.Queue;
/*
Given the root of a binary tree, return the maximum average value of a subtree of that tree. Answers within 10-5 of the actual answer will be accepted.
A subtree of a tree is any node of that tree plus all its descendants.
The average value of a tree is the sum of its values, divided by the number of nodes.

Example 1:
Input: root = [5,6,1]
Output: 6.00000
Explanation:
For the node with value = 5 we have an average of (5 + 6 + 1) / 3 = 4.
For the node with value = 6 we have an average of 6 / 1 = 6.
For the node with value = 1 we have an average of 1 / 1 = 1.
So the answer is 6 which is the maximum.
*/
/*
Solution:
Approach1:  time:O(n^2) space:o(n^2)
1. use bfs, iterate the tree, for each node => use dfs iterate the subtree, find the sum of the node.val and the number of node
2. tips: subtree is the root node plus all its descendants, so for a root node, subtree is unique.

Approach2: time:O(n) space:O(n)
1. for a node of subtree => sum(node) = node.val + sum(node.left) + sum(node.right)
                         => cnt(node) = 1 + cnt(node.left) + cnt(node.right)

2. so we can traverse tree from bottom to up, which means first visit child nodes, calculate the sum and cnt and then use these child nodes to solve parent nodes.

3. this order of tree traverse is postorder traversal. => left - right - root

4. so we can user postorder traversal to iterate the tree
    1
   / \
  2   3
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

    static double maximumAverageSubtree1(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        double res = 0;
        int[] nums = new int[2];
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            nums[0] = 0;
            nums[1] = 0;
            dfs(node, nums);
            res = Math.max(res, (double) nums[1] / nums[0]);
            if (node.right != null){
                nums[0] = 0;
                nums[1] = 0;
                dfs(node.right, nums);
                res = Math.max(res, (double) nums[1] / nums[0]);
                queue.add(node.right);
            }
            if (node.left != null){
                nums[0] = 0;
                nums[1] = 0;
                dfs(node.left, nums);
                res = Math.max(res, (double) nums[1] / nums[0]);
                queue.add(node.left);
            }
        }

        return res;
    }

    static void dfs(TreeNode root, int[] nums){
        if (root == null) return;

        nums[0]++;
        nums[1] += root.val;
        dfs(root.left, nums);
        dfs(root.right, nums);
    }

    public double maximumAverageSubtree2(TreeNode root) {
        double[] nums = new double[3];
        dfs(root, nums);

        return nums[2];
    }

    double[] dfs(TreeNode root, double[] nums){
        if(root == null) return new double[]{0, 0, 0};

        double[] left = dfs(root.left, nums);
        double[] right = dfs(root.right, nums);
        double sum = root.val + left[0] + right[0];
        double cnt = 1 + left[1] + right[1];
        nums[2] = Math.max(nums[2], (double) (sum / cnt));

        return new double[]{sum, cnt, nums[2]};
    }



    // use global variable
    double res = 0;
    public double maximumAverageSubtree3(TreeNode root) {
        dfs(root);

        return res;
    }

    int[] dfs(TreeNode root){
        if(root == null) return new int[]{0, 0};

        int[] left = dfs(root.left);
        int[] right = dfs(root.right);
        int sum = root.val + left[0] + right[0];
        int cnt = 1 + left[1] + right[1];
        res = Math.max(res, (double) sum / cnt);

        return new int[]{sum, cnt};
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        root.left = node1;
        root.right = node2;
        System.out.println(maximumAverageSubtree1(root));
        System.out.println((double) 2/10);
        System.out.println((double) (2/10));
    }
}
