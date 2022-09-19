package LeetCode._2416_Sum_of_Prefix_Scores_of_Strings;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

class Solution {
    class Trie{
        class TrieNode{
            int wordCnt;
            TrieNode[] node;
            TrieNode(){
                this.wordCnt = 0;
                this.node = new TrieNode[26];
            }
        }

        TrieNode root = new TrieNode();
        HashMap<String, Integer> map = new HashMap<>();

        void insert(String str){
            TrieNode cur = root;
            StringBuilder prefix = new StringBuilder();
            for (char c : str.toCharArray()){
                prefix.append(c);
                if (cur.node[c - 'a'] == null){
                    cur.node[c - 'a'] = new TrieNode();
                }
                cur = cur.node[c - 'a'];
                cur.wordCnt++;
                map.put(prefix.toString(), cur.wordCnt);
            }
        }

    }

    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }

        HashMap<String, Integer> map = new HashMap<>();

        int[] res = new int[words.length];
        int i = 0;

        for(String word: words){
            StringBuilder prefix = new StringBuilder();
            for(char ch: word.toCharArray()){
                prefix.append(ch);
                res[i] += trie.map.get(prefix.toString());
            }

            i++;
        }

        return res;
    }


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        String str = "";
        String start = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(Calendar.getInstance().getTime());
        System.out.println(start);
        for (int i = 0; i < 10000; i++){
            //sb.append('a');
            str += 'a';
        }

        String end = new SimpleDateFormat("yyyyMMdd_HH-mm-ss").format(Calendar.getInstance().getTime());
        System.out.println(end);
    }
}
