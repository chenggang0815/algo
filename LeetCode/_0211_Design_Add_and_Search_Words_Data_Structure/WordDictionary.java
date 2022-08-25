package LeetCode._0211_Design_Add_and_Search_Words_Data_Structure;
/*

*/
public class WordDictionary {
    static class TrieNode{
        String word;
        TrieNode[] next;
        TrieNode(){
            word = "";
            next = new TrieNode[26];
        }
    }

    TrieNode root;
    public WordDictionary() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode curNode = root;
        for (char ch: word.toCharArray()){
            if (curNode.next[ch - 'a'] == null){
                curNode.next[ch - 'a'] = new TrieNode();
            }
            curNode = curNode.next[ch - 'a'];
        }

        curNode.word = word;
    }

    public boolean match(char[] word, int index, TrieNode node){
        if (index == word.length) return !node.word.equals("");
        if (word[index] != '.'){
            return node.next[word[index] - 'a'] != null && match(word, index + 1, node.next[word[index] - 'a']);
        }else{
            for (int i = 0; i < node.next.length; i++){
                if (node.next[i] != null){
                    if (match(word, index + 1, node.next[i])) return true;
                }
            }
        }

        return false;
    }

    public boolean search(String word) {
        return match(word.toCharArray(), 0, root);
    }

}
