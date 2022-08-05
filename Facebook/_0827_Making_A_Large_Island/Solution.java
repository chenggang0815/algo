package Facebook._0827_Making_A_Large_Island;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/*
827. Making A Large Island

You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
Return the size of the largest island in grid after applying this operation.
An island is a 4-directionally connected group of 1s.

Example 1:
Input: grid = [[1,0],[0,1]]
Output: 3
Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.

Example 2:
Input: grid = [[1,1],[1,0]]
Output: 4
Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.

Example 3:
Input: grid = [[1,1],[1,1]]
Output: 4
Explanation: Can't change any 0 to 1, only one island with area = 4.

Constraints:
1. n == grid.length
2. n == grid[i].length
3. 1 <= n <= 500
4. grid[i][j] is either 0 or 1.
*/
/*
Solution:
Approach 1: brute force - dfs time:O(n^4) space:O(n^2)
1. for each 0, we change it to 1 and deep first search the size of the island.

Approach 2:  time:O(n^2) space:O(n^2)
1. use deep first search the size of all the island, store in the hashMap<islandId, size>
2. for each island, change the 1 to the islandId
3. for each 0, check if we change it to the 1, how many island we can connect, sum the total size of the connected island
*/
public class Solution {
    // approach 1
    public int largestIsland1(int[][] grid) {
        int[] size = new int[1];
        int res = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == 0){
                    grid[i][j] = 1;
                    int[][] visited = new int[grid.length][grid[0].length];
                    size[0] = 0;
                    dfs(grid, i, j, size, visited);
                    grid[i][j] = 0;
                    res = Math.max(size[0], res);
                }
            }
        }

        return res == 0 ? grid.length * grid.length : res;
    }

    void dfs(int[][] grid, int i, int j, int[] size, int[][] visited){
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1) return;

        if(grid[i][j] != 1 || visited[i][j] == 1) return;

        size[0]++;
        //int temp = grid[i][j];

        visited[i][j] = 1;

        dfs(grid, i + 1, j, size, visited);
        dfs(grid, i - 1, j, size, visited);
        dfs(grid, i, j + 1, size, visited);
        dfs(grid, i, j - 1, size, visited);
    }

    public int largestIsland2(int[][] grid) {
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int n = grid.length;
        int areaIndex = 2;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 1){
                    int size = dfs(grid, i, j, areaIndex);
                    map.put(areaIndex, size);
                    areaIndex++;
                }
            }
        }

        int res = map.getOrDefault(2, 0);
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(grid[i][j] == 0){
                    Set<Integer> set = new HashSet<>();
                    set.add(i > 0 ? grid[i - 1][j] : 0);
                    set.add(i < n - 1 ? grid[i + 1][j] : 0);
                    set.add(j > 0 ? grid[i][j - 1] : 0);
                    set.add(j < n - 1 ? grid[i][j + 1] : 0);

                    int tempSize = 1;
                    for(int index : set) tempSize += map.get(index);

                    res = Math.max(res, tempSize);
                }
            }
        }

        return res;
    }

    int dfs(int[][] grid, int i, int j, int areaIndex){
        if(i < 0 || i > grid.length - 1 || j < 0 || j > grid[0].length - 1 || grid[i][j] != 1) return 0;
        grid[i][j] = areaIndex;

        return 1 + dfs(grid, i + 1, j, areaIndex) + dfs(grid, i - 1, j, areaIndex) + dfs(grid, i, j + 1, areaIndex) + dfs(grid, i, j - 1, areaIndex);
    }
    public static void main(String[] args) {

    }
}
