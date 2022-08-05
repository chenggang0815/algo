package LeetCode._0217_Contains_Duplicate;
import java.util.Arrays;
import java.util.HashSet;
/*
217. Contains Duplicate
Given an array of integers, find if the array contains any duplicates.
Your function should return true if any value appears at least twice in the array,
and it should return false if every element is distinct.
Example 1:
Input: [1,2,3,1]
Output: true

Example 2:
Input: [1,2,3,4]
Output: false

Example 3:
Input: [1,1,1,3,3,4,3,2,4,2]
Output: true
*/
/*
题目拓展
这题类似题型非常之多，而且大多都是面试高频题 。

剑指 Offer 03. 数组中重复的数字
题目额外限定了数组元素的大小范围，所以有时间复杂度 O(n)，空间复杂度 O(1) 的做法。

287. 寻找重复数
题目也是额外限定了数组元素的大小范围（注意限定条件和上题不同！），最优做法是快慢指针。关于快慢指针的练习，还可以看这题快乐一下：202. 快乐数，我精心写了题解。

26. 删除排序数组中的重复项
做法也是快慢指针。非常经典的题目，C++ 标准库的 unique 方法就是 这么实现的。非常值得一刷。

136. 只出现一次的数字
超级经典，我相信绝大多数人已经做过了，没有做过的速速去会会它。姊妹题：137. 只出现一次的数字 II 和 260. 只出现一次的数字 III。这两题也是必刷题，刷了以后会对异或有更深入的了解和认识。其中 剑指 Offer 56 - I. 数组中数字出现的次数 是重复题目，我提供了一种 使用二分解决的思路，值得一看哦～
*/
/*
Solution
Approach 1: two for loop enumerate all the pair(i,j)
time: O(n^2)
space: O(1)

Approach 2: HashSet
time: O(n)
space: O(n)

Approach 3: sort
time: O(nlog(n))
space: O(1) for heapsort
*/
public class Solution {
    // approach 2
    static boolean containsDuplicate(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for (int num: nums){
            if(set.contains(num)) return true;
            set.add(num);
        }

        return false;
    }

    //比上面更加快 2022-08-05 没有比上面更快
    static boolean containsDuplicate2(int[] nums){
        HashSet<Integer> set = new HashSet<>();
        for(int num: nums) set.add(num);

        return set.size() != nums.length;
    }

    // approach 3
    public boolean containsDuplicate3(int[] nums) {
        Arrays.sort(nums);
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i + 1]) return true;
        }

        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsDuplicate2(new int[]{1,2,3,4}));
    }
}
