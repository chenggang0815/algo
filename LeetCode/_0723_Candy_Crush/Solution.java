package LeetCode._0723_Candy_Crush;
/*
723. Candy Crush
This question is about implementing a basic elimination algorithm for Candy Crush.

Given an m x n integer array board representing the grid of candy where board[i][j] represents the type of candy.
A value of board[i][j] == 0 represents that the cell is empty.

The given board represents the state of the game following the player's move.
Now, you need to restore the board to a stable state by crushing candies according to the following rules:
    1. If three or more candies of the same type are adjacent vertically or horizontally, crush them all at the same time - these positions become empty.
    2. After crushing all candies simultaneously, if an empty space on the board has candies on top of itself, then these candies will drop until they hit a candy or bottom at the same time. No new candies will drop outside the top boundary.
    3. After the above steps, there may exist more candies that can be crushed. If so, you need to repeat the above steps.
    4. If there does not exist more candies that can be crushed (i.e., the board is stable), then return the current board.

You need to perform the above rules until the board becomes stable, then return the stable board.

Constraints:
1. m == board.length
2. n == board[i].length
3. 3 <= m, n <= 50
4. 1 <= board[i][j] <= 2000
*/
public class Solution {
    public int[][] candyCrush(int[][] board) {
        int rows = board.length;
        int cols = board[0].length;
        boolean todo = true;
        while (todo){
            todo = false;
            for (int i = 0; i < rows; i++){
                for (int j = 0; j + 2 < cols; j++){
                    int temp = Math.abs(board[i][j]);
                    if (temp != 0 && temp == board[i][j + 1] && temp == board[i][j + 2]){
                        todo = true;
                        board[i][j] = board[i][j + 1] = board[i][j + 2] = -temp;
                    }
                }
            }
            for (int i = 0; i + 2 < rows; i++){
                for (int j = 0; j < cols; j++){
                    int temp = Math.abs(board[i][j]);
                    if (temp != 0 && temp == board[i + 1][j] && temp == board[i + 2][j]){
                        todo = true;
                        board[i][j] = board[i + 1][j] = board[i + 2][j] = -temp;
                    }
                }
            }
            int tempRow = rows - 1;
            for (int j = 0; j < cols; j++){
                for (int i = tempRow; i >= 0; i--){
                    if (board[i][j] > 0){
                        board[tempRow--][j] = board[i][j];
                    }
                }
                for(int i = tempRow; i >= 0; i--) board[i][j] = 0;
            }
        }

        return board;
    }
    public static void main(String[] args) {

    }
}
