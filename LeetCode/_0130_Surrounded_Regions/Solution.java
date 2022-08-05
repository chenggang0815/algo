package LeetCode._0130_Surrounded_Regions;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
130. Surrounded Regions
给你一个 m x n 的矩阵 board ，由若干字符 'X' 和 'O' ，找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充

input:
{'X','X','X','X'},
{'X','X','O','X'},
{'X','O','X','X'},
{'X','O','X','X'}}
output:
{'X','X','X','X'},
{'X','X','X','X'},
{'X','O','X','X'},
{'X','O','X','X'}}

similar to leetcode 130
思路1：dfs time:o(m*n) space:o(m*n)
1. 因为边界上的O都不会被填充为X => 所有的不被包围的O都直接或间接与边界上的O相连
2. 遍历矩阵的边界，对于边界上的每个O，以它为起点，用A标记所有与它直接或间接相连的字母O
3. 再次遍历矩阵，对每一个字母
    3.1 如果board[i][j] == 'A'，则该字母 => 是没有被字母X包围的字母O => 将其还原为字母O
    3.2 如果board[i][j] == 'O'，则该字母为 => 是被字母X包围的字母O => 将其修改为字母X

思路2：bfs time:o(m*n) space:o(m*n)
思路与1相同

ps：如何从一个坐标(i,j)，不用if遍历它周围的坐标，可以for循环遍历坐标数组来模拟每次的移动，参考：289题

*/
public class Solution {
    static void dfs(char[][] board, int i, int j){
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != 'O'){
            return;
        }
        board[i][j] = 'A';
        dfs(board, i + 1, j);
        dfs(board, i - 1, j);
        dfs(board, i, j + 1);
        dfs(board, i, j - 1);
    }

    static void solve1(char[][] board) {
        if (board.length == 0) return;

        int rows = board.length;
        int cols = board[0].length;
        // 对于边界上的每个O，以它为起点，用A标记所有与它直接或间接相连的字母O
        for (int i = 0; i < rows; i++){
            dfs(board, i, 0);
            dfs(board, i, cols - 1);
        }
        // 对于边界上的每个O，以它为起点，用A标记所有与它直接或间接相连的字母O
        for (int j = 0; j < cols; j++){
            dfs(board, 0, j);
            dfs(board, rows - 1, j);
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                }else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

    }
    /*
    通过遍历坐标数组来模拟每次上下左右移动
    => （i+1，j+0) (i-1,j+0) (i+0,j+1) (i+0,j-1)
    因此x和y的1/-1需要和对方的0对应，表示沿着0的方向移动（如果1和1对应，则元素会沿着对角线移动）
     */
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static void solve2(char[][] board) {
        int rows = board.length;
        if (rows == 0) return;;

        int cols = board[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // 将边界上为O的元素加入队列中，作为bfs的起点
        for (int i = 0; i < rows; i++){
            if (board[i][0] == 'O') queue.offer(new int[]{i, 0});
            if (board[i][cols - 1] == 'O') queue.offer(new int[]{i, cols - 1});
        }

        for (int j = 0; j < cols; j++){
            if (board[0][j] == 'O') queue.offer(new int[]{0, j});
            if (board[rows - 1][j] == 'O') queue.offer(new int[]{rows - 1, j});
        }

        // bfs，通过 int row = x + dx[i], col = y + dy[i]; 来遍历每个元素的上下左右
        while (!queue.isEmpty()){
            int[] cell = queue.poll();
            int x = cell[0], y = cell[1];
            board[x][y] = 'A';
            for (int i = 0; i < 4; i++){
                int row = x + dx[i], col = y + dy[i];
                if (row < 0 || row >= rows || col < 0 || col >= cols || board[row][col] != 'O') continue;
                queue.offer(new int[]{row, col});
            }
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                }else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }
    }


    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'X','X','X','X'},
                {'X','X','O','X'},
                {'X','O','X','X'},
                {'X','O','X','X'}};

        solve2(board);
        for (char[] ch: board){
            System.out.println(Arrays.toString(ch));
        }
    }
}
