package LeetCode._0261_Graph_Valid_Tree;
/*
261. Graph Valid Tree

You have a graph of n nodes labeled from 0 to n - 1.
You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
Return true if the edges of the given graph make up a valid tree, and false otherwise.

Example 1:
       0
     / | \
    1  2 3
   /
  4
Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
Output: true

Example 2:
   0 - 1 - 2
       | \|
      4  3
Input: n = 5, edges = [[0,1],[1,2],[2,3],[1,3],[1,4]]
Output: false
*/

/*
solution 1: disjoint - set
1. detect if a cycle in the graph => if parents[node1] == parents[node2] => exists a cycle
2. detect if only exists a graph
for example:  Input: n = 4, edges = [[0,1],[2,3]]
 0   2
 |   |
 1   3
is not a valid tree
*/
public class Solution {
    static public boolean validTree(int n, int[][] edges) {
        int[] parents = new int[n];
        for(int i = 0; i < n; i++){
            parents[i] = i;
        }
        int count = n;
        for(int i = 0; i < edges.length; i++){
            int p0 = find(parents, edges[i][0]);
            int p1 = find(parents, edges[i][1]);
            if(p0 != p1){
                parents[p0] = p1;
                count--;
            }else{
                return false;
            }
        }

        if(count > 1) return false;

        return true;
    }

    static int find(int[] parents, int i){
        while(i != parents[i]){
            i = parents[i];
        }

        return i;
    }

    public static void main(String[] args) {

    }
}
