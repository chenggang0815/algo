package Amazon._1268_Search_Suggestions_System;
import java.util.ArrayList;
import java.util.List;
/*
class function: use trie to find all the words match the prefix
 */
public class Solution3 {
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

        List<String> searchFromPrefix(String prefix){
            List<String> res = new ArrayList<>();
            TrieNode cur = root;
            for (char c : prefix.toCharArray()){
                if (cur.node[c - 'a'] == null) return res;
                cur = cur.node[c - 'a'];
            }
            searchFromPrefix(cur, prefix, res);

            return res;
        }

        void searchFromPrefix(TrieNode cur, String word, List<String> res){
            if (cur.isWord) res.add(word);
            for (char c = 'a'; c <= 'z'; c++){
                if (cur.node[c - 'a'] != null){
                    searchFromPrefix(cur.node[c - 'a'], word + c, res);
                }
            }
        }
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("batman");
        trie.insert("banana");
        trie.insert("hello");
        System.out.println(trie.searchFromPrefix("ba"));
    }
}
