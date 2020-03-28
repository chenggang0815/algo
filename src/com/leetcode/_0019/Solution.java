package com.leetcode._0019;

public class Solution {
    static class TreeNode{
        TreeNode left;
        TreeNode right;
        int val;
        TreeNode(int val){
            this.val = val;
        }
    }

    public static int pathSum(TreeNode root, int sum) {
        int num = 0;
        return pathLeft(root, sum, num) + pathRight(root,sum,num);
    }


    public static int pathRight(TreeNode root, int sum, int num){

        if(root==null) return num;

        TreeNode currentLeft = root;
        int s1 = currentLeft.val;
        while(currentLeft.left!=null){
            if(s1==sum){
                num++;
                break;
            }
            else{
                currentLeft = currentLeft.left;
                s1 = s1 + currentLeft.val;
                if (s1==sum){
                    num++;
                    break;
                }
            }
        }

        TreeNode currentRight = root;
        int s2 = currentRight.val;
        while(currentRight.right!=null){
            if(s2==sum){
                num++;
                break;
            }
            else{
                currentRight = currentRight.right;
                s2 = s2 + currentRight.val;
                //System.out.println("root:"+root.val);
                // System.out.println("s2:"+s2);
                if (s2==sum){
                    num++;
                    break;
                }
            }
        }

        System.out.println("root:"+root.val);
        System.out.println("num:"+num);
        System.out.println("===========");

        pathRight(root.right,sum,num);

        return num;
    }

    public static int pathLeft(TreeNode root, int sum, int num){

        if(root==null) return num;

        TreeNode currentLeft = root;
        int s1 = currentLeft.val;
        while(currentLeft.left!=null){
            if(s1==sum){
                num++;
                break;
            }
            else{
                currentLeft = currentLeft.left;
                s1 = s1 + currentLeft.val;
                if (s1==sum){
                    num++;
                    break;
                }
            }
        }

        TreeNode currentRight = root;
        int s2 = currentRight.val;
        while(currentRight.right!=null){
            if(s2==sum){
                num++;
                break;
            }
            else{
                currentRight = currentRight.right;
                s2 = s2 + currentRight.val;
                //System.out.println("root:"+root.val);
                // System.out.println("s2:"+s2);
                if (s2==sum){
                    num++;
                    break;
                }
            }
        }

        System.out.println("root:"+root.val);
        System.out.println("num:"+num);
        System.out.println("===========");

        return pathLeft(root.left,sum,num);

    }
    public static void main(String[] args) {

        TreeNode root = new TreeNode(10);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(-3);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(11);
        TreeNode node6 = new TreeNode(3);
        TreeNode node7 = new TreeNode(-2);
        TreeNode node8 = new TreeNode(1);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node3.left = node6;
        node3.right = node7;
        node4.right = node8;
        node2.right = node5;

        System.out.println(pathSum(root,8));


    }
}
