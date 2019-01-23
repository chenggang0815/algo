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