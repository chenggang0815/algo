package LeetCode._0310_Minimum_Height_Trees;
/*
310. Minimum Height Trees

A tree is an undirected graph in which any two vertices are connected by exactly one path.
In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1,
and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree,
you can choose any node of the tree as the root.

When you select a node x as the root, the result tree has height h.
Among all possible rooted trees, those with minimum height (i.e. min(h)) are called minimum height trees (MHTs).
Return a list of all MHTs' root labels. You can return the answer in any order.
The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

Example 1:
Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
*/

/*
1. 首先，我们看了样例，发现这个树并不是二叉树，是多叉树。
2. 然后，我们可能想到的解法是：根据题目的意思，就挨个节点遍历bfs，统计下每个节点的高度，然后用map存储起来，后面查询这个高度的集合里最小的就可以了。
3. 但是这样会超时的。
4. 于是我们看图（题目介绍里面的图）分析一下，发现，越是靠里面的节点越有可能是最小高度树。
5. 所以，我们可以这样想，我们可以倒着来。
6. 我们从边缘开始，先找到所有出度为1的节点，然后把所有出度为1的节点进队列，然后不断地bfs，
   最后找到的就是两边同时向中间靠近的节点，那么这个中间节点就相当于把整个距离二分了，
   那么它当然就是到两边距离最小的点啦，也就是到其他叶子节点最近的节点了。

*/
public class Solution {

    public static void main(String[] args) {

    }
}
