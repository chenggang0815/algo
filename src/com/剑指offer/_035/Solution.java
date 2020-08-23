package com.剑指offer._035;

import java.util.HashMap;

/*
一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class Solution {
    /*
    map记录每个数字出现的次数
     */
    //time: o(n) space: o(n)
    static void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<array.length; i++){
            if (map.containsKey(array[i])){
                map.put(array[i], map.get(array[i]) + 1);
            }else {
                map.put(array[i], 1);
            }
        }
        int index = 0;
        int[] nums = new int[2];
        for (int j=0; j<array.length; j++){
            if (map.get(array[j]) == 1){
                nums[index] = array[j];
                index++;
            }
        }

        num1[0] = nums[0];
        num2[0] = nums[1];
        System.out.println(num1[0]);
        System.out.println(num2[0]);
    }

    /*
    异或运算的性质：
    1. a^b = b^a
    2. a^b^c = a^(b^c) = (a^b)^c
    3. a^a = 0
    4. a^0 = a
    */
    /*
    1. 第一步：根据位运算将所有所有数字相异或，则最后的结果肯定是那两个只出现一次的数字异或的结果
    2. 此例中 2^4^3^6^3^2^5^5 => (2^2)^(3^3)^(5^5)^6^4 => 0^0^0^6^4 => 6^4 => (110)^(100) => (010) = 2
    3. 第二步：根据异或的结果1所在的最低位，把数字分成两半，每一半里都还有只出现一次的数据和成对出现的数据
    4. 第三步：继续对每一半相异或则可以分别求出两个只出现一次的数字
     */
    /*
为什么第二步找出的这个数（010）可以将数组分成两部分？
1. 因为此数是根据两个唯一的数i，j异或得来的，i，j肯定不会同时有 j&index==0 和 i&index==0
2. 至于数组中的其他数，无论他们是否 &index==0，都能保证相同的两个数被分到同一边，不影响我们的结果。
对于此例来说，4 和 6 分别 &index 一个是==0 一个是！=0 ，其他数结果不重要：
2: 010 & 010 = 010 !=0
4:  100 & 010 = 000 ==0
3:  011 & 010 = 010 !=0
6:  110 & 010 = 010 !=0
5:  101 & 010 = 000 ==0
r1 = 0^4^5^5 = 5
r2 = 0^2^3^6^3^2 = 6
 */
    // time:o(n) space:o(1)
    static void FindNumsAppearOnce2(int [] array,int num1[] , int num2[]) {
        int bitResult  = 0;
        for(int i=0; i<array.length; i++){
            bitResult ^= array[i];  //找到第一个不同的位对数据进行分类，分类为两个队列分别对数据进行异或求
        }

        int minIndex = 1;
        while((minIndex & bitResult) == 0){
            minIndex = minIndex << 1; //从最低位的1开始，每次左移一位，直到找到bitResult中的最低位1所在的位
        }

        int res1 = 0;
        int res2 = 0;
        for(int i=0; i<array.length; i++){
            if((minIndex & array[i]) == 0){
                res1 ^= array[i];
            }else{
                res2 ^= array[i];
            }
        }

        //System.out.println(res1);
        //System.out.println(res2);
        num1[0] = res1;
        num2[0] = res2;
    }
        public static void main(String[] args) {
        int[] array = new int[]{2,4,3,6,3,2,5,5};
        int[] num1 = new int[1];
        int[] num2 = new int[1];
        FindNumsAppearOnce2(array, num1, num2);
    }
}
