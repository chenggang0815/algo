package com.LeetCode._0148_Sort_List;
/*
148. 排序链表
在O(nlogn)时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:
输入: 4->2->1->3
输出: 1->2->3->4

示例 2:
输入: -1->5->3->4->0
输出: -1->0->3->4->5
 */

/*
思路1：冒泡排序 time:o(n^2) space:o(1)

思路2：归并排序 - 递归 time:o(nlog(n)) space:o(log(n))
对链表自顶向下归并排序的过程如下:
2.1 找到链表的中点，以中点为分界，将链表拆分成两个子链表。寻找链表的中点可以使用快慢指针的做法，快指针每次移动2步，慢指针每次移动1步，当快指针到达链表末尾时，慢指针指向的链表节点即为链表的中点。
2.2 对两个子链表分别递归调用排序
2.3 将两个排序后的子链表合并，得到完整的排序后的链表。可以使用「21. 合并两个有序链表」的做法，将两个有序的子链表进行合并。
上述过程可以通过递归实现。递归的终止条件是链表的节点个数小于或等于1，即当链表为空或者链表只包含1个节点时，不需要对链表进行拆分和排序

思路3：归并排序 - 迭代（非递归）time:o(nlog(n)) space:o(1)
考虑到递归调用的栈空间，自顶向下归并排序的空间复杂度是O(logn)。如果要达到O(1)的空间复杂度，则需要使用自底向上的实现方式
具体做法如下
3.1 用subLength表示每次需要排序的子链表的长度，初始时subLength=1
3.2 每次将链表拆分成若干个长度为subLength的子链表（最后一个子链表的长度可以小于subLength），按照每两个子链表一组进行合并，合并后即可得到若干个长度为subLength×2 的有序子链表（最后一个子链表的长度可以小于subLength×2）。合并两个子链表仍然使用「21. 合并两个有序链表」的做法。
3.3 将subLength 的值加倍，重复第2步，对更长的有序子链表进行合并操作，直到有序子链表的长度大于或等于length，整个链表排序完毕

https://leetcode-cn.com/problems/sort-list/solution/148-pai-xu-lian-biao-bottom-to-up-o1-kong-jian-by-/

举个简单的例子：[4,3,1,7,8,9,2,11,5,6]
step=1: (3->4)->(1->7)->(8->9)->(2->11)->(5->6)
step=2: (1->3->4->7)->(2->8->9->11)->(5->6)
step=4: (1->2->3->4->7->8->9->11)->5->6
step=8: (1->2->3->4->5->6->7->8->9->11)
 */
public class Solution {
    static class ListNode{
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
        }
    }

    // Time Limit Exceeded 冒泡排序 time:o(n^2) space:o(1)
    static ListNode listSort1(ListNode head){
        if (head == null || head.next == null) return head;

        ListNode left = head;
        ListNode right = null;

        while(left.next != right){
            while(left.next != right){
                if(left.val > left.next.val){
                    int temp = left.val;
                    left.val = left.next.val;
                    left.next.val = temp;
                }
                left = left.next;
            }

            right = left; // 此时，left以及右边的结点都是有序的 => 下次遍历的右边界为left
            left = head; // 下次遍历的右边界总为head
            /*  冒泡排序
                for(int i = 0; i < length-1; i++){
                    for(int j = 0; j < length - i - 1; j++){
                    }
                }
             */
        }

        return head;
    }

    // 归并排序 - 递归 time:o(nlog(n)) space:o(log(n))
    static ListNode listSort2(ListNode head) {
        return listSort2(head, null);
    }

    static ListNode listSort2(ListNode head, ListNode tail) {
        if (head == null){
            return head;
        }
        if (head.next == tail){
            head.next = null;
            return head;
        }

        ListNode slow = head, fast = head;
        while (fast != tail){
            slow = slow.next;
            fast = fast.next;
            if (fast != tail){
                fast = fast.next;
            }
        }

        ListNode mid = slow;
        ListNode list1 = listSort2(head, mid);
        ListNode list2 = listSort2(mid, tail);
        ListNode sorted = merge(list1, list2);

        return sorted;
    }

    static ListNode merge(ListNode head1, ListNode head2){
        ListNode newHead = new ListNode(0);
        ListNode temp = newHead, temp1 = head1, temp2 = head2;
        while (temp1 != null && temp2 != null){
            if (temp1.val < temp2.val){
                temp.next = temp1;
                temp1 = temp1.next;
            }else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }

        if ( temp1 != null) temp.next = temp1;
        if ( temp2 != null) temp.next = temp2;

        return newHead.next;
    }

    //归并排序 - 迭代（非递归）time:o(nlog(n)) space:o(1)
    public ListNode sortList(ListNode head) {
        int length = 0;
        ListNode node = head;
        while(node != null){
            length++;
            node = node.next;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        /*
        举个简单的例子：[4,3,1,7,8,9,2,11,5,6]
        step=1: (3->4)->(1->7)->(8->9)->(2->11)->(5->6)
        step=2: (1->3->4->7)->(2->8->9->11)->(5->6)
        step=4: (1->2->3->4->7->8->9->11)->5->6
        step=8: (1->2->3->4->5->6->7->8->9->11)
         */
        for(int step = 1; step < length; step *= 2){ //依次将链表分成1块，2块，4块...
            //每次变换步长，pre指针和cur指针都初始化在链表头
            ListNode pre = dummy;
            ListNode cur = dummy.next;
            while(cur != null){
                ListNode h1 = cur; //第一部分头 （第二次循环之后，cur为剩余部分头，不断往后把链表按照步长step分成一块一块...）
                ListNode h2 = split(h1,  step);  //第二部分头
                cur = split(h2, step); //剩余部分的头
                ListNode temp = merge(h1, h2); //将一二部分排序合并
                pre.next = temp; //将前面的部分与排序好的部分连接
                while(pre.next != null){
                    pre = pre.next; //把pre指针移动到排序好的部分的末尾
                }
            }
        }

        return dummy.next;
    }

    static ListNode split(ListNode head,int step){
        //断链操作 返回第二部分链表头
        if(head == null)  return null;
        ListNode cur = head;
        for(int i = 1; i < step && cur.next != null; i++){
            cur = cur.next;
        }
        ListNode right = cur.next;
        cur.next = null; //切断连接
        return right;
    }


    static void printList(ListNode head){
        ListNode cur = head;
        while(cur != null){
            System.out.println(cur.val);
            cur = cur.next;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        head.next = node1;
        node1.next = node2;
        node2.next = node3;
        //printList(listSort2(head));
        int a = 2;
        System.out.println(a <<= 1);
    }
}
