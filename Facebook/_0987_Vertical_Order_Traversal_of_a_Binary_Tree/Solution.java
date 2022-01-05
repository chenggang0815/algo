package Facebook._0987_Vertical_Order_Traversal_of_a_Binary_Tree;

import java.util.*;

/*
987. Vertical Order Traversal of a Binary Tree

Given the root of a binary tree, calculate the vertical order traversal of the binary tree.

For each node at position (row, col), its left and right children will be at positions (row + 1, col - 1) and (row + 1, col + 1) respectively. The root of the tree is at (0, 0).

The vertical order traversal of a binary tree is a list of top-to-bottom orderings for each column index starting from the leftmost column and ending on the rightmost column. There may be multiple nodes in the same row and same column. In such a case, sort these nodes by their values.

Return the vertical order traversal of the binary tree.

                 1
               /  \
              2    3
            /  \  /  \
           4   6 5   7
Input: root = [1,2,3,4,6,5,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation:
This case is the exact same as example 2, but with nodes 5 and 6 swapped.
Note that the solution remains the same since 5 and 6 are in the same location and should be ordered by their values.
*/
/*
Solution
Approach 1: BFS
1. use a map to store the tree node and position => Map<TreeNode, int[]> => map.put(0, [0,0])
2. use a map to store each column, Map<column, List<TreeNode>>
3. from minColumn to maxColumn, for each column, sort by the row index of the position and value of node
   list = map.get(column)
   list.sort((a, b) -> map.get(a)[0] == map.get(b)[0] ? a.val - b.val : map.get(a)[0] - map.get(b)[0])
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

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        HashMap<TreeNode, int[]> map = new HashMap<>();
        HashMap<Integer, List<TreeNode>> column = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        map.put(root, new int[]{0, 0});
        column.put(0, new ArrayList<>());
        column.get(0).add(root);
        int maxColumn = 0;
        int minColumn = 0;
        while(!queue.isEmpty()){
            TreeNode node = queue.poll();
            int[] index = map.get(node);
            int row = index[0];
            int col = index[1];
            if(node.left != null){
                map.put(node.left, new int[]{index[0] + 1, col - 1});
                if(!column.containsKey(col - 1)){
                    column.put(col - 1, new ArrayList<>());
                }
                column.get(col - 1).add(node.left);
                queue.add(node.left);
                minColumn = Math.min(minColumn, col - 1);
            }
            if(node.right != null){
                map.put(node.right, new int[]{index[0] + 1, col + 1});
                if(!column.containsKey(col + 1)){
                    column.put(col + 1, new ArrayList<>());
                }
                column.get(col + 1).add(node.right);
                queue.add(node.right);
                maxColumn = Math.max(maxColumn, col + 1);
            }
        }

        // 1 6 5
        // 1 [0,0]
        // 6 [2, 0]
        // 5 [2, 0]
        List<List<Integer>> res = new ArrayList<>();
        for(int i = minColumn; i <= maxColumn; i++){
            if(column.containsKey(i)){
                List<TreeNode> list = column.get(i);
                list.sort((a, b) -> map.get(a)[0] == map.get(b)[0] ? a.val - b.val : map.get(a)[0] - map.get(b)[0]);

                List<Integer> temp = new ArrayList<>();
                for(TreeNode node : list) temp.add(node.val);
                res.add(temp);
            }
        }

        return res;
    }
    public static void main(String[] args) {

    }
}
