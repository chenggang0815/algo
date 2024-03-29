package LeetCode._0104_Maximum_Depth_of_Binary_Tree;
import java.util.LinkedList;
import java.util.Queue;
/*
104. Maximum Depth of Binary Tree

Given a binary tree, find its maximum depth.
Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its depth = 3.
The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */

/*
Solution
Approach 1 dfs time: O(n) space:O(height)

Approach 2 bfs time: O(n) space:O(n)
1. bfs - v1
2. bfs - v2
*/
public class Solution {

    static class TreeNode{
        int val;
        TreeNode right;
        TreeNode left;
        TreeNode(int val){
            this.val = val;
        }
    }
    /*
    1. 如果一棵树只有一个结点，那么它的深度为1；
    2. 如果根结点只有左子树没有右子树，那么树的深度是左子树的深度加1，加1是加上根节点这一层
    3. 如果既有左子树又有右子树，那么树的深度应该是左、右子树中深度较大的值再加1
     */

    // time: o(n) space: o(n)
    static int maxDepth(TreeNode root){
        if (root == null) return 0;

        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    //bfs - v1
    static int maxDepth2(TreeNode root){
        if (root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            int cnt = queue.size();
            while (cnt > 0){
                TreeNode node = queue.poll();
                if (node.right != null) queue.add(node.right);
                if (node.left != null) queue.add(node.left);
                cnt--;
            }
            depth++;
        }

        return depth;
    }

    // bfs - v2
    public int maxDepth3(TreeNode root) {
        if(root == null) return 0;

        int depth = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            depth++;
            int k = queue.size();
            for(int i = 0; i < k; i++){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
        }

        return depth;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node2.right = node4;
        node2.left = node3;

        System.out.println(maxDepth(root));










    }

}
