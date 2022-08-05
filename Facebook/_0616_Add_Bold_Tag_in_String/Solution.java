package Facebook._0616_Add_Bold_Tag_in_String;

import java.util.ArrayList;
import java.util.List;

/*
616. Add Bold Tag in String

You are given a string s and an array of strings words.
You should add a closed pair of bold tag <b> and </b> to wrap the substrings in s that exist in words.
If two such substrings overlap, you should wrap them together with only one pair of closed bold-tag.
If two substrings wrapped by bold tags are consecutive, you should combine them.
Return s after adding the bold tags.

Example 1:
Input: s = "abcxyz123", words = ["abc","123"]
Output: "<b>abc</b>xyz<b>123</b>"

Example 2:
Input: s = "aaabbcc", words = ["aaa","aab","bc"]
Output: "<b>aaabbc</b>c"

Constraints:
1 <= s.length <= 1000
0 <= words.length <= 100
1 <= words[i].length <= 1000
s and words[i] consist of English letters and digits.
All the values of words are unique.
*/
/*
Solution
Approach 1: interval merge
for example:
s= "aaabbcc" words = [aaa, aab, bc]
aaa: [0, 2]
aab: [1, 3]
bc:  [4, 5]
Output: "<b>aaabbc</b>c"

step 1: find the interval => [[0,2], [1,3], [4,5]]
step 2: merge the interval => [0, 5]
step 3: apply rule to the interval
*/
public class Solution {
    public String addBoldTag(String s, String[] words) {
        // step 1
        List<int[]> list = new ArrayList<>();
        for(String word : words){
            int index = -1;
            index = s.indexOf(word);
            while(index != -1){
                list.add(new int[]{index, index + word.length() - 1});
                index += 1;
                index = s.indexOf(word, index);
            }
        }
        // step 2 merge interval
        list.sort((a, b) -> a[0] - b[0]);
        List<int[]> mergeList = new ArrayList<>();
        for(int[] nums: list){
            if(mergeList.size() == 0 || mergeList.get(mergeList.size() - 1)[1] < nums[0] - 1){
                mergeList.add(nums);
            }else{
                mergeList.get(mergeList.size() - 1)[1] = Math.max(mergeList.get(mergeList.size() - 1)[1], nums[1]);
            }
        }
        // apply rule into interval
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(int[] nums: mergeList){
            if(index < nums[0]) sb.append(s.substring(index, nums[0]));
            sb.append("<b>");
            sb.append(s.substring(nums[0], nums[1] + 1));
            sb.append("</b>");
            index = nums[1] + 1;
        }
        if(index < s.length()) sb.append(s.substring(index, s.length()));

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "hello, world, world";
        System.out.println(s.indexOf("world", 8));
    }
}
