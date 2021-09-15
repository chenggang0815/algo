package LeetCode._0079_Word_Search;
/*
79. Word Search
Given an m x n board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where 'adjacent' cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example 1:
Input: board = [['A','B','C','E'],
                ['S','F','C','S'],
                ['A','D','E','E']], word = 'ABCCED'
Output: true
 */
/*
解法：
1
2
3
4 => 2021-04-11
思路： 回溯/dfs + 多个剪枝条件
1. 遍历整个数组board，如果当前元素board[i][j]=word.charAt(0) => 进入dfs
2. dfs剪枝条件 => if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || flag[i][j] == 1 || board[i][j] != word.charAt(k)) return false;
   2.1 i < 0 || i >= board.length || j < 0 || j >= board[0].length 表示元素越界
   2.2 flag[i][j] == 1 表示元素已经搜索过了，不能走回头路
   2.3 board[i][j] != word.charAt(k) 表示当前搜索的元素与word中对应的元素不相等，提前退出搜索

3. dfs结束条件: if(k == word.length() - 1) return true; 表示遍历搜索完整个word

4. flag[i][j] 搜索状态重置
4. 递归搜索上下左右
        boolean res = dfs4(board, word, flag, i, j - 1, k)
                || dfs4(board, word, flag, i, j + 1, k)
                || dfs4(board, word, flag, i - 1, j, k)
                || dfs4(board, word, flag, i + 1, j, k);


时间复杂度：
    1. 一个非常宽松的上界为O(MN3^L),其中M,N为网格的长度与宽度，L 为字符串word的长度。在每次调用函数dfs时，除了第一次可以进入4个分支以外，
    2. 其余时间我们最多会进入3个分支（因为每个位置只能使用一次，所以走过来的分支没法走回去）。
    3. 由于单词长为L，故dfs(i, j, 0)的时间复杂度为O(3^L)，而我们要执行O(MN) 次检查。
    3. 然而，由于剪枝的存在，我们在遇到不匹配或已访问的字符时会提前退出，终止递归流程。因此，实际的时间复杂度会远远小于O(MN3^L)

空间复杂度：
    1. O(MN)。我们额外开辟了O(MN)的visited 数组，同时栈的深度最大为O(min(L,MN))。
*/
public class Solution {
    // 第一版本的逻辑
//    static boolean dfs(char[][] board, String word, int i, int j, int[][] index, StringBuilder sb){
//       // if (sb.toString().length() > word.length() || i < 0 || j < 0 ||  i >= board.length || j >= board[0].length) return false;
//        System.out.println(sb.toString());
//
//        if (sb.toString().equals(word)){
//            return true;
//        }
//
//        if (sb.toString().length() > word.length() || i <= 0 || j <= 0 ||  i >= board.length - 1 || j >= board[0].length - 1 || index[i][j] == 1)
//            return false;
//
//        index[i][j] = 1;
//        boolean res =  dfs(board, word, i + 1, j, index, sb.append(board[i + 1][j])) ||
//            dfs(board, word, i, j + 1, index, sb.append(board[i][j + 1])) ||
//            dfs(board, word, i - 1, j, index, sb.append(board[i - 1][j])) ||
//            dfs(board, word, i, j - 1, index, sb.append(board[i][j - 1]));
//
//        index[i][j] = 0;
//
//        return res;
//    }
//
//    static boolean exist(char[][] board, String word) {
//        int[][] index  = new int[board.length][board[0].length];
//        for (int i = 0; i < board.length; i++){
//            for (int j = 0; j < board[0].length; j++){
//                StringBuilder sb = new StringBuilder();
//                sb.append(board[i][j]);
//                if (dfs(board, word, i, j, index, sb)){
//                    return true;
//                }
//            }
//        }
//
//        return false;
//    }

