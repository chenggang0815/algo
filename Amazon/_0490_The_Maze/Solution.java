package Amazon._0490_The_Maze;
/*
490. The Maze
There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.
Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
You may assume that the borders of the maze are all walls (see examples).

Example 1:
Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
Output: true
Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
*/

import java.util.LinkedList;
import java.util.Queue;

/*
Solution
Approach 1: DFS
 [[0,0,1,0,0],
  [0,0,0,0,0],
  [0,0,0,1,0],
  [1,1,0,1,1],
  [0,0,0,0,0]]

 1. each time move left/right/up/down until meet a wall
 2. then dfs four direction
 3. use visited to avoid repeat iterate

Approach 2: BFS
思路：
1. 从开始节点，上下左右一直遍历到有墙或者有边界的地方停下
2. 对于停下之前的这个位置，
    2.1 如果等于目标值，那么已经找到符合要求的值，return true
    2.2 如果不等于目标值，停下之前的这个位置，可以考虑作为新的起始点放进去队列。
    2.3 至于从开始节点到停下之前的这个位置中间的位置，都可以忽略，因为它们肯定不满足

1. we try to explore the search space on a level by level basis. i.e. We try to move in all the directions at every step.
2. When all the directions have been explored and we still don't reach the destination, then only we proceed to the new set of traversals from the new positions obtained.
    2.1 we make use of a queue. We start with the ball at the start position.
    2.2 For every current position, we add all the new positions possible by traversing in all the four directions(till reaching the wall or boundary) into the queue to act as the new start positions
    2.3 and mark these positions as True in the visited array.

If we hit the destination position at any moment, we return a True directly indicating that the destination position can be reached starting from the start position.

*/
public class Solution {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        int[][] directions = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        return dfs(maze, start, destination, directions, visited);
    }

    boolean dfs(int[][] maze, int[] current, int[] destination, int[][] directions, boolean[][] visited){
        int i = current[0];
        int j = current[1];
        if(i == destination[0] && j == destination[1]) return true;
        if(i < 0 || i > maze.length - 1 || j < 0 || j > maze[0].length - 1 || visited[i][j]) return false;

        visited[i][j] = true;
        for(int[] direction : directions){
            int row = i;
            int col = j;
            while(row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] == 0){
                row += direction[0];
                col += direction[1];
            }
            current = new int[]{row - direction[0], col - direction[1]};
            if(dfs(maze, current, destination, directions, visited)) return true;
        }

        return false;
    }

    // bfs
    public boolean hasPath2(int[][] maze, int[] start, int[] destination) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(start);
        int[][] directions = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        //visited[start[0]][start[1]] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == destination[0] && cur[1] == destination[1]) return true;

            for(int[] dir: directions){
                int x = cur[0];
                int y = cur[1];
                while(x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0){
                    x += dir[0];
                    y += dir[1];
                }

                if(!visited[x - dir[0]][y - dir[1]]){
                    queue.add(new int[]{x - dir[0], y - dir[1]});
                    visited[x - dir[0]][y - dir[1]] = true;
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

    }
}
