package LeetCode._0098_Validate_Binary_Search_Tree;

public class Solution2 {
    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
        }
    }

    // inorder traversal
    TreeNode pre = null;
    boolean isBST = true;
    public boolean isValidBST(TreeNode root) {
        dfs(root);

        return isBST;
    }

    void dfs(TreeNode root){
        if(root == null) return;

        dfs(root.left);

        if(pre != null && pre.val >= root.val) isBST = false;

        pre = root;
        dfs(root.right);
    }
    public static void main(String[] args) {

    }
}
