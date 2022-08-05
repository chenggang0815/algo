package LeetCode._0290_Word_Pattern;

import java.util.HashMap;

/*
290. Word Pattern
Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in s.

Example 1:
Input: pattern = "abba", s = "dog cat cat dog"
Output: true

Example 2:
Input: pattern = "abba", s = "dog cat cat fish"

Output: false

Example 3:
Input: pattern = "aaaa", s = "dog cat cat dog"
Output: false

Example 4:
Input: pattern = "abba", s = "dog dog dog dog"
Output: false

Constraints:
1 <= pattern.length <= 300
pattern contains only lower-case English letters.
1 <= s.length <= 3000
s contains only lower-case English letters and spaces ' '.
s does not contain any leading or trailing spaces.
All the words in s are separated by a single space.
 */
/*
Solution：利用两个hashmap，分别映射s2p和p2s，依次判断是否相匹配
example 1 => Input: pattern = "abba", s = "dog cat cat dog"
i=0, a -> dog => a not in p2s, p2s.put(a, dog)
     dog->a => dog not in s2p, s2p.put(dog, a)
i=1, b -> cat cat->b
i=2, p2s.get(b)=cat, s2p.get(cat)=b
i=3, p2s.get(a)=dog, s2p.get(dog)=a
=> return ture

example 2 => Input: pattern = "abba", s = "dog dog dog dog"
i=0, a -> dog => a not in p2s, p2s.put(a, dog)
     dog->a => dog not in s2p, s2p.put(dog, a)
i=1, b -> dog  => b not in p2s, p2s.put(b, dog)
     dog->b => dog in s2p, s2p.get(dog) != b => return false;

time:o(m+n) m和n分别是pattern和s的长度，其中对s按照空格切分需要o(n)的时间复杂度
space:o(m+n)

similar question：
205. Isomorphic Strings
 */
public class Solution {

    static boolean wordPattern(String pattern, String s) {
        String[] str = s.split(" ");
        if (pattern.length() != str.length) return false;

        HashMap<Character, String> p2s = new HashMap<>();
        HashMap<String, Character> s2p = new HashMap<>();
        for (int i = 0; i < str.length; i++){
            char p = pattern.charAt(i);
            if (!p2s.containsKey(p)){
                p2s.put(p, str[i]);
            }else {
                if (!p2s.get(p).equals(str[i])) return false;
            }
            if (!s2p.containsKey(str[i])){
                s2p.put(str[i], p);
            }else {
                if (s2p.get(str[i]) != p) return false;
            }
        }

        return true;
    }

    public boolean wordPattern2(String pattern, String s) {
        HashMap<String, Character> w2p = new HashMap<>();
        HashMap<Character, String> p2w = new HashMap<>();
        String[] words = s.split(" ");
        if (words.length != pattern.length()) return false;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Character c = pattern.charAt(i);
                            // character compare to character                       // string compare to string
            if (w2p.containsKey(word) && w2p.get(word) != c  || p2w.containsKey(c) && !p2w.get(c).equals(word)) return false;
            w2p.put(word, c);
            p2w.put(c, word);
            }

        return true;
    }

    public static void main(String[] args) {
       // System.out.println(wordPattern("abba", "dog cat cat dog"));
      //  System.out.println(wordPattern("abba", "dog dog dog dog"));
        HashMap<String, Character> map = new HashMap<>();
        map.put("a", 'a');
        Character s = 'a';
        System.out.println(map.get("a").equals(s));
        System.out.println(s.equals(map.get("a")));
        System.out.println(map.get("a") == s);
        System.out.println(s == map.get("a"));
    }
}
