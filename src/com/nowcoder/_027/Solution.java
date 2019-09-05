public class binaryTree {
    //前驱节点：二叉树中序遍历完成后和这个节点相邻的前面的节点为该节点的前驱节点
    //
    //后继节点：二叉树中序遍历完成后和这个节点相邻的后面的节点为该节点的后继节点
    //
    //比普通的二叉树结构多了一个指向该节点父亲节点的指针，我们规定：头节点指向其父亲节点的指针为空或者是头节点本身
    class node{
        node right;
        node left;
        node parent;
        int data;
    }
    private static node root; //定义根节点

    //查找当前节点的前驱节点
    /*两种情况：
    1.如果当前节点的左子树不为空，那么该点的前驱节点为该点左子树中最右的节点
    2.如果当前节点的左子树为空，那么该点的前驱节点为从该点往上延伸，
    a. 如果延伸到的点为其父亲节点的右孩子，那么这个父亲节点就是该点的前驱节点
    b. 如果延伸到当前节点没有父亲节点时，那么parent为空，这个条件不满足，函数返回空
     */
    public static node getPreNode(node searchNode ){
        if(searchNode == null){
            return searchNode;
        }
        if (searchNode.right!=null){//如果当前节点左子树不为空，那么该节点的后继节点为左子树中最右的节点
            return getrightmost(searchNode.right); //找到左子数树中最右的节点，并返回
        }
        else { //如果程序到达这里，说明当前节点的左子数为空
            node parent = searchNode.parent;
            while (parent!=null && searchNode!=parent.right){ //第一个条件就是判断那种特殊情况的，一直延伸到当前节点没有父亲节点时，那么parent为空，这个条件不满足，函数返回空
                searchNode = parent;                          //第二个条件是判断当前节点是否为该父亲节点的右孩子，如果不是，继续执行下面的代码，如果是，说明这个父亲节点就是heap节点的前驱节点
                parent = searchNode.parent;
            }

            return parent;
        }

    }

    public static node getrightmost(node current){ //这个函数是求左子树中最右的节点的函数
        while (current!=null){
            current = current.right;
        }
        return current;
    }


    public static void main(String[] args) {
    }

}




