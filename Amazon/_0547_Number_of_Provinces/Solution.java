package Amazon._0547_Number_of_Provinces;

import java.util.ArrayList;
import java.util.List;

/*
547. Number of Provinces

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
Return the total number of provinces.

example 1
Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2

1 1 0 0 0
1 1 0 0 0
0 0 1 0 0
      1 1
        1

i,j = 1 => i [j]
j,i = 1 => j [i]
1: 2 3
2: 1
3:
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

    static int findGroup(int[][] cities){
        int n = cities.length;

        int res = 0;
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                if (i != j && cities[i][j] == 1){
              //      dfs(cities, i, j);
                    res++;
                }
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
        findGroup(nums);
    }
}
