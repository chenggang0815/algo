package LeetCode._0438_Find_All_Anagrams_in_a_String;

import java.util.ArrayList;
import java.util.List;

/*
438. Find All Anagrams in a String

Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
The order of output does not matter.

Example 1:
Input:
s: "cbaebabacd" p: "abc"
Output:
[0, 6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
*/
/*
思考：窗口滑动+左右指针
1. 一开始还是先将字符串转换为字符数组，定义一个ans来接收结果
2. 这里使用了两个数组needs和window来分别记录需要得到的元素和滑动窗口遍历到的元素
3. 首先把目标数组arrP中有的元素都放入needs中，然后通过不断移动滑动窗口将目标元素的个数保存到window中
4. 如果window数组中记录的元素个数超过了needs数组的元素个数，则开始移动左窗口慢慢减少多余的个数
5. 最后把整个遍历过程中所有符合要求的左窗口索引放到ans中并返回即可

作者：Jason_H
链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/solution/20200321438median-by-jasion_han-r/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution {
    static List<Integer> findAnagrams1(String s, String p) {
        char[] arrS = s.toCharArray();
        char[] arrP = p.toCharArray();

        List<Integer> res = new ArrayList<>();

        int[] needs = new int[26];
        int[] windows = new int[26];

        for (char ch: arrP){
            needs[ch - 'a'] += 1;
        }

        int left = 0;
        int right = 0;
        while (right < arrS.length){
            int curR = arrS[right] - 'a';

            right++;

            windows[curR] += 1;
            // 在右指针移动的过程中判断当前字符的元素超过了needs中它对应的次数（needs中没有也是超过），超过了我就移动做指针让他个数正好相等
            // 1. 一定要保证当前字符的出现次数是满足要求的
            // 2. 在这个基础上 => 如果当左右指针之差为p的长度的时候，窗口内的字符串就是满足要求的字母异位词
            /*
            s = "bacdefbgac" p="abcdefg"
            left = 0 right = 6 时，window中有两个b
            => 不满足要求 => left++ => left = 1
            while循环结束 => 此时可以保证当前window中字符的出现次数是满足要求的 => acdefb都是一次
            right再向右移动一次，同时满足要求1,2 => left加入答案

            s = "cbaeacb" p="abc"
            left = 0 right = 2 left加入答案
            left = 0 right = 3 => window[e]=1 > need[e]=0
            进入while循环直到left=4
            left = 4 right = 6 left加入答案
            所以每次while循环结束后，能保证[left，right]直接满足要求1
             */
            while (windows[curR] > needs[curR]){
                int curL = arrS[left] - 'a';
                windows[curL] = windows[curL] - 1;
                left++;
            }

            if (right - left == arrP.length){
                res.add(left);
            }
        }

        return res;
    }

    static List<Integer> findAnagrams2(String s, String p) {
        char[] arrS = s.toCharArray();
        char[] arrP = p.toCharArray();

        List<Integer> res = new ArrayList<>();

        int[] needs = new int[26];
        int[] windows = new int[26];

        for (char ch: arrP){
            needs[ch - 'a'] += 1;
        }

        int left = 0;
        int right = 0;
        while (right < arrS.length){
            int curR = arrS[right] - 'a';
            windows[curR] += 1;

            while (windows[curR] > needs[curR]){
                int curL = arrS[left] - 'a';
                windows[curL] = windows[curL] - 1;
                left++;
            }

            if (right - left + 1 == arrP.length){
                res.add(left);
            }

            right++;
        }

        return res;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        System.out.println(findAnagrams1(s, p));
    }
}
