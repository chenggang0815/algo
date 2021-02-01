package com.LeetCode._0079_Word_Search;
/*
79. Word Search
Given an m x n board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where 'adjacent' cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [['A','B','C','E'],
                ['S','F','C','S'],
                ['A','D','E','E']], word = 'ABCCED'
Output: true

Example 2:
Input: board = [['A','B','C','E'],
                ['S','F','C','S'],
                ['A','D','E','E']], word = 'ABCB'
Output: false

 */
public class Solution {
    static boolean flag = false;

    static boolean dfs(char[][] board, String word, int i, int j, StringBuilder sb){
        if (sb.toString().length() > word.length()) return false;
        if (sb.toString().equals(word)){
            //flag = true;
            return true;
        }
        
        if (i < board.length - 1){
            dfs(board, word, i + 1, j, sb.append(board[i+1][j]));
        }
        if (j < board[0].length - 1){
            dfs(board, word, i, j + 1, sb.append(board[i][j+1]));
        }

        if (i > 1){
            dfs(board, word, i - 1, j, sb.append(board[i-1][j]));
        }
        if (j > 1){
            dfs(board, word, i, j - 1, sb.append(board[i][j-1]));
        }

        return false;
    }

    static boolean exist(char[][] board, String word) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (dfs(board, word, i, j, sb)){
                    return true;
                }
            }
        }
        
        return false;
    }

    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};

        System.out.println(exist(board, "ABCCED"));
    }
}
