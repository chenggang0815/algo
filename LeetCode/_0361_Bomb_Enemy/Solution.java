package LeetCode._0361_Bomb_Enemy;
/*
361. Bomb Enemy
Given an m x n matrix grid where each cell is either a wall 'W', an enemy 'E' or empty '0',
return the maximum enemies you can kill using one bomb. You can only place the bomb in an empty cell.

The bomb kills all the enemies in the same row and column from the planted point
until it hits the wall since it is too strong to be destroyed.
Example 1:
Input: grid = [["0","E","0","0"],
               ["E","0","W","E"],
               ["0","E","0","0"]]
Output: 3

Constraints:
1. m == grid.length
2. n == grid[i].length
3. 1 <= m, n <= 500
4. grid[i][j] is either 'W', 'E', or '0'.
*/

/*
Solution:
m = rows
n = cols
Approach 1: brute force time:O(m*n(m+n)) space:O(1)


Approach 2: dynamic programming time:O(m*n) space:O(n)
1. iterate the grid from up to bottom (row by row)
2. for each row, we can reuse the number of enemy until we meet a wall
3. for each col, we can reuse the number of enemy until we meet a wall

    Input: grid = [["0","E","0","0"], i=0 rowCnt=1
                   ["E","0","W","E"], i=1 rowCnt=1(j=[0,1]) rowCnt=1(j=[3])
                   ["0","E","0","0"]] i=2 rowCnt=1
        j=0 colCnt[0]=1
        j=1 colCnt[1]=2
        j=2 colCnt[2]=0(i=0) colCnt[2]=0(i=2)
        j=3 colCnt[3]=1
time:O(m*n)
Concerning each cell in the grid, we assert that it would be visited exactly three times.
The first visit is the case where we iterate through each cell in the grid in the outer loop.
The second visit would occur when we need to calculate the row_hits that involves with the cell.
And finally the third visit would occur when we calculate the value of col_hits that involves with the cell.
total time complexity => O(3m*n)
space:O(n)
*/
public class Solution {
    // approach 1
    public int maxKilledEnemies1(char[][] grid) {
        int res = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j] == '0'){
                    int enemy = 0;
                    int m = i;
                    while(m >= 0 && grid[m][j] != 'W'){
                        if(grid[m][j] == 'E') enemy++;
                        m--;
                    }
                    m = i;
                    while(m < rows && grid[m][j] != 'W'){
                        if(grid[m][j] == 'E') enemy++;
                        m++;
                    }
                    m = j;
                    while(m >= 0 && grid[i][m] != 'W'){
                        if(grid[i][m] == 'E') enemy++;
                        m--;
                    }
                    m = j;
                    while(m < cols && grid[i][m] != 'W'){
                        if(grid[i][m] == 'E') enemy++;
                        m++;
                    }
                    res = Math.max(res, enemy);
                }
            }
        }

        return res;

    }
    // approach
    public int maxKilledEnemies2(char[][] grid) {
        int res = 0;
        int rowCnt = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        int[] colCnt = new int[cols];

        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(j == 0 || grid[i][j - 1] == 'W'){
                    rowCnt = 0;
                    for(int k = j; k < cols; k++){
                        if(grid[i][k] == 'E'){
                            rowCnt++;
                        }else if(grid[i][k] == 'W') break;
                    }
                }
                if(i == 0 || grid[i - 1][j] == 'W'){
                    colCnt[j] = 0;
                    for(int k = i; k < rows; k++){
                        if(grid[k][j] == 'E') colCnt[j]++;
                        else if(grid[k][j] == 'W') break;;
                    }
                }
                if(grid[i][j] == '0') res = Math.max(res, rowCnt + colCnt[j]);
            }
        }

        return res;
    }

    public static void main(String[] args) {

    }
}
