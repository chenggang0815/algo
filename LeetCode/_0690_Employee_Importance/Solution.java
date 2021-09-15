package LeetCode._0690_Employee_Importance;
/*
690. 员工的重要性

给定一个保存员工信息的数据结构，它包含了员工唯一的id，重要度和直系下属的id。

比如，员工1是员工2的领导，员工2是员工3的领导。他们相应的重要度为15, 10, 5。
那么员工1的数据结构是[1, 15, [2]]，员工2的数据结构是[2, 10, [3]]，员工3的数据结构是[3, 5, []]。
注意虽然员工3也是员工1的一个下属，但是由于并不是直系下属，因此没有体现在员工1的数据结构中。

现在输入一个公司的所有员工信息，以及单个员工id，返回这个员工和他所有下属的重要度之和。

输入: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
输出: 11
解释:
员工1自身的重要度是5，他有两个直系下属2和3，而且2和3的重要度均为3。因此员工1的总重要度是 5 + 3 + 3 = 11。

注意:

    一个员工最多有一个直系领导，但是可以有多个直系下属
    员工数量不超过2000。
 */


import java.util.*;

/*
思路1： dfs深度优先遍历  time:o(N) space:o(N) N=员工个数
[[1,5,[2,3]],[2,3,[4]],[3,4,[]],[4,1,[]]]
1
画出递归搜索树
         1(5)
       /  \
    2(3)   3(4)
            \
            4(1)

    1.1 由上图可以看出，只需要不停遍历搜索树，并且把当前遍历到的结点的重要性累加到res中
    1.2 递归终止条件：当前结点没有子结点了
    1.3 不需要把1.1的条件想复杂了，否则会多加或者少加结点

思路2： bfs广度优先遍历 利用queue
2.1 因为queue中的元素，需要同时带着当前的重要性，以及它的下属员工信息，所以需要一个节点类带着这些信息，不过题目中已经定义好结点类了，可以直接使用。
2.2 把头个员工的节点放入queue中，按照dfs的思路，累加当前的重要性到res中
    2.2.1 如果当前员工存在下属，把它的下属员工节点加入queue中。
2.3 遍历queue直到queue为空，return res
*/
public class Solution {
    static class Employee{
        int id;
        int importance;
        List<Integer> subordinates;
        Employee(int id, int importance, List<Integer> subordinates){
            this.id = id;
            this.importance = importance;
            this.subordinates = subordinates;
        }
    }

    /*//思路一 dfs
    static HashMap<Integer, Employee> map;
    static int getImportance(List<Employee> Employees, int targetId){
        map = new HashMap<>();
//        for (Employee e: Employees){
//            map.put(e.id, e);
//        }
        for(int i = 0; i < Employees.size(); i++){
            map.put(Employees.get(i).id, Employees.get(i));
        }

        return dfs(targetId);
    }

    static int importance;
    static int dfs(int targetId){
        importance = map.get(targetId).importance;
        List<Integer> subId = map.get(targetId).subId;
        if (subId.size() == 0) return importance;

        for (int i = 0; i < subId.size(); i++){
            importance += dfs(subId.get(i));
        }

        return importance;
    }
*/
    // 思路1 dfs + 使用全局变量res
    static int res = 0;
    static int getImportance2(List<Employee> employees, int id){
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee employee: employees){
            map.put(employee.id, employee);
        }

        //  if(map.get(id).subordinates.size() == 0) return map.get(id).importance;

        dfs(map, id);

        return res;
    }

    static void dfs(HashMap<Integer, Employee> map, int id){
        Employee employee = map.get(id);
        //  if(employee.subordinates.size() == 0){
        //       res += employee.importance;
        //       return;
        //   }
        res += map.get(id).importance;
        List<Integer> sub = employee.subordinates;
        for(int index: sub){
            // res += map.get(id).importance;
            dfs(map, index);
        }
    }

        // 思路2 bfs
    static int getImportance3(List<Employee> employees, int id){
        HashMap<Integer, Employee> map = new HashMap<>();
        for(Employee employee: employees){
            map.put(employee.id, employee);
        }

        Queue<Employee> queue = new LinkedList<>();
        Employee employee = map.get(id);
        queue.offer(employee);
        int res = 0;
        while (!queue.isEmpty()){
            Employee node = queue.poll();
            res += node.importance;
            if (node.subordinates.size() != 0){
                for(int i: node.subordinates){
                    queue.offer(map.get(i));
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        List<Integer> sub1 = new ArrayList<>();
        sub1.add(2);
        sub1.add(3);
        Employee e1 = new Employee(1, 5, sub1);
        List<Integer> sub2 = new ArrayList<>();
        sub2.add(4);
        List<Integer> sub3 = new ArrayList<>();
        Employee e2 = new Employee(2, 8, sub2);
        Employee e3 = new Employee(3, 7, sub3);
        List<Integer> sub4 = new ArrayList<>();
        Employee e4 = new Employee(4, 10,sub4);

        List<Employee> Employees = new ArrayList<>();
        Employees.add(e1);
        Employees.add(e2);
        Employees.add(e3);
        Employees.add(e4);
        System.out.println(getImportance3(Employees, 1));

    }
}
