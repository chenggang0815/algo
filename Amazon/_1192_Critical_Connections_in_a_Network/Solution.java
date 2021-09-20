package Amazon._1192_Critical_Connections_in_a_Network;

import java.util.*;

/*
1192. Critical Connections in a Network

There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.
A critical connection is a connection that, if removed, will make some servers unable to reach some other server.
Return all critical connections in the network in any order.


*/

public class Solution {
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++){
            graph[i] = new ArrayList<>();
        }
        for (List<Integer> connection: connections){
            graph[connection.get(0)].add(connection.get(1));
            graph[connection.get(1)].add(connection.get(0));
        }

        HashSet<List<Integer>> connectionSet = new HashSet<>(connections);
        int[] rank = new int[n];
        Arrays.fill(rank, -2);
        dfs(graph, 0, 0, rank, connectionSet);

        return new ArrayList<>(connectionSet);
    }

    int dfs(List<Integer>[] graph, int node, int depth, int[] rank, HashSet<List<Integer>> connectionSet){
        if (rank[node] >= 0){
            return rank[node];
        }
        rank[node] = depth;
        int minDepthFound = depth;
        for (Integer neighbor:graph[node]){
            if (rank[neighbor] == depth - 1){
                continue;
            }
            int minDepth = dfs(graph, neighbor, depth+1, rank, connectionSet);
            minDepthFound = Math.min(minDepthFound, minDepth);
            if (minDepth <= depth){
                connectionSet.remove(Arrays.asList(node, neighbor));
                connectionSet.remove(Arrays.asList(neighbor, node));
            }
        }

        return minDepthFound;
    }

    public static void main(String[] args) {
    }
}