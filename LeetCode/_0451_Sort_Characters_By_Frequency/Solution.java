package LeetCode._0451_Sort_Characters_By_Frequency;
/*
451. Sort Characters By Frequency

Given a string s, sort it in decreasing order based on the frequency of the characters.
The frequency of a character is the number of times it appears in the string.
Return the sorted string. If there are multiple answers, return any of them.

Example 1:
Input: s = "tree"
Output: "eert"
Explanation: 'e' appears twice while 'r' and 't' both appear once.
So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
Approach 1: HashMap + List + StringBuilder
1. hashmap<Character, count> map
2. List<character> list = map.keySet() => list sort by count of character
3. use stringBuilder to construct string
*/
public class Solution {
    public String frequencySort(String s) {

        HashMap<Character, Integer> cnt = new HashMap<>();
        for(char ch: s.toCharArray()){
            cnt.put(ch, cnt.getOrDefault(ch, 0) + 1);
        }

        List<Character> list = new ArrayList<>(cnt.keySet());
        list.sort((a, b) -> cnt.get(b) - cnt.get(a));

        StringBuilder sb = new StringBuilder();
        for(Character ch: list){
            for(int i = 0; i < cnt.get(ch); i++) sb.append(ch);
        }

        return sb.toString();
    }

    public static void main(String[] args) {

    }
}
