package 剑指offer._995_单链表的复制;
// 单链表的复制
public  class Solution {
//
//	public static Node cloneList(Node head) {
//		Node temp_head = head;
//		Node clone_head = new Node();
//		Node clone_node = new Node();
//
//		if (temp_head != null) {
//			clone_head.data = temp_head.data;
//			clone_head.next = null;
//			clone_node = clone_head;
//			temp_head = temp_head.next;
//		}
//		while (temp_head != null) {
//			Node temp_node = new Node(1);
//			temp_node.data = temp_head.data;
//			temp_node.next = null;
//			clone_node.next = temp_node;
//			clone_node = clone_node.next;
//			temp_head = temp_head.next;
//		}
//
//		return clone_head;
//	}
//
//
//	public static void copyList(Node head) {
//		Node node = head;
//		while (node != null) {
//			Node copyNode = new Node();
//			copyNode.value = node.value;
//			copyNode.next = node.next;
//			copyNode.sbiling = null;
//			node.next = copyNode;
//			node = copyNode.next;
//		}
//	}
//
//	public static void setSbiling(Node head) {
//		Node node = head;
//		while (node != null) {
//			Node copyNode = node.next;
//			if (node.sbiling != null) {
//				copyNode.sbiling = node.sbiling.next;
//			}
//			node = copyNode.next;
//		}
//	}
//
//	public static Node disConnectList(Node head) {
//		Node node = head;
//		Node copyHead = null;
//		Node copyNode = null;
//		if (node != null) {
//			copyHead = node.next;
//			copyNode = node.next;
//			node.next = copyNode.next;
//			node = node.next;
//		}
//		while (node != null) {
//			copyNode.next = node.next;
//			copyNode = copyNode.next;
//			node.next = copyNode.next;
//			node = node.next;
//		}
//		return copyHead;
//	}
//
//	public static Node copy(Node head) {
//		copyList(head);
//		setSbiling(head);
//		return disConnectList(head);
//	}
}
