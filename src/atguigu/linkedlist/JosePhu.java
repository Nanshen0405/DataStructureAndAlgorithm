package atguigu.linkedlist;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/2/4 - 18:55
 */
public class JosePhu {
    // 测试
    public static void main(String[] args) {

        /**
         * 约瑟夫环问题解析：
         * n = 5, 即有5个人
         * k = 1，从第一个人开始报数
         * m = 2，数两下
         */

        int nums = 25;
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();

        circleSingleLinkedList.add(nums);
        circleSingleLinkedList.list();


        circleSingleLinkedList.count(1,2,nums);

    }
}

// 创建环形单向链表
class CircleSingleLinkedList {

    private Boy head;

    public CircleSingleLinkedList() {

    }

    // 添加小孩数量，构建一个环形链表
    public void add(int nums) {
        //对nums做数据校验
        if(nums < 1) {
            System.out.println("nums的值不正确");
            return;
        }

        // 辅助变量，帮助创建循环链表
        Boy temp = null;
        // 使用循环创建环形链表
        for (int i = 0; i < nums; i++) {
            Boy boy = new Boy(i+1);
            // 创建第一个节点
            if(i==0){
                head = boy;
                head.setNext(head);
                temp = head;
            } else {
                /**
                 * 1、temp节点的next指向新节点
                 * 2、再把新节点设置为temp节点
                 * 3、temp节点的next设置为头结点
                 */
                temp.setNext(boy);
                temp = boy;
                temp.setNext(head);
            }
        }


    }


    //根据用户输入，生成一个小孩出圈的顺序

    /**
     *
     * @param start 表示从第几个小孩开始数数
     * @param count 数几下
     * @param nums  小孩人数
     */
    public void count(int start,int count,int nums) {
        // 对数据进行校验
        if(head==null || start < 1 || start > nums) {
            throw new RuntimeException("参数有误，请重新输入");
        }

        // 辅助变量，帮助生成出圈顺序
        Boy pre = head;
        Boy temp = null;

        // 找到报数前一个小孩
        while(pre.getNext().getNo()!=start) {
            pre = pre.getNext();
        }
        System.out.println("第一个报数的小孩是" + pre.getNext().getNo());


        while(temp!=pre) {
            // 循环报数
            for (int i = 0; i < count-1; i++) {
                temp = pre;
                pre=pre.getNext();
            }
            // 报到号码出圈
            System.out.println("当前出圈小孩编号：" + pre.getNext().getNo());
            pre.setNext(pre.getNext().getNext());
        }
    }

    // 查看所有的小孩
    public void list() {
        // 判断链表是否为空
        if(head==null) {
            System.out.println("链表为空,没有数据可遍历");
            return;
        }
        // 辅助变量，帮助遍历循环链表
        Boy temp = head;

        do {
            System.out.println("小孩的编号为" + temp.getNo());
            temp = temp.getNext();
        }while(temp!=head);

    }

}


// 创建boy类，代表节点
class Boy {

    /**
     * no : 小孩编号
     */
    private int no;
    /**
     * next：指向下一个小孩的指针
     * 默认为null
     */
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Boy{" +
                "no=" + no +
                '}';
    }
}
