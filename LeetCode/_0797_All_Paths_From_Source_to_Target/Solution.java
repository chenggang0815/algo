package LeetCode._0797_All_Paths_From_Source_to_Target;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*
797. All Paths From Source to Target
Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1,
find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i
(i.e., there is a directed edge from node i to node graph[i][j]).

Example 1:
Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
*/
/*
Approach 1: backtracking
*/
public class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> res = new LinkedList<>();
        int target = graph.length - 1;
        LinkedList<Integer> path = new LinkedList<>();
        path.add(0);
        backTracking(graph, 0, target, path, res);

        return res;
    }

    void backTracking(int[][] graph, int cur, int target, LinkedList<Integer> path, List<List<Integer>> res){
        if (cur == target){
            res.add(new ArrayList<>(path));
            return;
        }

        for (int nextNode: graph[cur]){
            path.add(nextNode);
            backTracking(graph, nextNode, target, path, res);
            path.removeLast();
        }
    }

    public static void main(String[] args) {

    }
}
