package com.LeetCode._0289_Game_of_Life;

import java.util.Arrays;

/*
289. Game of Life

根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
    1. 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
    2. 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
    3. 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
    4. 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。给你 m x n 网格面板 board 的当前状态，返回下一个状态

提示：
m == board.length
n == board[i].length
1 <= m, n <= 25
board[i][j] 为 0 或 1

规则简化：
原来是活细胞：  board[i][j] == 1
    1. if 周围有2-3个活细胞 => 活
    2. else => 死
原来是死细胞：
    1. 周围有3个活细胞 => 活

思路1：复制原数组进行模拟 time:o(m*n) space:o(m*n)
1. 如果直接根据规则更新原始数组 => 那么就做不到题目中说的同步更新
2. 因为: 当前轮次其他细胞状态的更新就会引用到当前轮已更新细胞的状态，但实际上每一轮更新需要依赖上一轮细胞的状态，是不能用这一轮的细胞状态来更新的
3. 一个最简单的解决方法就是复制一份原始数组，复制的那一份永远不修改，只作为更新规则的引用。这样原始数组的细胞值就不会被污染了

思路2：使用额外的状态 time:o(m*n) space:o(1)
1. 活 -> 死 => 1 -> -1 将活细胞的状态由1改为-1，而不是0，在统计（i，j）周围的1时，判断条件为：Math.abs(board[r][c]) == 1，即没有改变后面位置对当前位置的依赖关系
2. 死 -> 活 => 0 -> 2 将死细胞的状态由0改为2，而不是1，是为了不改变后面位置和它的依赖关系
3. 再次遍历数组，将-1复原为0，将2复原为1
ps：
1. 如何从一个坐标(i,j)，不用if遍历它周围的坐标，可以for循环遍历坐标数组来模拟每次的移动，参考：130题 思路2：bfs
2. 使用额外的状态 => 参考：130题
*/
public class Solution {
    static void gameOfLife1(int[][] board) {
        int[] neighbors = new int[]{0,1,-1};
        int rows = board.length;
        int cols = board[0].length;
        int[][] copyBoard = new int[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                copyBoard[i][j] = board[i][j];
            }
        }

        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)){
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);
                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && copyBoard[r][c] == 1){
                                liveNeighbors++;
                            }
                        }
                    }
                }
                // 原来是活细胞：  board[i][j] == 1
                //    1. if 周围有2-3个活细胞 => 活
                //    2. else => 死
                if (copyBoard[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)){
                    board[row][col] = 0;
                }
                // 原来是死细胞：
                //    1. 周围有3个活细胞 => 活
                if (copyBoard[row][col] == 0 && liveNeighbors == 3){
                    board[row][col] = 1;
                }

            }
        }
    }

    // 使用额外的状态 time:o(m*n) space:o(1)
    static void gameOfLife2(int[][] board) {
        int[] neighbors = new int[]{0,1,-1};
        int rows = board.length;
        int cols = board[0].length;
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[0].length; col++){
                // 对于每一个细胞统计其八个相邻位置里的活细胞数量
                int liveNeighbors = 0;
                for (int i = 0; i < 3; i++){
                    for (int j = 0; j < 3; j++){
                        if (!(neighbors[i] == 0 && neighbors[j] == 0)){
                            int r = (row + neighbors[i]);
                            int c = (col + neighbors[j]);
                            // 查看相邻的细胞是否是活细胞
                            if ((r < rows && r >= 0) && (c < cols && c >= 0) && Math.abs(board[r][c]) == 1){
                                liveNeighbors++;
                            }
                        }
                    }
                }
                // 原来是活细胞：  board[i][j] == 1
                //    1. if 周围有2-3个活细胞 => 活
                //    2. else => 死
                if (board[row][col] == 1 && (liveNeighbors < 2 || liveNeighbors > 3)){
                    board[row][col] = -1;
                }
                // 原来是死细胞：
                //    1. 周围有3个活细胞 => 活
                if (board[row][col] == 0 && liveNeighbors == 3){
                    board[row][col] = 2;
                }

            }
        }

        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] == -1){
                    board[i][j] = 0;
                }
                if (board[i][j] == 2){
                    board[i][j] = 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        /*
        [[0,1,0],
         [0,0,1],
         [1,1,1],
         [0,0,0]]

         output:
         [[0,0,0],
          [1,0,1],
          [0,1,1],
          [0,1,0]]
         */


        int[][] board = new int[][]{{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        gameOfLife2(board);
        for (int[] nums: board){
            System.out.println(Arrays.toString(nums));
        }

    }
}
