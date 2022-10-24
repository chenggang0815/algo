package LeetCode._0022_Generate_Parentheses;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
/*
22. Generate Parentheses
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
Example 1:
Input: n = 3
Output: ["((()))","(()())","(())()","()(())","()()()"]
Example 2:
Input: n = 1
Output: ["()"]
*/

/*
Solution
Approach 1：backtracking
    由于字符串的特殊性，产生一次拼接都生成新的对象，因此无需回溯
                        => 如果使用StringBuilder呢？ => 回溯
    2.1 做减法 left和right分别表示左右括号剩余的数量
            "" n=2 left=right=2
           /  \
          (    ) left=2 right=1 => left > right => 剪枝
         /  \  x
       ((   ()
       /   /   \
     (()  ()(  ()) left=1 right=0 => left > right => 剪枝
     /    /     x
    (()) ()()

    1. 当左括号剩余数量大于右括号剩余数量时，已经不能生成有效的括号，直接剪枝 => if(left > right) return;
    2. 当左右括号剩余数量大于0时，可以产生分支
    3. 当左右括号剩余数量都等于0时，递归停止，把字符加入res

Approach 2：backtracking
n = 3 left=3 right=3
we have
    => ( ( ( ) ) ) left=3 right=3
    => ( ) ( ) ( )
    => ( ( ) ) ( )
    => ( left=1 right=0
    => ( ( left=2 right=0
    => ( ( ) left=2 right=1
    => ) left=0 right=1 => left < right => return;
    => (((( left=4 > n => return
    => left=3 right=3 => res.add(s) => return;

Approach 3：bfs => 创建结点对象，使用队列完成广度优先遍历
    4.1 因为queue中的元素，需要同时带着当前的字符以及左右括号剩余数量这三个状态，所以需要自己编写一个结点类。
    4.2 类的每个实例带着这三个元素的状态
以n=2为，模拟bfs的过程：
    初始化q=[""]
    while(queue.isEmpty())
        1 ""出队列 => q=[["(",l=1,r=2]]
        2 ["(",l=1,r=2]出队列 => q=[["((",l=0,r=2], ["()",l=1,r=1]]
        3 ["((",l=0,r=2]出队列 => q=[["()",l=1,r=1], ["(()",l=0,r=1]]
        4 ["()",l=1,r=1]出队列 => q=[["(()",l=0,r=1], ["()(",l=0,r=1]]
        5 ["(()",l=0,r=1] => q=[["()(",l=0,r=1], ["(())",l=0,r=0]]
        6 ["()(",l=0,r=1] => q=[["(())",l=0,r=0], ["()()",l=0,r=0]]
        7 queue.poll()
        8 queue.poll()
        9 return

Approach 4: backtracking + StringBuilder
1. 使用sb需要回溯，因为搜索过程中使用一份状态变量sb去搜索所有可能的状态
2. 在递归终止的时候需要拷贝变量
 */
public class Solution {
    // approach 1
    static List<String> generateParenthesis1(int n){
        List<String> res = new ArrayList<>();
        backTracking1(res,"", n, n);

        return res;
    }

    static void backTracking1(List<String> res, String s, int left, int right){
        if (left == 0 && right == 0){
            res.add(s);
            return;
        }

        if (left > right) return;

        if (left > 0) backTracking1(res,s + "(", left - 1, right);
        if (right > 0) backTracking1(res,s + ")", left, right - 1);
    }

    // approach 2
    static List<String> generateParenthesis2(int n){
        List<String> res = new ArrayList<>();
        backTracking2(res, n, "", 0, 0);

        return res;
    }

    static void backTracking2(List<String> res, int n, String s, int left, int right){
        if (left == n && right == n){
            res.add(s);
            return;
        }

        if (left > n || left < right) return; // (((( left = 4 > 3 => should return

        backTracking2(res, n, s + "(", left + 1, right);
        backTracking2(res, n, s + ")", left, right + 1);
    }

    //approach 3
    static class Node{
        private String res;
        private int left; // 剩余左括号数量
        private int right; // 剩余右括号数量
        public Node(String str, int left, int right){
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    static List<String> generateParenthesis3(int n){
        List<String> res = new ArrayList<>();
        if (n == 0) return res;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));
        while (!queue.isEmpty()){
            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) res.add(curNode.res);
            if (curNode.left > 0) queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            if (curNode.right >0 && curNode.left < curNode.right) queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
        }

        return res;
    }

    //approach 4
    static List<String> generateParenthesis4(int n){
        List<String> res = new ArrayList<>();
        if (n == 0) return res; // 边界条件
        StringBuilder sb = new StringBuilder();
        dfs3(res, sb, n, n);
        return res;
    }

    static void dfs3(List<String> res, StringBuilder sb, int left, int right){
        if (left == 0 && right == 0){
            //path.toString() 生成了一个新的字符串，相当于做了一次拷贝，这里的做法等同于「力扣」第 46 题、第 39 题
            res.add(sb.toString());
        }

        if (left > right) return;
        if (left > 0){
            dfs3(res, sb.append("("), left - 1, right);
            sb.deleteCharAt(sb.length() - 1); //sb对象需要回溯
        }

        if (right > 0){
            dfs3(res, sb.append(")"), left, right - 1);
            sb.deleteCharAt(sb.length() - 1);//sb对象需要回溯
        }
    }

    public static void main(String[] args){
        System.out.println(generateParenthesis4(2));
    }
}
