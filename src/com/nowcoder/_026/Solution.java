package com.nowcoder._026;
//二叉树的基本操作
/*
前序，中序，后序遍历是以根结点的访问次序划分的（左子树先于右子树）：
	前序遍历：根节点->左子树->右子树
	中序遍历：左子树->根节点->右子树
    后序遍历：左子树->右子树->根节点
 */
public class Solution {
     static Node root;

     static Node find(int data) {
        Node current = root;
        while (current != null) {
            if (data < current.data) current = current.left;
            else if (data > current.data) current = current.right;
            else return current;
        }
        return null;
    }

     static void insert(int data) {

        if (root == null) {
            root = new Node(data);
            return;
        }

        Node current = root;
        while (current != null) {
            if (data > current.data) {
                if (current.right == null) {
                    current.right = new Node(data);
                    return;
                }
                current = current.right;
            } else { // data < p.data
                if (current.left == null) {
                    current.left = new Node(data);
                    return;
                }
                current = current.left;
            }
        }
    }
// 中序遍历（左->中->右 ；两层递归，先在左子树递归，后在每棵左子树的右子树递归）
    public static void infixOrder(Node current){
        if(current!=null){
            infixOrder(current.left);
            System.out.println(current.data+"");
            infixOrder(current.right);
        }
        return;
    }

// 前序遍历：中-》左-》右
    public static void preOrder(Node current){
        if (current!=null){
            System.out.println(current.data+"");
            preOrder(current.left);
            preOrder(current.right);
        }
        return;
    }

// 后序遍历:左-》右-》中
    public static void postOrder(Node current){
        if (current!=null){
            postOrder(current.left);
            postOrder(current.right);
            System.out.println(current.data+"");
        }
    }

// 删除结点
    public void delete(int data) {
        Node p = root; // p指向要删除的节点，初始化指向根节点
        Node pp = null; // pp记录的是p的父节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) p = p.right;
            else p = p.left;
        }
        if (p == null) return; // 没有找到

        // 要删除的节点有两个子节点
        if (p.left != null && p.right != null) { // 查找右子树中最小节点
            Node minP = p.right;
            Node minPP = p; // minPP表示minP的父节点
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data; // 将minP的数据替换到p中
            p = minP; // 下面就变成了删除minP了
            pp = minPP;
        }

        // 删除节点是叶子节点或者仅有一个子节点
        Node child; // p的子节点
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) root = child; // 删除的是根节点
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

	//寻找最小的结点
    public Node findMin() {
        if (root == null) return null;
        Node p = root;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }
//寻找最大的结点
    public Node findMax() {
        if (root == null) return null;
        Node p = root;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    //树的节点类
    public static class Node {
         int data;
         Node left;
         Node right;
         Node(int data) {
            this.data = data;
        }
    }


    public static void main(String[] args) {

        insert(6);
        insert(7);
        insert(4);
        insert(2);
        insert(5);
//
        postOrder(root);

    }
}