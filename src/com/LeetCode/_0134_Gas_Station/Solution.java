package com.LeetCode._0134_Gas_Station;
/*
134. Gas Station
在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升
你有一辆油箱容量无限的的汽车，从第i个加油站开往第i+1个加油站需要消耗汽油cost[i]升
你从其中的一个加油站出发，开始时油箱为空
如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回-1

说明:
如果题目有解，该答案即为唯一答案。
输入数组均为非空数组，且长度相同。
输入数组中的元素均为非负数

示例 1:
输入:
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
输出: 3
解释:
从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
因此，3 可为起始索引。
 */

/*
思路1：根据规则，遍历判断当前油量是否大于等于到下一站所需的油量 time:o(n^2) space:o(1)
1. 由上面的例子可以看出
    加油站id   当前油量   到下一站所需油量
      3         4     >=     1
      4       4-1+5=8 >=     2
      0         7            3
      1         6            4
      2         5     >=     5
所以只需要从id=0开始进行遍历判断
需要特别处理的是，循环遍历数组的index如何计算
=> j = (i + count) % length
index =[0,1,2,3,4]
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]
eg: 从i=3开始遍历，
    遍历了1个数字，count=1 j=(3+1)%5=4 gas[4]=5
    遍历了2个数字，count=2 j=(3+2)%5=0 gas[0]=1

如果能遍历到count=length => 能遍历回到起点 => return i;

思路2： => 总加油 >= 总油耗就一定有解 time:o(n) space:o(1)
可以考虑
gas  = [1,2,3,4,5]
cost = [5,4,3,1,2] 无论cost怎么摆放，如果sum(gas) >= sum(cost) => 则一定存在解

1. 从起点0开始，累加每个站点的gas[i]-cost[i]，即cur(i)
2. 当站i累加完cur[i]后，如果小于0，则站0到站i都不是起点
3. 将i+1作为起点，重新累加每个站点的cur[i]
4. 当站j累加完cur[j]，如果小于0，则站i+1到站j都不是起点
5. 继续考察新起点,因为一定又解 => 不可能一直cur[i] < 0 下去
6. 因此必然有一段是[i,j]，以i为起点的这一段能加到足够的油，足以填补其他段欠缺的量
 */
public class Solution {
    static int canCompleteCircuit1(int[] gas, int[] cost) {
        int length = gas.length;
        for (int i = 0; i < length; i++){
            int currentGas = 0;
            int count = 0;
            while (count < length){
                int j = (i + count) % length;
                currentGas = currentGas + gas[j]; //上一站剩下的油量+当前站可以提供的油量
                if (currentGas >= cost[j]){ // 判断当前油量是否大于等于到下一站所需的油量
                    currentGas = currentGas - cost[j]; // 更新当前油量
                }else break;
                count++;
            }

            if (count == length) return i;
        }

        return -1;
    }

    static int canCompleteCircuit2(int[] gas, int[] cost) {
        int lenght = gas.length;
        int cur = 0, res = 0, ans = 0;

        for (int i = 0; i < lenght; i++){
            cur += gas[i] - cost[i];
            if (cur < 0){
                ans = i + 1;
                res += cur;
                cur = 0;
            }
        }

        return cur + res >= 0 ? ans : -1;
    }

    public static void main(String[] args) {
        int[] gas = new int[]{1,2,3,4,5};
        int[] cost = new int[]{3,4,5,1,2};
        System.out.println(canCompleteCircuit2(gas, cost));
    }
}
