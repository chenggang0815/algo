package LeetCode._0234_Palindrome_Linked_List;
import java.util.ArrayList;
/*
234. Palindrome Linked List
Easy
Given a singly linked list, determine if it is a palindrome.
Example 1:
Input: 1->2
Output: false

Example 2:
Input: 1->2->2->1
Output: true
 */
//leetcode中的链表的头结点都是带有真实值的
public class Solution {
    static class ListNode{
        int val;
        ListNode next;
        ListNode(int val){
            this.val = val;
        }
    }
    static ListNode head = new ListNode(1);

    static boolean isPalindrome(ListNode head) {
        if (head==null||head.next==null) return true;
        ArrayList<ListNode> arr = new ArrayList<>();
        ListNode temp = head;
        while (temp!=null){
           // temp = temp.next;
            arr.add(temp);
            temp = temp.next;
        }
        int length = arr.size();
        System.out.println(arr.size());

        for (int i=0;i<length/2;i++){
            if (arr.get(i).val == arr.get(length-i-1).val){
                continue;
            }else return false;
        }

        return true;
    }

    static void addNode(int data){
        ListNode node = new ListNode(data);
        ListNode temp = head;
        while (temp.next!=null){
            temp=temp.next;
        }

        temp.next=node;
    }
    public static void main(String[] args) {

//        addNode(1);
        addNode(2);
//        addNode(3);
//        addNode(2);
//        addNode(1);
        System.out.println(isPalindrome(head));
    }
}
