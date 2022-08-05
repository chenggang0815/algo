package Facebook._0791_Custom_Sort_String;
/*
791. Custom Sort String
You are given two strings order and s. All the words of order are unique and were sorted in some custom order previously.
Permute the characters of s so that they match the order that order was sorted. More specifically, if a character x occurs before a character y in order, then x should occur before y in the permuted string.
Return any permutation of s that satisfies this property.

Example 1:
Input: order = "cba", s = "abcd"
Output: "cbad"
Explanation:
"a", "b", "c" appear in order, so the order of "a", "b", "c" should be "c", "b", and "a".
Since "d" does not appear in order, it can be at any position in the returned string. "dcba", "cdba", "cbda" are also valid outputs.

Example 2:
Input: order = "cbafg", s = "abcd"
Output: "cbad"

Constraints:
1 <= order.length <= 26
1 <= s.length <= 200
order and s consist of lowercase English letters.
All the characters of order are unique.
 */

import java.util.HashMap;
import java.util.HashSet;

/*
solution 1: time: O(N*K) N = len(s) k = len(order)
order = "abgdcfde" s = "dazdczgzarx"
         01234567
iterate all the character in the s, base the index, insert the character to the right position
char = d
res = d
char = a
res = ad
      03

solution 2: time:O(N) N = len(s) space: O(N)
example:
order = "abgdcfde" s = "dazdczgzarx"

----- d a g in the order
d => number of d in the s => 2
a:2
g:1
------ z r x not in the order ===
z:3
r:1
x:1

res = aagddzzzrx

1. iterate all the character in the order, if current character in the s, then append the number of character to the result
2. fot those character which not in the order, iterate the s, use a hashset to check if this character occurs in the order
*/
public class Solution {
    static public String customSortString(String order, String s) {

        HashMap<Character, Integer> map = new HashMap<>();
        for(Character c: s.toCharArray()){
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        HashSet<Character> set = new HashSet<>();
        for(Character c: order.toCharArray()){
            set.add(c);
        }

        StringBuilder res = new StringBuilder();
        for(Character c:  order.toCharArray()){
            if(map.containsKey(c)){
                for(int i = 0; i < map.get(c); i++){
                    res.append(c);
                }
            }
        }

        for(Character c: s.toCharArray()){
            if(!set.contains(c)){
                res.append(c);
            }
        }

        return res.toString();
    }

    public String customSortString2(String S, String T) {
            // count[char] = the number of occurrences of 'char' in T.
            // This is offset so that count[0] = occurrences of 'a', etc.
            // 'count' represents the current state of characters
            // (with multiplicity) we need to write to our answer.
            int[] count = new int[26];
            for (char c: T.toCharArray())
                count[c - 'a']++;

            // ans will be our final answer.  We use StringBuilder to join
            // the answer so that we more efficiently calculate a
            // concatenation of strings.
            StringBuilder ans = new StringBuilder();

            // Write all characters that occur in S, in the order of S.
            for (char c: S.toCharArray()) {
                for (int i = 0; i < count[c - 'a']; ++i)
                    ans.append(c);
                // Setting count[char] to zero to denote that we do
                // not need to write 'char' into our answer anymore.
                count[c - 'a'] = 0;
            }

            // Write all remaining characters that don't occur in S.
            // That information is specified by 'count'.
            for (char c = 'a'; c <= 'z'; ++c)
                for (int i = 0; i < count[c - 'a']; ++i)
                    ans.append(c);

            return ans.toString();
    }


    public static void main(String[] args) {
        String order = "abgdcfde", s = "dazdczgzarx";
        System.out.println(customSortString(order, s));
    }
}
