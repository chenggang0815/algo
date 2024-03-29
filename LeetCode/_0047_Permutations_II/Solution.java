package LeetCode._0047_Permutations_II;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
47. Permutations II
Given a collection of numbers, nums, that might contain duplicates, return all possible unique permutations in any order.
Example 1:

Input: nums = [1,1,2]
Output:
[[1,1,2],
 [1,2,1],
 [2,1,1]]
*/
/*
思考：回溯+剪枝
相比46题，序列中多了重复的数字，所以需要再次基础上剪枝
思路1. Runtime: 551 ms
    1. 和46题保持同样的剪枝条件 => 可以保证list不出现相同（index）的元素
    2. 在最后把list放入res的时，判断是否有相同的集合
思路2. Runtime: 1 ms
    2.1 先对数组排序
    2.2 剪枝
    剪枝条件1: 和46题相同 if (flag[i] == 1) continue;
    剪枝条件2：if (i > 0 && nums[i] == nums[i - 1] && flag[i - 1] == 0) continue;
    剪枝条件2是为了保证：每次选取的数字，在重复集合nums中是从左往右第一个未被选取过的数字

            2
         /  |  \
        1   1'  2
      / | \ c   d
     1  1' 2
     a  ok b
    遍历到2时，a,b,d都命中剪枝条件1，c命中剪枝条件2
    即遍历到c时，有：
    1. i=2
    2. flag = [0,0,1] 1已经使用过=>flag[i]=0，1’的结果肯定和1的结果一样，故剪枝
    因此有=>if (i > 0 && nums[i] == nums[i - 1] && flag[i - 1] == 0) continue;

参考：https://leetcode-cn.com/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
*/
public class Solution {
    static public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        backtrack1(nums, res, new ArrayList<>(), new int[nums.length]);

        return res;
    }

    //Runtime: 1 ms, faster than 99.29% of Java online submissions for Permutations II.
    static void backtrack1(int[] nums, List<List<Integer>> res, List<Integer> list, int[] flag){
        if (list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if (flag[i] == 1) continue;
            // 参考40题的剪枝条件，因为每次i都是从0开始，没法比较当前递归层的index和i的大小，所以只能用f[i - 1]判断前一个元素是不是在同一层被遍历过
            // flag[i - 1] 表示上一个元素没有被遍历过，当前元素为新的list的第一个元素，这样会造成重复解，所以需要skip
            if (i > 0 && nums[i] == nums[i - 1] && flag[i - 1] == 0) continue;
            //if (i > 0 && nums[i] == nums[i - 1] && flag[i - 1] == 0) break; 用break直接结束整个for循环，停止后面所有递归，所以错误
            flag[i] = 1;
            list.add(nums[i]);
            backtrack1(nums, res, list, flag);
            list.remove(list.size() - 1);
            flag[i] = 0;
        }
    }

    // Runtime: 551 ms, faster than 5.23% of Java online submissions for Permutations II.
    public void backtrack2(int[] nums, List<List<Integer>> res, List<Integer> list, int[] flag){
        if(list.size() == nums.length){
            if(!res.contains(list)){
                res.add(new ArrayList<>(list));
            }
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if(flag[i] == 0){
                flag[i] = 1;
                list.add(nums[i]);
                backtrack2(nums, res, list, flag);
                list.remove(list.size() - 1);
                flag[i] = 0;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1,1,2};
        System.out.println(permuteUnique(nums));

    }
}
