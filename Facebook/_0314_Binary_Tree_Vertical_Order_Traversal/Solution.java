package Facebook._0314_Binary_Tree_Vertical_Order_Traversal;

import java.util.*;

/*
314. Binary Tree Vertical Order Traversal
Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
If two nodes are in the same row and column, the order should be from left to right.
example
                        3
                       / \
                     9     8
                    / \   / \
                   4  0  1  7
                      /   \
                     5    2
Input: root = [3,9,8,4,0,1,7,null,null,null,2,5]
Output: [[4],[9,5],[3,0,1],[8,2],[7]]
*/
/*
Solution
Approach 1: BFS time:O(n) space:O(n)
1. bfs traverse the binary tree, for each node, get it's column => HashMap<TreeNode, integer>
2. for each column, the traverse order is top -> bottom (left -> right) => HashMap<column, List<Integer>>
3. maintain the max column and minimum column
4. from max column to minimum column, add map.get(column) to the result list

Approach 2: DFS
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

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        HashMap<Integer, List<Integer>> columnMap = new HashMap<>();
        HashMap<TreeNode, Integer> nodeColumn = new HashMap<>();

        int maxColumn = 0;
        int minColumn = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        nodeColumn.put(root, 0);
        columnMap.put(0, new ArrayList<>());
        columnMap.get(0).add(root.val);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                int column = nodeColumn.get(node) + 1;
                maxColumn = Math.max(column, maxColumn);
                nodeColumn.put(node.left, column);
                if (!columnMap.containsKey(column)) {
                    columnMap.put(column, new ArrayList<>());
                }
                columnMap.get(column).add(node.left.val);
            }
            if (node.right != null) {
                queue.add(node.right);
                int column = nodeColumn.get(node) - 1;
                minColumn = Math.min(minColumn, column);
                nodeColumn.put(node.right, column);
                if (!columnMap.containsKey(column)) {
                    columnMap.put(column, new ArrayList<>());
                }
                columnMap.get(column).add(node.right.val);
            }
        }

        for (int i = maxColumn; i >= minColumn; i--) {
            if (columnMap.containsKey(i)) {
                res.add(columnMap.get(i));
            }
        }


        return res;
    }

    public static void main(String[] args) {

    }
}
