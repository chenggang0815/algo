package LeetCode._1834_Single_Threaded_CPU;

import java.util.Arrays;
import java.util.PriorityQueue;

/*
1834. Single-Threaded CPU

You are given n tasks labeled from 0 to n - 1 represented by a 2D integer array tasks, where tasks[i] = [enqueueTimei, processingTimei] means that the i​​​​​​th​​​​ task will be available to process at enqueueTimei and will take processingTimei to finish processing.

You have a single-threaded CPU that can process at most one task at a time and will act in the following way:

If the CPU is idle and there are no available tasks to process, the CPU remains idle.
If the CPU is idle and there are available tasks, the CPU will choose the one with the shortest processing time. If multiple tasks have the same shortest processing time, it will choose the task with the smallest index.
Once a task is started, the CPU will process the entire task without stopping.
The CPU can finish a task then start a new one instantly.
Return the order in which the CPU will process the tasks.

Example 1:
Input: tasks = [[1,2],[2,4],[3,2],[4,1]]
Output: [0,2,3,1]
*/
public class Solution {
    //[1,2],[2,4],[3,2],[4,1]
    //cur=1  i=0
    //cur += tasks[0][1] = 3 => i=2
    //cur += tasks[2][1] = 3 + 2 = 5
    //cur += tasks[3][2] = 5 + 1 = 6
    //cur += tasks[1][1]
    public int[] getOrder(int[][] tasks) {
        // [i, start time, process time]
        int[][] sortedTasks = new int[tasks.length][3];
        for(int i = 0; i < tasks.length; i++){
            sortedTasks[i][0] = i;
            sortedTasks[i][1] = tasks[i][0];
            sortedTasks[i][2] = tasks[i][1];
        }

        Arrays.sort(sortedTasks, (a, b) -> (a[1] - b[1]));
        int[] res = new int[tasks.length];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] == b[2] ? a[0] - b[0] : a[2] - b[2]);

        int curTime = 0;
        int resIndex = 0;
        int taskIndex = 0;
        while(resIndex < tasks.length){
            while(taskIndex < tasks.length && sortedTasks[taskIndex][1] <= curTime){
                pq.offer(sortedTasks[taskIndex++]);
            }

            if(pq.isEmpty()) {
                curTime = sortedTasks[taskIndex][1];
                continue;
            }
            int[] bestFit = pq.poll();
            res[resIndex++] = bestFit[0];
            curTime += bestFit[2];
        }

        return res;

    }
    public static void main(String[] args) {

    }
}
