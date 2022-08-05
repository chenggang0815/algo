package Facebook._0636_Exclusive_Time_of_Functions;

import java.util.List;
import java.util.Stack;

/*
636. Exclusive Time of Functions

On a single-threaded CPU, we execute a program containing n functions. Each function has a unique ID between 0 and n-1.

Function calls are stored in a call stack: when a function call starts, its ID is pushed onto the stack, and when a function call ends, its ID is popped off the stack. The function whose ID is at the top of the stack is the current function being executed. Each time a function starts or ends, we write a log with the ID, whether it started or ended, and the timestamp.

You are given a list logs, where logs[i] represents the ith log message formatted as a string "{function_id}:{"start" | "end"}:{timestamp}". For example, "0:start:3" means a function call with function ID 0 started at the beginning of timestamp 3, and "1:end:2" means a function call with function ID 1 ended at the end of timestamp 2. Note that a function can be called multiple times, possibly recursively.

A function's exclusive time is the sum of execution times for all function calls in the program. For example, if a function is called twice, one call executing for 2 time units and another call executing for 1 time unit, the exclusive time is 2 + 1 = 3.

Return the exclusive time of each function in an array, where the value at the ith index represents the exclusive time for the function with ID i.

Example 1:
Input: n = 2, logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
Output: [3,4]
Explanation:
Function 0 starts at the beginning of time 0, then it executes 2 for units of time and reaches the end of time 1.
Function 1 starts at the beginning of time 2, executes for 4 units of time, and ends at the end of time 5.
Function 0 resumes execution at the beginning of time 6 and executes for 1 unit of time.
So function 0 spends 2 + 1 = 3 units of total time executing, and function 1 spends 4 units of total time executing.

Example 2:
Input: n = 1, logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
Output: [8]
Explanation:
Function 0 starts at the beginning of time 0, executes for 2 units of time, and recursively calls itself.
Function 0 (recursive call) starts at the beginning of time 2 and executes for 4 units of time.
Function 0 (initial call) resumes execution then immediately calls itself again.
Function 0 (2nd recursive call) starts at the beginning of time 6 and executes for 1 unit of time.
Function 0 (initial call) resumes execution at the beginning of time 7 and executes for 1 unit of time.
So function 0 spends 2 + 4 + 1 + 1 = 8 units of total time executing.
*/
/*
Solution
Approach 1: stack
1. for example: logs = ["0:start:0","1:start:2","1:end:5","0:end:6"]
2. define a logProcess class to process each log string, get the isStart, fId, time
3. iterate the log array, if current log is start, just push the log in the stack
4. if the current log is end, pop the peek log of the stack, res[curLog.fId] += (curLog.time - preLog.time + 1)
    4.1 if the stack is not empty => res[stack.peek().fId] -= (curLog.time - preLog.time + 1)
    4.2 which means subtract the time from "1:start:2" to "1:end:5"
*/
public class Solution {
    // logs = ["0:start:0","0:start:2","0:end:5","0:start:6","0:end:6","0:end:7"]
    // start => stack.push() =>  [0, 1]
    // end => stack.poll() => [0]
    //      1: (5 - 2 + 1) = 4
    //      !stack.isEmpty() => stack.peek() => 0: - (5 - 2 + 1) = -4
    // end => stack.poll() => []
    //      0: (-4 + 6 - 0 + 1) = 3

    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<logProcess> stack = new Stack<>();
        for(String log : logs){
            logProcess curLog = new logProcess(log);
            //System.out.print(l.isStart + "==" + l.fId + "==" + l.time + "\n");
            if(curLog.isStart){
                stack.push(curLog);
            }else{
                logProcess preLog = stack.pop();
                res[curLog.fId] += (curLog.time - preLog.time + 1);
                if(!stack.isEmpty()){
                    res[stack.peek().fId] -= (curLog.time - preLog.time + 1);
                }
            }
        }

        return res;
    }

    class logProcess{
        boolean isStart;
        int fId;
        int time;
        //"1:start:2"
        public logProcess(String log){
            String[] s = log.split(":");
            this.fId = Integer.parseInt(s[0]);
            this.isStart = s[1].equals("start");
            this.time = Integer.parseInt(s[2]);
        }
    }

    public static void main(String[] args) {

    }
}
