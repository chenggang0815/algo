package LeetCode._0423_Reconstruct_Original_Digits_from_English;
/*
423. Reconstruct Original Digits from English
Given a string s containing an out-of-order English representation of digits 0-9, return the digits in ascending order.
Example 1:
Input: s = "owoztneoer"
Output: "012"

Example 2:
Input: s = "fviefuro"
Output: "45"

Constraints:
1 <= s.length <= 105
s[i] is one of the characters ["e","g","f","i","h","o","n","s","r","u","t","w","v","x","z"].
s is guaranteed to be valid.
*/
/*
1. "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"
2. all the character from "zero" to "nine"
   => "z", "e", "r", "o", "n", "t", "w", "h", "f", "u", "i", "v", "s", "x", "g"
Hence the idea is to look for something unique. One could notice that all even numbers contain each a unique letter:
1.  "z" is present only in "zero".
2.  "w" is present only in "two".
3.  "u" is present only in "four".
4.  "x" is present only in "six".
5.  "g" is present only in "eight".
Hence there is a good way to count 0 2 4 6 8 numbers.
digit[0] = count['z']
digit[2] = count['w']
digit[4] = count['u']
digit[6] = count['x']
digit[8] = count['g']

1.  "h" is present only in "three" and "eight".  3,8
2.  "f" is present only in "five" and "four".    4,5
3.  "s" is present only in "seven" and "six".   6,7
digit[3] = count['h'] - digit[8];
digit[5] = count['f'] - digit[4];
digit[7] = count['s'] - digit[6];

1.  "i" is present in "nine", "five", "six", and "eight" => digit[9] = count['i'] - 5,6,8;
2.  "n" is present in "one", "seven", and "nine".

*/
public class Solution {
    static String originalDigits(String s) {
        int[] digit = new int[10];
        int[] count = new int[26];
        for (char c : s.toCharArray()){
            count[c - 'a']++;
        }

        digit[0] = count['z' - 'a'];
        digit[2] = count['w' - 'a'];
        digit[4] = count['u' - 'a'];
        digit[6] = count['x' - 'a'];
        digit[8] = count['g' - 'a'];

        digit[3] = count['h' - 'a'] - digit[8];
        digit[5] = count['f' - 'a'] - digit[4];
        digit[7] = count['s' - 'a'] - digit[6];

        digit[9] = count['i' - 'a'] - digit[5] - digit[6] - digit[8];
        digit[1] = count['n' - 'a'] - digit[7] - digit[9] *2 ; // one 9 user two 'n'
        StringBuilder sb = new StringBuilder();
        // 0 => 2
        // 1 => 3
        // 22333
        // i=0 digit[i]=2
        for (int i = 0; i <= 9; i++){
            for (int j = 1; j <= digit[i]; j++){
                sb.append(i);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
//        List<String> list = Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine");
//        HashSet<String> set = new HashSet<>(list);
//        System.out.println(set);
        System.out.println(originalDigits("nnei"));
    }
}
