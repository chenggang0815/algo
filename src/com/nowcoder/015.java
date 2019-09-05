// 单链表的复制
    public static node cloneList(node head){
        node temp_head = head;
        node clone_head = new node();
        node clone_node = new node();

        if (temp_head!=null){
            clone_head.data = temp_head.data;
            clone_head.next = null;
            clone_node = clone_head;
            temp_head = temp_head.next;
        }
        while (temp_head != null){
            node temp_node = new node(1);
            temp_node.data = temp_head.data;
            temp_node.next = null;
            clone_node.next = temp_node;
            clone_node = clone_node.next;
            temp_head = temp_head.next;
        }

        return clone_head;
    }
	
	
	public static void copyList(Node head){ 
		Node node = head; 
		while(node != null){ 
			Node copyNode = new Node(); 
			copyNode.value = node.value; 
			copyNode.next = node.next; 
			copyNode.sbiling = null; 
			node.next = copyNode; 
			node = copyNode.next; 
		}
 } 
    public static void setSbiling(Node head){ 
		Node node = head; 
		while(node != null){ 
			Node copyNode = node.next; 
			if(node.sbiling != null){ 
				copyNode.sbiling = node.sbiling.next; 
			} 
		node = copyNode.next; 
		} 
} 
	public static Node disConnectList(Node head){ 
		Node node = head; 
		Node copyHead = null;
		Node copyNode = null; 
		if(node != null){ 
			copyHead = node.next; 
			copyNode = node.next; 
			node.next = copyNode.next; 
			node = node.next; 
		} 
		while(node != null){ 
			copyNode.next = node.next; 
			copyNode = copyNode.next; 
			node.next = copyNode.next; 
			node = node.next; 
		} 
		return copyHead;
} 
	public static Node copy(Node head){ 
		copyList(head); 
		setSbiling(head); 
		return disConnectList(head);
	} 
