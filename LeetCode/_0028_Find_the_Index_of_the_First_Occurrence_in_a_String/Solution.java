package LeetCode._0028_Find_the_Index_of_the_First_Occurrence_in_a_String;
/*
28. Implement strStr() Easy
Implement strStr().
Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:
Input: haystack = "hello", needle = "ll"
Output: 2

Example 2:
Input: haystack = "aaaaa", needle = "bba"
Output: -1

Clarification:
What should we return when needle is an empty string? This is a great question to ask during an interview.
For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().
 */
/*
1. brute force
2. use String API startsWith() => return bool/false
3. KMP algorithm
*/
public class Solution {
    // time: O(n^2) space:O(n)
    //Runtime: 456 ms, faster than 9.35% of Java online submissions for Implement strStr().
    static public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        char[] hay = haystack.toCharArray();
        char[] nee = needle.toCharArray();
        for (int i=0;i<hay.length;i++){
            int j=0;
            int temp = i;
            while (j<nee.length&&temp<hay.length){
                if (nee[j]==hay[temp]){
                    temp++;
                    j++;
                }else j++;
            }
            if ((temp-i)==nee.length){
                return i;
            }
        }
        return -1;
    }
    //Runtime: 5 ms, faster than 17.19% of Java online submissions for Implement strStr().
    static public int strStr2(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        char[] hay = haystack.toCharArray();
        for (int i=0;i<hay.length;i++){
            StringBuffer str5 = new StringBuffer();
            int j = 0;
            while (j< needle.length()&&i+needle.length()-1<hay.length){
                str5.append(hay[i+j]);
                j++;
            }
            if (str5.toString().equals(needle)) return i;
        }
        return -1;
    }

    //
    public int strStr3(String haystack, String needle) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < haystack.length()){
            if(haystack.charAt(i) == needle.charAt(0)){
                String s = haystack.substring(i, haystack.length());
                if(s.startsWith(needle)) return i;
                i++;
            }else{
                i++;
            }
        }

        return -1;
    }

    // this solution is not correct, consider the example below.
    // "mississippi"
    //      ji
    //  "issip" => we can not just move the the position i and the start search again, because the correct position is j
    //    "issip"

    public int strStr4(String haystack, String needle) {
        int index = 0;
        while(index < haystack.length()){
            if(haystack.charAt(index) == needle.charAt(0)){
                int j = 0;
                int i = index;
                while(i < haystack.length() && j < needle.length() && haystack.charAt(i) == needle.charAt(j)){
                    i++;
                    j++;
                }

                if(j == needle.length()) return index;

                index = i;
            }else{
                index++;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        String haystack = "a";
        String needle = "a";
        System.out.println(strStr2(haystack,needle));

    }
}