    // 解法1 Time Limit Exceeded 超时
    static boolean exist1(char[][] board, String word) {
        int[][] index  = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                StringBuilder sb = new StringBuilder();
                if (backtrack1(board, word, i, j, index, sb)){
                    return true;
                }
            }
        }

        return false;
    }

    static boolean backtrack1(char[][] board, String word, int i, int j, int[][] index, StringBuilder sb){
        //System.out.println(sb.toString());
        // 通过字符串的长度来构建剪枝空间，可能会造成多余的搜索 => 更好的办法，通过搜索的每一步判断当前字符和word中的字符是否相等来剪枝
        if (sb.toString().length() > word.length() || i < 0 || j < 0 ||  i >= board.length || j >= board[0].length || index[i][j] == 1)
            return false;
        sb.append(board[i][j]);
        if (sb.toString().equals(word)){
            return true;
        }
        index[i][j] = 1;
        boolean res =  backtrack1(board, word, i + 1, j, index, sb) ||
                backtrack1(board, word, i, j + 1, index, sb) ||
                backtrack1(board, word, i - 1, j, index, sb) ||
                backtrack1(board, word, i, j - 1, index, sb);

        sb.deleteCharAt(sb.length() - 1);
        index[i][j] = 0;

        return res;
    }

    //解法1 Runtime: 35 ms, faster than 11.91% of Java online submissions for Word Search.
    static boolean exist2(char[][] board, String word){
        int[][] index  = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                if (board[i][j] != word.charAt(0)) continue; // 根据第一位字母剪枝
                StringBuilder sb = new StringBuilder();
                if (backtrack2(board, word, i, j, index, sb)){
                    return true;
                }
            }
        }
        return false;
    }

    static boolean backtrack2(char[][] board, String word, int i, int j, int[][] index, StringBuilder sb){
        // 每加一个字母就判断剪枝一次，加大剪枝空间
        if ((sb.length() > 0 && sb.charAt(sb.length() - 1) != word.charAt(sb.length() - 1)) || i < 0 || j < 0 ||  i >= board.length || j >= board[0].length || index[i][j] == 1)
            return false;

        sb.append(board[i][j]);
       // System.out.println(sb.toString());

        if (sb.toString().equals(word)){
            return true;
        }

        index[i][j] = 1; // 在一次遍历中，防止走回头路
        boolean res =  backtrack2(board, word, i + 1, j, index, sb) ||
                backtrack2(board, word, i, j + 1, index, sb) ||
                backtrack2(board, word, i - 1, j, index, sb) ||
                backtrack2(board, word, i, j - 1, index, sb);

        sb.deleteCharAt(sb.length() - 1); // 回溯
        index[i][j] = 0; // 在一次遍历中，防止走回头路

        return res;
    }



    //解法3 节省了o(M*N)的空间复杂度 5 ms
    //Runtime: 44 ms, faster than 67.22% of Java online submissions for Word Search.
    public boolean exist3(char[][] board, String word) {
        char[] strs = word.toCharArray();
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == strs[0] && dfs3(board, strs, i , j, 0)) return true;
            }
        }
        return false;
    }

    public boolean dfs3(char[][] board, char[] strs, int i, int j, int k){
        if(k == strs.length) return true;
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || strs[k] != board[i][j]) return false;
        board[i][j] = '#';
        boolean res = false;
        res = dfs3(board, strs, i + 1, j, k + 1) || dfs3(board, strs, i - 1, j, k + 1) || dfs3(board, strs, i, j + 1, k + 1) || dfs3(board, strs, i, j - 1, k + 1);

        board[i][j] = strs[k];

        return res;
    }


    // 2021-04-11日
    // 解法4
    public boolean exist4(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        int[][] flag = new int[rows][cols];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(board[i][j] == word.charAt(0) && dfs4(board, word, flag, i, j, 0)){
                    return true;
                }
            }
        }

        return false;
    }

    public boolean dfs4(char[][] board, String word, int[][] flag, int i, int j, int k){
        if(i < 0 || i >= board.length || j < 0 || j >= board[0].length || flag[i][j] == 1 || board[i][j] != word.charAt(k)) return false;

        if(k == word.length() - 1) return true;

        flag[i][j] = 1;
        k++;
        boolean res = dfs4(board, word, flag, i, j - 1, k)
                || dfs4(board, word, flag, i, j + 1, k)
                || dfs4(board, word, flag, i - 1, j, k)
                || dfs4(board, word, flag, i + 1, j, k);

        flag[i][j] = 0;

        return res;

    }
    public static void main(String[] args) {
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}};
       System.out.println(exist2(board, "BCCED"));
    }
}
