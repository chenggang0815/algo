package LeetCode._0134_Gas_Station;
/*
134. Gas Station
There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
You begin the journey with an empty tank at one of the gas stations.
Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.
If there exists a solution, it is guaranteed to be unique

Example 1:
Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.

Example 2:
Input: gas = [2,3,4], cost = [3,4,3]
Output: -1
Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.

Constraints:
1. gas.length == n
2. cost.length == n
3. 1 <= n <= 105
4. 0 <= gas[i], cost[i] <= 104
*/

/*
Solution
Approach time:o(n) space:o(1)
1. if total gas < total cost, can't find a start station to perform a trip.
   else, must exists a start station can perform a trip.
2. iterate the from start station is 0
3. (A,B,C,D,E) => if we can reach C from A, but we can't reach D from A. which means at C, curCost - curGas > 0 => we can't reach D from A
    3.1 and we also can know, we can reach B,C from A
     at A curCost - curGas < 0
     at B curCost - curGas < 0
     as C curCost + cost > curGas + gas => so we can't reach D from B and C

for example:
      A   B   C   D
gas = 4   3   1   3
cost= 2   2   5   2
     g=2 g=3 g=-1
         g=1
1. total gas = 11 total cost = 11, can find a station
2. start A to C curGas=8 curCost=9 => can't reach D
3. start B to C curGas=4 curCost=7 => can't reach D
4. start C  curGas=1 curCost=5 => can't reach D
5. we can try start from D

 */
public class Solution {

    static int canCompleteCircuit1(int[] gas, int[] cost) {
        int totalCost = 0;
        int totalGas = 0;
        for (int i = 0; i < gas.length; i++){
            totalCost += cost[i];
            totalGas += gas[i];
        }
        if (totalCost > totalGas) return -1;

        int startIndex = 0;
        int curGas = 0;
        int curCost = 0;
        for (int i = 0; i < gas.length; i++){
            curCost += cost[i];
            curGas += gas[i];
            if (curCost > curGas){
                startIndex = i + 1;
                curCost = 0;
                curGas = 0;
            }
        }

        return startIndex;
    }

    static int canCompleteCircuit2(int[] gas, int[] cost) {
        int totalCost = 0;
        int totalGas = 0;
        int startIndex = 0;
        int curGas = 0;
        int curCost = 0;
        for (int i = 0; i < gas.length; i++){
            curCost += cost[i];
            curGas += gas[i];
            totalCost += cost[i];
            totalGas += gas[i];
            if (curCost > curGas){
                startIndex = i + 1;
                curCost = 0;
                curGas = 0;
            }
        }

        return totalCost > totalGas ? -1 : startIndex;
    }

    public int canCompleteCircuit3(int[] gas, int[] cost) {
        int startIndex = 0;
        int totalTank = 0;
        int curTank = 0;
        for (int i = 0; i < gas.length; i++){
            totalTank += gas[i] - cost[i];
            curTank += gas[i] - cost[i];
            if (curTank < 0){
                startIndex = i + 1;
                curTank = 0;
            }
        }

        return totalTank < 0 ? -1 : startIndex;
    }

    public static void main(String[] args) {
        int[] gas = new int[]{1,2,3,4,5};
        int[] cost = new int[]{3,4,5,1,2};
        System.out.println(canCompleteCircuit1(gas, cost));
    }
}
