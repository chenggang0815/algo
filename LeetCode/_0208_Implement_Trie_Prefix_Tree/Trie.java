package LeetCode._0208_Implement_Trie_Prefix_Tree;
/*
208. Implement Trie (Prefix Tree)

Implement a trie with insert, search, and startsWith methods.

Example:

Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");   // returns true
trie.search("app");     // returns false
trie.startsWith("app"); // returns true
trie.insert("app");
trie.search("app");     // returns true

Note:
You may assume that all inputs are consist of lowercase letters a-z.
All inputs are guaranteed to be non-empty strings.
 */

/*
Trie 树是一个有根的树，其结点具有以下字段：。

最多R个指向子结点的链接，其中每个链接对应字母表数据集中的一个字母。
本文中假定R 为 26，小写拉丁字母的数量。
布尔字段，以指定节点是对应键的结尾还是只是键前缀

插入
描述：向 Trie 中插入一个单词 word
实现：这个操作和构建链表很像。首先从根结点的子结点开始与word第一个字符进行匹配，一直匹配到前缀链上没有对应的字符，这时开始不断开辟新的结点，直到插入完 word 的最后一个字符，
同时还要将最后一个结点isEnd = true; 表示它是一个单词的末尾

查找
描述：查找 Trie 中是否存在单词 word
实现：从根结点的子结点开始，一直向下匹配即可，如果出现结点值为空就返回 false，如果匹配到了最后一个字符，那我们只需判断 node->isEnd即可

前缀匹配
描述：判断 Trie 中是或有以 prefix 为前缀的单词
实现：和 search 操作类似，只是不需要判断最后一个字符结点的isEnd，因为既然能匹配到最后一个字符，那后面一定有单词是以它为前缀的

 */
public class Trie {
    class TrieNode{
        boolean isWord;
        TrieNode[] next;
        public TrieNode(){
            isWord = false;
            next = new TrieNode[26];
        }
    }

     TrieNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        for (char c: word.toCharArray()){
            if (cur.next[c - 'a'] == null) cur.next[c - 'a'] = new TrieNode();
            cur = cur.next[c - 'a'];
        }

        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        for (char c: word.toCharArray()){
            cur = cur.next[c - 'a'];
            if (cur == null) return false;
        }

        return cur.isWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        for (char c: prefix.toCharArray()){
            cur = cur.next[c - 'a'];
            if (cur == null) return false;
        }

        return true;
    }


    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));   // returns true
        System.out.println(trie.search("app"));     // returns false
        System.out.println(trie.startsWith("app")); // returns true
        trie.insert("app");
        System.out.println(trie.search("app"));     // returns true
    }
}
