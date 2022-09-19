package LeetCode._2416_Sum_of_Prefix_Scores_of_Strings;
/*
1. 每个节点存当前的前缀出现的次数
2. 在统计每个前缀的出现次数的时候，不要每次从头开始查找
比如 "abcd" 的前缀有"a" "ab" "abc" "abcd"
超时的做法：分别单独遍历"a" "ab" "abc" "abcd"
更快的做法：遍历到"a"，记下"a"的出现次数，从"a"遍历到"ab"，记下"ab"出现的次数，直到遍历到"abcd"，
          相当只遍历一次"abcd"，记下沿途每个前缀出现的次数。

*/
public class Solution2 {
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

        void insert(String str){
            TrieNode cur = root;
            for (char c : str.toCharArray()){
                if (cur.node[c - 'a'] == null){
                    cur.node[c - 'a'] = new TrieNode();
                }
                cur = cur.node[c - 'a'];
                cur.wordCnt++;
            }
        }

    }

    public int[] sumPrefixScores(String[] words) {
        Trie trie = new Trie();
        for(String word: words){
            trie.insert(word);
        }

        int[] res = new int[words.length];
        int i = 0;

        for(String word: words){
            Trie.TrieNode cur = trie.root;
            for(char c: word.toCharArray()){
                // 更快的做法：遍历到"a"，记下"a"的出现次数，从"a"遍历到"ab"，记下"ab"出现的次数，直到遍历到"abcd"，
                //           相当只遍历一次"abcd"，记下沿途每个前缀出现的次数。
                res[i] += cur.node[c - 'a'].wordCnt;
                cur = cur.node[c - 'a'];
            }

            i++;
        }

        return res;
    }
}
