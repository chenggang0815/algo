package Other._59_按之字形顺序打印二叉树;
import java.util.*;
/*
按之字形顺序打印二叉树

请实现一个函数按照之字形打印二叉树，
即第一行按照从左到右的顺序打印，
第二层按照从右至左的顺序打印，
第三行按照从左到右的顺序打印，其他行以此类推。


思路1：层次遍历，每次反转

思路2：层次遍历，双端队列

参考leetcode 103题
 */
public class Solution {
    static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    static public ArrayList<ArrayList<Integer> > Print(TreeNode root) {
        if(root == null) return new ArrayList<>();

        ArrayList<ArrayList<Integer>> array = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int num = 0;
        while(!queue.isEmpty()){
            int count = queue.size();
            ArrayList<Integer> temp = new ArrayList<>();
            while(count > 0){
                TreeNode node = queue.poll();
                temp.add(node.val);
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                count--;
            }

            if (num % 2 ==0){
                array.add(temp);
            }else{
                ArrayList<Integer> t = new ArrayList<>();
                for (int i = temp.size() - 1; i >= 0; i--){
                    t.add(temp.get(i));
                }
                array.add(t);
            }
            num++;
        }

        return array;
    }

  //  时间复杂度 O(N) ： N为二叉树的节点数量，即 BFS 需循环 N 次，占用 O(N)；双端队列的队首和队尾的添加和删除操作的时间复杂度均为 O(1) 。
  //  空间复杂度 O(N) ： 最差情况下，即当树为满二叉树时，最多有N/2个树节点 同时 在 deque 中，使用 O(N)大小的额外空间。

    static public ArrayList<LinkedList<Integer> > Print2(TreeNode root) {
        if(root == null) return new ArrayList<>();

        ArrayList<LinkedList<Integer>> array = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int count = queue.size();
            LinkedList<Integer> temp = new LinkedList<>();
            while(count > 0){
                TreeNode node = queue.poll();
                if (array.size() % 2 == 0){
                    temp.addFirst(node.val);
                }else {
                    temp.addLast(node.val);
                }
                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
                count--;
            }

            array.add(temp);
        }

        return array;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        System.out.println(Print2(root));
    }
}
