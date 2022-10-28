package LeetCode._0286_Walls_and_Gates;

import java.util.LinkedList;
import java.util.Queue;

/*
286. Walls and Gates
You are given an m x n grid rooms initialized with these three possible values.
1. -1 A wall or an obstacle.
2. 0 A gate.
3. INF Infinity means an empty room. We use the value 231 - 1 = 2147483647 to represent INF as you may assume that the distance to a gate is less than 2147483647.
Fill each empty room with the distance to its nearest gate.
If it is impossible to reach a gate, it should be filled with INF.

Example 1:
Input: rooms = [[2147483647,    -1,          0,       2147483647],
                [2147483647, 2147483647,   2147483647,    -1],
                [2147483647,    -1,        2147483647,    -1],
                [0,             -1,        2147483647, 2147483647]]
Output: [[3,-1,0,1],
         [2,2,1,-1],
         [1,-1,2,-1],
         [0,-1,3,4]]
*/
/*
Solution => // similar with question 994. Rotting Oranges
Approach 1: BFS
time: O(K*M*N)
space: O(M*N)

Approach 2: BFS
time: O(M*N)
space: O(M*N)

Approach 3: BFS

time: O(M*N)
space: O(M*N)
 */
public class Solution {
    // approach 1 => 2359 ms
    public void wallsAndGates(int[][] rooms) {
        int[][] directions = new int[][]{{0,1}, {0,-1}, {-1,0}, {1,0}};
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 2147483647){
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i, j, 0});
                    int[][] visited = new int[rooms.length][rooms[0].length];
                    while(!queue.isEmpty()){
                        int[] num = queue.poll();
                        if(rooms[num[0]][num[1]] == 0){
                            rooms[i][j] = num[2];
                            break;
                        }
                        for(int[] direction : directions){
                            int x = num[0] + direction[0];
                            int y = num[1] + direction[1];
                            if(x < 0 || x > rooms.length - 1 || y < 0 || y > rooms[0].length - 1 || rooms[x][y] == -1){
                                continue;
                            }
                            if(visited[x][y] == 1) continue;
                            visited[x][y] = 1;
                            queue.add(new int[]{x,y, num[2] + 1});
                        }
                    }
                }
            }
        }
    }

    // approach 2 => 174 ms
    public void wallsAndGates2(int[][] rooms) {
        int[][] directions = new int[][]{{0,1}, {0,-1}, {-1,0}, {1,0}};
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0) queue.add(new int[]{i, j});
            }
        }

        while(!queue.isEmpty()){
            int[] num = queue.poll();
            for(int[] direction : directions){
                int x = num[0] + direction[0];
                int y = num[1] + direction[1];
                if(x < 0 || x > rooms.length - 1 || y < 0 || y > rooms[0].length - 1 || rooms[x][y] == -1) continue;
                if(rooms[x][y] <= rooms[num[0]][num[1]]) continue;
                rooms[x][y] = rooms[num[0]][num[1]] + 1;
                queue.add(new int[]{x,y});
            }
        }
    }
    // approach 3 => 17 ms
    public void wallsAndGates3(int[][] rooms) {
        int[][] directions = new int[][]{{0,1}, {0,-1}, {-1,0}, {1,0}};
        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0) queue.add(new int[]{i, j});
            }
        }

        while(!queue.isEmpty()){
            int[] num = queue.poll();
            for(int[] direction : directions){
                int x = num[0] + direction[0];
                int y = num[1] + direction[1];
                if(x < 0 || x > rooms.length - 1 || y < 0 || y > rooms[0].length - 1 || rooms[x][y] != 2147483647) continue;
                //if(rooms[x][y] <= rooms[num[0]][num[1]]) continue;
                rooms[x][y] = rooms[num[0]][num[1]] + 1;
                queue.add(new int[]{x,y});
            }
        }
    }

    // similar with question 994. Rotting Oranges
    public void wallsAndGates4(int[][] rooms) {
        Queue<int[]> queue = new LinkedList<>();
        for(int i = 0; i < rooms.length; i++){
            for(int j = 0; j < rooms[0].length; j++){
                if(rooms[i][j] == 0) queue.add(new int[]{i, j, 0});
            }
        }
        int INF = 2147483647;
        int[][] directions = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            while(levelSize > 0){
                int[] cur = queue.poll();
                for(int[] dir: directions){
                    int x = cur[0] + dir[0];
                    int y = cur[1] + dir[1];
                    int step = cur[2] + 1;
                    if(x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length) continue;
                    if(rooms[x][y] == -1 || rooms[x][y] == 0 || rooms[x][y] != INF) continue;
                    rooms[x][y] = step;
                    queue.add(new int[]{x, y, step});
                }
                levelSize--;
            }
        }

    }
    public static void main(String[] args) {

    }
}
