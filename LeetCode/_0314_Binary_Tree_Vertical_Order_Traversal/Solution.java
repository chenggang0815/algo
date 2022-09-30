package LeetCode._0314_Binary_Tree_Vertical_Order_Traversal;

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
*/
/*
Approach 2: DFS
用dfs解这道题，可能会出现同一个列，下面的节点在上面的节点前面的情况，不符合题目要求， root-left-right
所以我们需要同时记录每个节点的column 和 row,再根据column和row来排序
但是用bfs，我们可以保证从上到下，从左到右的顺序
    public List<List<Integer>> verticalOrder(TreeNode root) {
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        int[] col = new int[2];
        dfs(root, 0, map, col);

        List<List<Integer>> res = new ArrayList<>();
        for(int i = col[0]; i <= col[1]; i++){
            res.add(new ArrayList<>(map.get(i)));
        }

        return res;
    }

    void dfs(TreeNode root, int column, HashMap<Integer, List<Integer>> map, int[] col){
        if(root == null) return;

        col[0] = Math.min(col[0], column);
        col[1] = Math.max(col[1], column);
        if(!map.containsKey(column)){
            map.put(column, new ArrayList<>());
        }
        map.get(column).add(root.val);

        dfs(root.left, column - 1, map, col);
        dfs(root.right, column + 1, map, col);
    }
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
        if(root == null) return res;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxCol = 0;
        int minCol = 0;
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, new ArrayList<>());
        map.get(0).add(root.val);
        HashMap<TreeNode, Integer> nodeColumn = new HashMap<>();
        nodeColumn.put(root, 0);
        while(!queue.isEmpty()){
            int cnt = queue.size();
            while(cnt > 0){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.add(node.left);
                    int curColumn = nodeColumn.get(node) - 1;
                    nodeColumn.put(node.left, curColumn);
                    minCol = Math.min(minCol, curColumn);

                    if(!map.containsKey(curColumn)) map.put(curColumn, new ArrayList<>());
                    map.get(curColumn).add(node.left.val);
                }
                if(node.right != null){
                    queue.add(node.right);
                    int curColumn = nodeColumn.get(node) + 1;
                    nodeColumn.put(node.right, curColumn);
                    maxCol = Math.max(maxCol, curColumn);

                    if(!map.containsKey(curColumn)) map.put(curColumn, new ArrayList<>());
                    map.get(curColumn).add(node.right.val);
                }
                cnt--;
            }
        }

        for(int i = minCol; i <= maxCol; i++){
            res.add(new ArrayList<>(map.get(i)));
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
