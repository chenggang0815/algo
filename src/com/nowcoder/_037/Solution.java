package com.nowcoder._037;

import java.util.HashMap;

/*
数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
如果不存在则输出0。
 */

/*
解题思路1：先快排，再统计中间数的个数看是否大于一半 ，时间复杂度：o(n*log(n)) space: o(1)
 */

/*
解题思路2: 利用hashmap存储每个数字的个数 time: o(n) space: o(n)
 */

/*
解题思路3: 守擂台算法 time:o(n) space:o(1)
 */
public class Solution {
    static int MoreThanHalfNum_Solution(int [] array) {
        if (array.length == 0) return 0;
        if (array.length == 1) return array[0];

        HashMap<Integer, Integer> map = new HashMap<>();
        int length = array.length;
        for (int i=0; i<length; i++){
            if (map.containsKey(array[i])){
                map.put(array[i], map.get(array[i]) + 1);
                if (map.get(array[i]) > length/2) return array[i];
            }else{
                map.put(array[i], 1);
            }
        }

        return 0;
    }
/*
解题思路：
采用阵地攻守的思想：
1. 第一个数字作为第一个士兵，守阵地；count = 1；遇到相同元素，count++;
2. 遇到不相同元素，即为敌人，同归于尽,count--；
3. 当遇到count为0的情况，又以新的i值作为守阵地的士兵，继续下去，到最后还留在阵地上的士兵，有可能是主元素。
4. 再加一次循环，记录这个士兵的个数看是否大于数组一半即可


首先第一个for循环结束后得到的num是什么？
1. 如果这个数组中存在个数大于数组长度一半的数，那么这个num一定是这个数，因为数组中所有不是num的数，一定会被这个数覆盖，

2. 但是，如果这个数组中根本不存在个数大于数组长度一半的数，那么这个num就是一个不确定的值
3. 这也是为什么找出num之后，还要再做一次循环验证这个数出现的个数是不是大于数组长度一半的原因。
 */
    //time: o(n) space: o(1)
    static int MoreThanHalfNum_Solution2(int [] array) {
        if (array.length == 0) return 0;

        int count = 0;
        int num = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                count++;
            } else {
                count--;
                if (count == 0) {
                    num = array[i];
                    count++;
                }
            }
        }
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == num) {
                count++;
            }
        }

        if (count > array.length / 2) return num;

        return 0;
    }
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,2,2,2,4,2,6};
        System.out.println(MoreThanHalfNum_Solution2(array));

    }
}
