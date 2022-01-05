package Facebook._0249_Group_Shifted_Strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
249. Group Shifted Strings
We can shift a string by shifting each of its letters to its successive letter.

For example, "abc" can be shifted to be "bcd".
We can keep shifting the string to form a sequence.

For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.

Example 1:
Input: strings = ["abc","bcd","acef","xyz","az","ba","a","z"]
Output: [["acef"],["a","z"],["abc","bcd","xyz"],["az","ba"]]

Example 2:
Input: strings = ["a"]
Output: [["a"]]

Constraints:
1. 1 <= strings.length <= 200
2. 1 <= strings[i].length <= 50
3. strings[i] consists of lowercase English letters.
*/
/*
Solution
Approach 1: time:O(n) n is the sum of length of all word in input
1. for word "abc",
   we can find the pattern that b - a = 1
                               c - a = 1
  for word ""bcd"
   we can find the pattern that c - b = 1
                               d - b = 1
  so each character in the word minus the first character form a sequence of integer, which represent the distance between current character to the first character

2. abc...za => b to a and a to z both means move from right to left one position
   for word "ba" => a - b = -1
   for word "az" => z - a = 25
   so, if distance < 0 => we can find distance = distance + 26 = -1 + 26 = z - a
*/
public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strings){
            String key = getKey(s);
            if(!map.containsKey(key)) map.put(key, new ArrayList<>());
            map.get(key).add(s);
        }

        return new ArrayList<>(map.values());
    }

    public String getKey(String s){
        StringBuilder key = new StringBuilder();
        char c = s.charAt(0); // 4 ms -> 2ms
        for(int i = 1; i < s.length(); i++){
            int distance = s.charAt(i) - c;
            distance = distance > 0 ? distance : distance + 26;
            key.append(distance).append(" ");
        }

        return key.toString();
    }

    public static void main(String[] args) {
        System.out.println('z' - 'a');
    }
}
