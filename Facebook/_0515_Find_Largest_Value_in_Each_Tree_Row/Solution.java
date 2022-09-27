package Facebook._0515_Find_Largest_Value_in_Each_Tree_Row;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
515. Find Largest Value in Each Tree Row
Given the root of a binary tree, return an array of the largest value in each row of the tree (0-indexed).
Example 1:
            1
          /   \
         3     2
       /  \     \
      5   3      9
Input: root = [1,3,2,5,3,null,9]
Output: [1,3,9]
*/
/*
Solution
Approach 1: BFS

Approach 2: DFS
1. must preorder traversal and a depth variable to record the current depth of tree in the recursion.
2. if we first reach the current level, we have => depth == res.size() then we add a number in the res
3. else the current level is note the first reach, we compare => res.set(depth, max(root.val, res.get(depth)))
*/
public class Solution {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res, root, 0);

        return res;
    }

    void dfs(List<Integer> res, TreeNode root, int depth){
        if(root == null) return;

        if(depth == res.size()) res.add(root.val); // first time reach the depth's level, add the current value in the res
        else res.set(depth, Math.max(res.get(depth), root.val)); // reach the depth's level again, compare the value
        dfs(res, root.left, depth + 1);
        dfs(res, root.right, depth + 1);
    }

    public List<Integer> largestValues2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int cnt = queue.size();
            int maxNode = Integer.MIN_VALUE;
            while(cnt > 0){
                TreeNode node = queue.poll();
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
                maxNode = Math.max(node.val, maxNode);
                cnt--;
            }
            res.add(maxNode);
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
