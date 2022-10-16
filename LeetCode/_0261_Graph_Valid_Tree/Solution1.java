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
solution 1: disjoint - set / UnionFind
1. detect if a cycle in the graph => if parents[node1] == parents[node2] => exists a cycle
2. detect if only exists a graph or more than one graph
for example:  Input: n = 4, edges = [[0,1],[2,3]]
 0   2
 |   |
 1   3
is not a valid tree
*/
public class Solution1 {
    class UnionFind{
        int[] parent;
        public UnionFind(int n){
            parent = new int[n];
            for(int i = 0; i < n; i++) parent[i] = i;
        }

//        int findParent(int i){
//            while(i != parent[i]){
//                i = parent[i];
//            }
//
//            return parent[i];
//        }
        //
        int findParent(int i){
            if (i == parent[i]) return parent[i];

            return findParent(parent[i]);
        }

        void merge(int i, int j){
            int p1 = findParent(i);
            int p2 = findParent(j);
            parent[p1] = p2;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for(int[] edge : edges){
            int p1 = uf.findParent(edge[0]);
            int p2 = uf.findParent(edge[1]);
            if(p1 == p2){
                return false;
            }else{
                uf.merge(p1, p2);
                n--;
            }
        }

        return n <= 1;
    }

    public static void main(String[] args) {

    }
}
