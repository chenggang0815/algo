package Facebook._0958_Check_Completeness_of_a_Binary_Tree;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/*
958. Check Completeness of a Binary Tree
Given the root of a binary tree, determine if it is a complete binary tree.
In a complete binary tree, every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example 1:
                     1
                   /  \
                  2    3
                 / \  /
                4  5 6
Input: root = [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.

Example 2:
                     1
                   /  \
                  2    3
                 / \    \
                4   5    7
Input: root = [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
*/
/*
Solution
Approach 1: bfs
1. we can use a variable to record the index of the node
2. for a node, if the index of it != the expected node, which means it's not a complete tree
         1
       /   \
      2     3
     /      / \
    5       7 8

    node => index
    node.left => 2 * index
    node.right => 2 * index + 1

*/
public class Solution {
    class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public boolean isCompleteTree(TreeNode root) {
        HashMap<TreeNode, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int index = 1;
        map.put(root, index);
        while(!queue.isEmpty()){
            int cnt = queue.size();
            while(cnt > 0){
                TreeNode node = queue.poll();
                if(index != map.get(node)) return false;
                //if(node.left == null && node.right != null) return false;
                //if(cnt > 1 && (node.left == null || node.right == null)) return false;
                if(node.left != null){
                    queue.add(node.left);
                    map.put(node.left, index * 2);
                }
                if(node.right != null){
                    queue.add(node.right);
                    map.put(node.right, index * 2 + 1);
                }
                index++;
                cnt--;
            }
        }

        return true;
    }
    public static void main(String[] args) {

    }
}
