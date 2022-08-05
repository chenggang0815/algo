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

    public static void main(String[] args) {

    }
}
