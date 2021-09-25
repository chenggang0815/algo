package Amazon._0547_Number_of_Provinces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
547. Number of Provinces

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.

example 1
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2


solution dfs
1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
0 0 0 1 1
0 0 0 1 1
Output => 3

1 1 0 0 0
1 1 1 0 0
0 1 1 0 0
0 0 0 1 1
0 0 0 1 1
Output => 2

i = 0 j = 0,1
i = 1 j = 0,1,2
i = 2 j = 0,1,2
form the first col, iterate the first col, if we find a 1 at jth rows, then we search the j col again

solution bfs

*/
public class Solution {
//    static int findGroup(int[][] cities){
//        int n = cities.length;
//        List<Integer>[] graph = new ArrayList[n];
//        for (int i = 0; i < n; i++){
//            graph[i] = new ArrayList<>();
//        }
//        for (int i = 0; i < n; i++){
//            for (int j = 0; j < n; j++){
//                if (i != j && cities[i][j] == 1){
//                    graph[i].add(j);
//                }
//            }
//        }
//
//        int res = 0;
//        int[] visited = new int[cities.length];
//
//
//        return 0;
//    }

    // dfs
    static int findGroup1(int[][] isConnected){
        int n = isConnected.length;
        int res = 0;
        int[] visited = new int[n];
        for (int i = 0; i < n; i++){
            if (visited[i] == 0){
                dfs(isConnected, i, visited);
                res++;
            }
        }

        return res;
    }

    static void dfs(int[][] isConnected, int i, int[] visited){
        for (int j = 0; j < isConnected.length; j++){
            if (isConnected[i][j] == 1 && visited[j] == 0){
                visited[j] = 1;
                dfs(isConnected, j, visited);
            }
        }
    }

    // bfs
    static int findGroup2(int[][] isConnected){
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[isConnected.length];
        int res = 0;
        for(int i = 0; i < isConnected.length; i++){
            if (visited[i] == 0){
                queue.add(i);
                while (!queue.isEmpty()){
                    int row = queue.poll();
                    visited[row] = 1;
                    for (int j = 0; j < isConnected.length; j++){
                        if (isConnected[row][j] == 1 && visited[j] == 0){
                            queue.add(j);
                        }
                    }
                }
                res++;
            }
        }

        return res;
    }


//    static void dfs(int[][] cities, int i, int j){
//        for (int m = i; m < cities.length; m++){
//            for (int n = j; n < cities.length; n++){
//                if (cities[m][n] == 1)
//            }
//        }
//    }

    public static void main(String[] args) {
        int[][] nums = new int[][]{
                {1,1,0,0,0},
                {1,1,1,0,0},
                {0,1,1,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1}
        };
        System.out.println(findGroup1(nums));
    }
}
