package Amazon._0323_Number_of_Connected_Components_in_an_Undirected_Graph;

import java.util.*;

/*
323. Number of Connected Components in an Undirected Graph
You have a graph of n nodes.
You are given an integer n and an array edges where edges[i] = [ai, bi] indicates that there is an edge between ai and bi in the graph.
Return the number of connected components in the graph.

Example 1:
Input: n = 5, edges = [[0,1],[1,2],[3,4]]
Output: 2

Example 2:
Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
Output: 1

Constraints:
1 <= n <= 2000
1 <= edges.length <= 5000
edges[i].length == 2
0 <= ai <= bi < n
ai != bi
There are no repeated edges.
*/

/*
solution 1: dfs

Here E = Number of edges, V = Number of vertices.
Time complexity: O(E+V).
Building the adjacency list will take O(E) operations, as we iterate over the list of edges once, and insert each edge into two lists.
During the DFS traversal, each vertex will only be visited once.
This is because we mark each vertex as visited as soon as we see it, and then we only visit vertices that are not marked as visited.
In addition, when we iterate over the edge list of each vertex, we look at each edge once. This has a total cost of O(E+V).

Space complexity: O(E+V).
Building the adjacency list will take O(E) space.
To keep track of visited vertices, an array of size O(V) is required.
Also, the run-time stack for DFS will use O(V) space.

solution 2:
*/
public class Solution {
    static int countComponents1(int n, int[][] edges) {
        //[[0,1],[1,2],[3,4]]
        //[[0,1],[1,2],[0,2], [3,4]]
        int[] visited = new int[n];
        int res = 0;

        List<Integer>[] graph = new ArrayList[n];
        for(int i = 0; i < n; i++){
            graph[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < edges.length; i++){
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        for(int i = 0; i < n; i++){
            if(visited[i] == 0){
                res++;
                dfs(graph, visited, i);
            }
        }

        return res;
    }

    static void dfs(List<Integer>[] graph, int[] visited, int startNode){
        visited[startNode] = 1;

        for(int i = 0; i < graph[startNode].size(); i++){
            if(visited[graph[startNode].get(i)] == 0){
                dfs(graph, visited, graph[startNode].get(i));
            }
        }

    }


    static int countComponents2(int n, int[] edges){
        int[] parents = new int[n];
        for (int i = 0; i < n; i++){
            parents[i] = i;
        }
        int res = n;
        for (int i = 0; i < edges.length; i++){
            int p1 = find(parents, edges[0]);
            int p2 = find(parents, edges[1]);
            if (p2 != p1){
                parents[p1] = p2;
                res--;
            }
        }

        return res;
    }

    // disjoint-set union -  Without Path Compression
    static int find(int[] parents, int i){
        while (i != parents[i]){
            i = parents[i];
        }

        return i; // Without Path Compression
    }

    // Path compression
    //edges = [0,1],[1,2],[2,3]
    /*  parents = new int[] {0,1,2,3}
    parents[0]=0 parents[1]=1 parents[2]=2 parents[3]=3
           3
          /
         2
        /
       1
      /
     0  parents[0]=1 parents[1]=2 parents[2]=3 parents[3]=3
     after path compression for node 0 => parents[0]=3 parents[1]=2 parents[2]=3 parents[3]=3
         3
        / \
       2   0
      /
     1
   */
    static int findParent(int[] parent, int i) {
        if (i == parent[i]) return i;
        //return parent[i] = findParent(parent, parent[i]); // Path compression
        parent[i] = findParent(parent, parent[i]);

        return parent[i];
    }


    public static void main(String[] args) {
        int[][] edges = new int[][]{{0,1},{1,2},{2,3},{3,4}};

        System.out.println(countComponents1(5, edges));
    }
}
