package LeetCode._0129_Sum_Root_to_Leaf_Numbers;
import javafx.util.Pair;
import java.util.LinkedList;
import java.util.Queue;
/*
129. Sum Root to Leaf Numbers
You are given the root of a binary tree containing digits from 0 to 9 only.
Each root-to-leaf path in the tree represents a number.
For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
A leaf node is a node with no children.

for example:
Input:
             4
           /  \
          9    0
         / \
        5  1

Output: 1026
Explanation:
The root-to-leaf path 4->9->5 represents the number 495.
The root-to-leaf path 4->9->1 represents the number 491.
The root-to-leaf path 4->0 represents the number 40.
Therefore, sum = 495 + 491 + 40 = 1026.

Constraints:
1. The number of nodes in the tree is in the range [1, 1000].
2. 0 <= Node.val <= 9
3. The depth of the tree will not exceed 10.
*/

/*
Solution
Approach 1: bfs
1. queue<Pair<TreeNode, Integer>> => queue<Pair<node, sum>>

Approach 2: dfs
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
    // bfs
    static public int sumNumbers1(TreeNode root){
        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, root.val));
        int res = 0;
        while (!queue.isEmpty()){
            Pair<TreeNode, Integer> pair = queue.poll();
            TreeNode node = pair.getKey();
            int val = pair.getValue();
            if (node.left != null){
               int newVal  = Integer.parseInt(String.valueOf(val) + String.valueOf(node.left.val));
               queue.add(new Pair<>(node.left, newVal));
            }
            if (node.right != null){
                int newVal  = Integer.parseInt(String.valueOf(val) + String.valueOf(node.right.val));
                queue.add(new Pair<>(node.right, newVal));
            }
            if (node.right == null && node.left == null) res += val;
        }

        return res;
    }

    // dfs
    public int sumNumbers2(TreeNode root) {
        int[] res = new int[1];
        dfs(root, res, "");

        return res[0];
    }

    void dfs(TreeNode root, int[] res, String sum){
        if(root == null) return;

        if(root.left == null && root.right == null){
            sum += String.valueOf(root.val);
            res[0] += Integer.valueOf(sum);
            return;
        }

        sum += String.valueOf(root.val);
        dfs(root.left, res, sum);
        dfs(root.right, res, sum);
    }

    public static void main(String[] args) {
/*
   1
  / \
 2   3
    /
   4
*/
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        System.out.println(sumNumbers1(root));

    }
}
