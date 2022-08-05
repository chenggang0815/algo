package LeetCode._0316_Remove_Duplicate_Letters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;

/*
316. 去除重复字母
给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。

注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同

示例 1：
输入：s = "bcabc"
输出："abc"
示例 2：

输入：s = "cbacdcbc"
输出："acdb"

提示：
1 <= s.length <= 104
s 由小写英文字母组成
 */
/*
与402不同，这道题没有一个全局的删除次数 k。而是对于每一个在字符串 s 中出现的字母 c 都有一个 k 值。这个 k 是 c 出现次数 - 1。

沿用上面的知识的话，我们首先要做的就是计算每一个字符的 k，可以用一个字典来描述这种关系，其中 key 为 字符 c，value 为其出现的次数。

具体算法：

建立一个字典。其中 key 为 字符 c，value 为其出现的剩余次数。
从左往右遍历字符串，每次遍历到一个字符，其剩余出现次数 - 1.
对于每一个字符，如果其对应的剩余出现次数大于 1，我们可以选择丢弃（也可以选择不丢弃），否则不可以丢弃。
是否丢弃的标准和上面题目类似。如果栈中相邻的元素字典序更大，那么我们选择丢弃相邻的栈中的元素。
还记得上面题目的边界条件么？如果栈中剩下的元素大于 n - kn−k，我们选择截取前 n - kn−k 个数字。然而本题中的 k 是分散在各个字符中的，因此这种思路不可行的。

不过不必担心。由于题目是要求只出现一次。我们可以在遍历的时候简单地判断其是否在栈上即可

作者：fe-lucifer
链接：https://leetcode-cn.com/problems/remove-k-digits/solution/yi-zhao-chi-bian-li-kou-si-dao-ti-ma-ma-zai-ye-b-5/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

/*
时间复杂度分析：
对于每一个字符，需要判断字符是否在stack中 所以是o(n^2)的时间复杂度

可以考虑使用set来判断字符是否在其中 => 时间复杂度o(n) 空间复杂度o(n)

 */
public class Solution {
    static String removeDuplicateLetters(String s) {
         HashMap<Character, Integer> map = new HashMap<>();
         for(int i = 0; i < s.length(); i++){
             int temp = map.getOrDefault(s.charAt(i), 0) + 1;
             map.put(s.charAt(i), temp);
         }

         Stack<Character> stack = new Stack<>();
         for(int i = 0; i < s.length(); i++){
             Character ch = s.charAt(i);
             if (!stack.contains(ch)){
                 while(!stack.isEmpty() && stack.peek() > ch &&  map.get(stack.peek()) > 0){
                     stack.pop();
                 }
                 stack.push(ch);
             }
             map.put(ch, map.get(ch) - 1);
         }

         String res = "";
         while (!stack.isEmpty()){
             res = stack.pop() + res;
         }
        return res;
    }

    static String removeDuplicateLetters2(String s) {
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s.length(); i++){
            int temp = map.getOrDefault(s.charAt(i), 0) + 1;
            map.put(s.charAt(i), temp);
        }

        HashSet<Character> set = new HashSet<>();
        Stack<Character> stack = new Stack<>();
        for(int i = 0; i < s.length(); i++){
            Character ch = s.charAt(i);
            if (!set.contains(ch)){
                while(!stack.isEmpty() && stack.peek() > ch &&  map.get(stack.peek()) > 0){
                    set.remove(stack.pop());
                }
                stack.push(ch);
                set.add(ch);
            }
            map.put(ch, map.get(ch) - 1);
        }

        String res = "";
        while (!stack.isEmpty()){
            res = stack.pop() + res;
        }
        return res;
    }

    public static void main(String[] args) {
       System.out.println(removeDuplicateLetters2("bbcaac"));
    }
}
