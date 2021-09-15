package LeetCode._0114_Flatten_Binary_Tree_to_Linked_List;
/*
114. Flatten Binary Tree to Linked List

Given the root of a binary tree, flatten the tree into a "linked list":

1. The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
2. The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
import java.util.ArrayList;
import java.util.List;
/*
solution 1:
1. 递归前序遍历，用list记下每个node的顺序
2. 遍历list，对当前节点执行操作
time: o(n) space:o(n)

solution 2: time: o(n) space:o(1)
具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，
将当前节点的右子节点赋给前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，
并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。


可以发现展开的顺序其实就是二叉树的先序遍历。算法和 94 题中序遍历的 Morris 算法有些神似，我们需要两步完成这道题。

1. 将左子树插入到右子树的地方
2. 将原来的右子树接到左子树的最右边节点
3. 考虑新的右子树的根节点，一直重复上边的过程，直到新的右子树为 null
    1
   / \
  2   5
 / \   \
3   4   6

//将1的左子树插入到右子树的地方
    1
     \
      2         5
     / \         \
    3   4         6
//将原来的右子树接到左子树的最右边节点
    1
     \
      2
     / \
    3   4
         \
          5
           \
            6

 //将 2 的左子树插入到右子树的地方
    1
     \
      2
       \
        3       4
                 \
                  5
                   \
                    6

 //将原来的右子树接到左子树的最右边节点
    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6

  ......

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

    static void dfs(TreeNode root, List<TreeNode> list){
        if (root == null) return;
        list.add(root);
        dfs(root.left, list);
        dfs(root.right, list);
    }
    static void flatten1(TreeNode root) {
        if (root == null) return;
        List<TreeNode> list = new ArrayList<>();
        dfs(root, list);

        for (int i = 0; i < list.size() - 1; i++){
            list.get(i).right = list.get(i + 1);
            list.get(i).left = null;
        }
    }

    static void flatten2(TreeNode root){
        while (root != null){
            //左子树为 null，直接考虑下一个节点
            if (root.left == null){
                root = root.right;
            }else{
                // 找左子树最右边的节点
                TreeNode pre = root.left;
                while (pre.right != null){
                    pre = pre.right;
                }
                //左子树的最右边节点 指向 原来的右子树
                pre.right = root.right;
                // 将左子树插入到右子树的地方
                root.right = root.left;
                root.left = null;
                // 考虑下一个节点
                root = root.right;
            }
        }

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(8);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(6);
        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.right = node5;
        flatten2(root);
        TreeNode cur = root;
        while (cur != null){
            System.out.println(cur.val);
            cur = cur.right;
        }
    }
}
