package LeetCode._0082_Remove_Duplicates_from_Sorted_List_II;
/*

1->2->3->3->4 逻辑没有问题，使用pre和cur节点
            if(head.next != null && head.val == head.next.val){
                while(head.next != null && head.val == head.next.val){
                    head = head.next;
                }
                pre.next = head.next;

1->1->2->3->4时，考虑单独处理head节点是错误的方向，应该用dummy node，然后使用上面的逻辑一起处理
*/
public class Solution {
     public class ListNode {
     int val;
     ListNode next;
     ListNode() {}
         ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;

        while(head != null){
            if(head.next != null && head.val == head.next.val){
                while(head.next != null && head.val == head.next.val){
                    head = head.next;
                }
                pre.next = head.next;
            }else{
                pre = pre.next;
            }

            head = head.next;

        }

        return dummyNode.next;
    }
    public static void main(String[] args) {

    }
}
