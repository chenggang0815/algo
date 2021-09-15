package 剑指Offer._34_第一个只出现一次的字符;

import java.util.HashMap;

/*
第一个只出现一次的字符

在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
如果没有则返回 -1（需要区分大小写）.（从0开始计数）

思路: 遍历两次，第一次用hashmap存每个字符的个数，第二次从字符串左边开始找到一个个数为1的字符
 */
public class Solution {
    // time: o(n) space: o(n)
    static int FirstNotRepeatingChar(String str) {

        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++){
            map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1 );
        }

        for (int i = 0; i < str.length(); i++){
            if (map.get(str.charAt(i)) == 1){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(FirstNotRepeatingChar("  "));
    }
}
