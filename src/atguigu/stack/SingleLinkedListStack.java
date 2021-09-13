package atguigu.stack;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 实现栈（stack）的链式存储结构
 * @date 2021/2/19 - 18:58
 */
public class SingleLinkedListStack {

    public static void main(String[] args) {
        // test
        StackNode s5 = new StackNode(5);
        StackNode s4 = new StackNode(4);
        StackNode s3 = new StackNode(3);
        StackNode s2 = new StackNode(2);
        StackNode s1 = new StackNode(1);

        LinkedListStack linkedListStack = new LinkedListStack();
        linkedListStack.Push(s1);
        linkedListStack.Push(s2);
        linkedListStack.Push(s3);
        linkedListStack.Push(s4);
        linkedListStack.Push(s5);

        StackNode temp = null;
        System.out.println("栈的长度" + linkedListStack.size());
        do{
            temp = linkedListStack.Pop();
            System.out.println("当前出栈：" + temp);
        }while(temp!=null);

        System.out.println("栈的长度" + linkedListStack.size());

    }
}


class LinkedListStack {
    private StackNode top;
    private int count;

    public LinkedListStack() {
    }

    public void Push(StackNode stackNode) {
        // 新节点插入在表头
        stackNode.setNext(top);
        top = stackNode;
        count++;
    }

    public StackNode Pop() {
        if(top==null) {
//            throw new RuntimeException("栈顶为空");
            return null;
        }
        StackNode temp = top;
        top = top.getNext();
        count--;
        return temp;
    }

    public int size() {
        return count;
    }
}

class StackNode {

    /**
     * elem：栈的节点保存的数据
     */
    private int elem;
    /**
     * next：指向下一个节点的指针
     */
    private StackNode next;

    // 单参构造
    public StackNode(int elem) {
        this.elem = elem;
    }

    //getter、setter方法
    public int getElem() {
        return elem;
    }

    public void setElem(int elem) {
        this.elem = elem;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "StackNode{" +
                "elem=" + elem +
                '}';
    }
}