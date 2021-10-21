package Amazon._0348_Design_Tic_Tac_Toe;
/*
348. Design Tic-Tac-Toe

Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
A move is guaranteed to be valid and is placed on an empty block.
Once a winning condition is reached, no more moves are allowed.
A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.

Implement the TicTacToe class:
1. TicTacToe(int n) Initializes the object the size of the board n.
2. int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of the board. The move is guaranteed to be a valid move.

Example 1:

Input
["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
[[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
Output
[null, 0, 0, 0, 0, 0, 0, 1]

Explanation
TicTacToe ticTacToe = new TicTacToe(3);
Assume that player 1 is "X" and player 2 is "O" in the board.
ticTacToe.move(0, 0, 1); // return 0 (no one wins)
|X| | |
| | | |    // Player 1 makes a move at (0, 0).
| | | |

ticTacToe.move(0, 2, 2); // return 0 (no one wins)
|X| |O|
| | | |    // Player 2 makes a move at (0, 2).
| | | |

ticTacToe.move(2, 2, 1); // return 0 (no one wins)
|X| |O|
| | | |    // Player 1 makes a move at (2, 2).
| | |X|

ticTacToe.move(1, 1, 2); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 2 makes a move at (1, 1).
| | |X|

ticTacToe.move(2, 0, 1); // return 0 (no one wins)
|X| |O|
| |O| |    // Player 1 makes a move at (2, 0).
|X| |X|

ticTacToe.move(1, 0, 2); // return 0 (no one wins)
|X| |O|
|O|O| |    // Player 2 makes a move at (1, 0).
|X| |X|

ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
|X| |O|
|O|O| |    // Player 1 makes a move at (2, 1).
|X|X|X|
*/

/*
Solution
Approach 1: brute force

Approach 2: time;O(1) space:O(n)
1. design three matrix rows[n][3], cols[n][3], dig[2][3] to check row, col and diagonal
   for rows[n][3] => n means (n-1)th row for 1 or 2 player
   for cols[n][3]
   for dig[2][3] => first dim means two direction diagonal
2. after a move (row, col, player) => if rows[row][player] == n || cols[col][player] == n || dagCnt[0][player] == n || dagCnt[1][player] == n
   return player
*/
public class TicTacToe {
    int[][] rowCnt;
    int[][] colCnt;
    int[][] dagCnt;
    int dim;
    public TicTacToe(int n) {
        dim = n;
        rowCnt = new int[n][3];
        colCnt = new int[n][3];
        dagCnt = new int[2][3];
    }

    public int move(int row, int col, int player) {
        rowCnt[row][player]++;
        colCnt[col][player]++;
        if(row == col)  dagCnt[0][player]++; // meet diagonal
        if(row + col == dim - 1) dagCnt[1][player]++; // meet diagonal

        if(rowCnt[row][player] == dim || colCnt[col][player] == dim || dagCnt[0][player] == dim || dagCnt[1][player] == dim) return player;

        return 0;
    }
    public static void main(String[] args) {

    }
}
