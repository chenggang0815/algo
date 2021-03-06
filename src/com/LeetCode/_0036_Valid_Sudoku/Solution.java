package com.LeetCode._0036_Valid_Sudoku;

import java.util.HashMap;
import java.util.HashSet;
/*
36. 有效的数独
判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

1. 数字 1-9 在每一行只能出现一次。
2. 数字 1-9 在每一列只能出现一次。
3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

数独部分空格内已填入了数字，空白格用 '.' 表示。
 */

/*
思路1： 根据三个条件来判断是否满足是有效的数独 => 需要遍历9x9的数独三次
//  if (board[i][col] == '.') continue;
思路2： 遍历一次，对每个元素同时判断是否满足三个条件，
    2.1  if (board[i][col] == '.') continue;
    2.2 对每一个元素，同时判断是否满足三个条件
                int boxIndex = (i / 3) * 3 + j / 3;
                if (col[num][j] || row[i][num] || box[boxIndex][num]){
                    return false;
                }
                col[num][j] = row[i][num] = box[boxIndex][num] = true;

 */
public class Solution {
    static boolean isduplicate(char[][] board, int i, int j){
        HashSet<Character> set = new HashSet<>();
        for (int n = i; n < i + 3; n++){
            for (int m = j; m < j + 3; m++){
                if (board[n][m] == '.') continue; // 5ms -> 3ms
                if (set.contains(board[n][m]) && board[n][m] != '.' ) return false;
                set.add(board[n][m]);
            }
        }
        return true;
    }
    static boolean isduplicaterow(char[][] board, int row){
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < board[row].length; i++){
            if (board[row][i] == '.') continue; // 5ms -> 3ms
            if (set.contains(board[row][i]) && board[row][i] != '.') return false;
            set.add(board[row][i]);
        }

        return true;
    }

    static boolean isduplicatecol(char[][] board, int col){
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < board.length; i++){
            if (board[i][col] == '.') continue; // 5ms -> 3ms
            if (set.contains(board[i][col]) && board[i][col] != '.') return false;
            set.add(board[i][col]);
        }

        return true;
    }
    static boolean isValidSudoku1(char[][] board){
        int nums_row = board.length;
        int nums_col = board[0].length;
        for (int i = 0; i < nums_row; i += 3){
            for (int j = 0; j < nums_col; j += 3){
                if (!isduplicate(board, i, j)) return false;
            }
        }
        for (int i = 0; i < nums_row; i++){
            if (!isduplicaterow(board, i)) return false;
        }
        for (int i = 0; i < nums_col; i++){
            if (!isduplicatecol(board,i)) return false;
        }

        return true;
    }

    // 5ms
    static boolean isValidSudoku2(char[][] board){
        HashMap<String, Boolean> colMap = new HashMap<>();
        HashMap<String, Boolean> rowMap = new HashMap<>();
        HashMap<String, Boolean> matrixMap = new HashMap<>();
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] == '.') continue; // 17ms -> 5ms
                StringBuilder rowString = new StringBuilder();
                rowString.append(board[i][j]).append(i);
                if (rowMap.getOrDefault(rowString.toString(), false) && board[i][j] != '.') return false;
                rowMap.put(rowString.toString(), true);

                StringBuilder colString = new StringBuilder();
                colString.append(board[i][j]).append(j);
                if (colMap.getOrDefault(colString.toString(), false) && board[i][j] != '.') return false;
                colMap.put(colString.toString(), true);

                StringBuilder matrixString = new StringBuilder();
                int boxIndex = (i / 3) * 3 + j / 3;
                matrixString.append(board[i][j]).append(boxIndex);
                if (matrixMap.getOrDefault(matrixString.toString(), false) && board[i][j] != '.') return false;
                matrixMap.put(matrixString.toString(), true);
            }
        }
//        matrixMap.forEach((key, value) ->{
//            System.out.println(key + ':' + value);
//        });
        return true;
    }

    // 2 ms
    static boolean isValidSudoku3(char[][] board) {
        int length = 9;
        boolean[][] col = new boolean[length][length];
        boolean[][] row = new boolean[length][length];
        boolean[][] box = new boolean[length][length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '1';
                int boxIndex = (i / 3) * 3 + j / 3;
                if (col[num][j] || row[i][num] || box[boxIndex][num]){
                    return false;
                }
                col[num][j] = row[i][num] = box[boxIndex][num] = true;
            }
        }

        return true;
    }

        public static void main(String[] args) {
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        
       // char[][] board1= [['5','3','.','.','7','.','.','.','.'],['6','.','.','1','9','5','.','.','.'],['.','9','8','.','.','.','.','6','.'],['8','.','.','.','6','.','.','.','3'],['4','.','.','8','.','3','.','.','1'],['7','.','.','.','2','.','.','.','6'],['.','6','.','.','.','.','2','8','.'],['.','.','.','4','1','9','.','.','5'],['.','.','.','.','8','.','.','7','9']];
       System.out.println(isValidSudoku2(board));

    }
}
