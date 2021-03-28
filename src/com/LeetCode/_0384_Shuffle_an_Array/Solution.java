package com.LeetCode._0384_Shuffle_an_Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/*
384. Shuffle an Array
给你一个整数数组 nums ，设计算法来打乱一个没有重复元素的数组。
实现 Solution class:
Solution(int[] nums) 使用整数数组 nums 初始化对象
int[] reset() 重设数组到它的初始状态并返回
int[] shuffle() 返回数组随机打乱后的结果
 
示例：
输入
["Solution", "shuffle", "reset", "shuffle"]
[[[1, 2, 3]], [], [], []]
输出
[null, [3, 1, 2], [1, 2, 3], [1, 3, 2]]
解释
Solution solution = new Solution([1, 2, 3]);
solution.shuffle();    // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。例如，返回 [3, 1, 2]
solution.reset();      // 重设数组到它的初始状态 [1, 2, 3] 。返回 [1, 2, 3]
solution.shuffle();    // 随机返回数组 [1, 2, 3] 打乱后的结果。例如，返回 [1, 3, 2]
 
提示：
1 <= nums.length <= 200
-106 <= nums[i] <= 106
nums 中的所有元素都是 唯一的
最多可以调用 5 * 104 次 reset 和 shuffle
*/

/*
思路1：time：o(n^2) space:o(n)
1. 首先把数组array复制一份给list，之后每次随机从list中取一个数，为了防止数被重复取出，每次取完就把这个数从list中移除
2. 重置只需把array恢复称最开始的状态就可以了，因此需要一个数组original来保存原始数组
3. 这个算法的正确性在于，每次for循环中，任何一个元素都会以等可能的概率被选中

思路2： Fisher-Yates 洗牌算法 time：o(n) space:o(n)
1. 在每次迭代中，生成一个范围在当前下标到数组末尾元素下标之间的随机整数
2. 接下来，将当前元素和随机选出的下标所指的元素互相交换 => 其中选取下标范围的依据在于每个被选中的元素都不可能再被选中了
3. 此外还有一个需要注意的细节，当前元素是可以和它本身互相交换的 => 否则生成最后的排列组合的概率就不对了
*/
public class Solution {
    private int[] array;
    private int[] original;
    private Random rand = new Random();

    private List<Integer> getArrayCopy(){
        List<Integer> list = new ArrayList<>();
        for (int num: array){
            list.add(num);
        }
        return list;
    }

    public Solution(int[] nums) {
            array = nums;
            original = nums.clone();
    }
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {

            array = original;
            original = original.clone(); // 保证reset这个方法的正确性，因为original的地址被赋给了array，但在shuffle方法里面被修改了。
                                        // 如果不克隆的话，下次再shuffle的时候岂不是对一个乱序的数组进行操作，reset返回的数组和shuffle返回的数组是一样的乱序
            return array;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() { // 时间复杂度o(n^2) =>  list.remove(randomIndex); 为o(n)
            List<Integer> list = getArrayCopy();
            for (int i = 0; i < array.length; i++){
                int randomIndex = rand.nextInt(list.size());
                array[i] = list.get(randomIndex);
                list.remove(randomIndex);
            }

            return array;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4};
        Solution s = new Solution(nums);
        nums = s.shuffle();
        System.out.println(Arrays.toString(nums));
        nums = s.reset();
        System.out.println(Arrays.toString(nums));
        nums = s.shuffle();
        System.out.println(Arrays.toString(nums));
        nums = s.reset();
        System.out.println(Arrays.toString(nums));

    }
}
