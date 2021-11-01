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
Solution:
Approach 1: dfs
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

Approach 2: UnionFind

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

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0,1},{1,2},{2,3},{3,4}};

        System.out.println(countComponents1(5, edges));
    }
}
