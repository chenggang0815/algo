package LeetCode._0212_Word_Search_II;

import java.util.ArrayList;
import java.util.List;

/*
212. Word Search II

Given an m x n board of characters and a list of strings words, return all words on the board.
Each word must be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring.
The same letter cell may not be used more than once in a word.

example:
Input: board = [["o","a","a","n"],
                ["e","t","a","e"],
                ["i","h","k","r"],
                ["i","f","l","v"]],
      words = ["oath","pea","eat","rain"]

Output: ["eat","oath"]

Constraints:
1. m == board.length
2. n == board[i].length
3. 1 <= m, n <= 12
4. board[i][j] is a lowercase English letter.
5. 1 <= words.length <= 3 * 104
6. 1 <= words[i].length <= 10
7. words[i] consists of lowercase English letters.
8. All the strings of words are unique.
*/
/*
Solution
Approach 1: backtracking + trie
*/
public class Solution {
    public static class Trie{
        class TrieNode{
            boolean isWord;
            TrieNode[] node;
            TrieNode(){
                this.isWord = false;
                this.node = new TrieNode[26];
            }
        }

        TrieNode root = new TrieNode();

        void insert(String str){
            TrieNode cur = root;
            for (char c : str.toCharArray()){
                if (cur.node[c - 'a'] == null){
                    cur.node[c - 'a'] = new TrieNode();
                }
                cur = cur.node[c - 'a'];
            }

            cur.isWord = true;
        }
    }

    static public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        Trie trie = new Trie();
        Trie.TrieNode root = trie.root;
        for (String word : words) trie.insert(word);
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[0].length; j++){
                dfs(board, i, j, root, res, new StringBuilder());
            }
        }

        return res;
    }

    static void dfs(char[][] board, int i, int j, Trie.TrieNode root, List<String> res, StringBuilder word){
        if (root.isWord){
            res.add(word.toString());
            root.isWord = false;
        }
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') return;

        char c = board[i][j];
        root = root.node[c - 'a'];
        if (root == null) return;

        board[i][j] = '#';
        word.append(c);
        dfs(board, i + 1, j, root, res, word);
        dfs(board, i - 1, j, root, res, word);
        dfs(board, i, j + 1, root, res, word);
        dfs(board, i, j - 1, root, res, word);
        board[i][j] = c;
        word.deleteCharAt(word.length() - 1); // why we need this line, edge case => words = ["oath","pea","eat","rain","hklf", "hf"]
    }

    public static void main(String[] args) {
        String[] words = new String[]{"oath","pea","eat","rain"};
        char[][] board = new char[][]{{'o','a','a','n'}, {'e','t','a','e'}, {'i','h','k','r'}, {'i','f','l','v'}};
        System.out.println(findWords(board, words));
    }
}
