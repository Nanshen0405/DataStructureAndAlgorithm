package atguigu.linkedlist;


import java.util.Stack;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/1/22 - 18:20
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        //Test
        // 先创建节点
        HeroNode h1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode h2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode h3 = new HeroNode(3, "吴用", "智多星");
        HeroNode h4 = new HeroNode(4, "林冲", "豹子头");

        // 创建一个链表
        SingLinkedList singLinkedList = new SingLinkedList();
        // 加入节点按编号
//        list1.addByOrder(h1);
//        list1.addByOrder(h4);
//        System.out.println("合并前链表：");
//        System.out.println("输出第一个链表");
//        list1.list();
//
//        // 创建第二个链表
//        SingLinkedList list2 = new SingLinkedList();
//        list2.addByOrder(h3);
//        list2.addByOrder(h2);
//        System.out.println("输出第二个链表");
//        list2.list();
//
//        System.out.println("开始合并");
//        combinationList(list1,list2);
//        System.out.println("合并后链表一：");
//        list1.list();
//        System.out.println("合并后链表二：");
//        list2.list();

//        singLinkedList.HeadInsert(h1);
//        singLinkedList.HeadInsert(h2);
//        singLinkedList.HeadInsert(h3);
//        singLinkedList.HeadInsert(h4);
//        singLinkedList.add(h1);
//        singLinkedList.add(h2);
//        singLinkedList.add(h3);
//        singLinkedList.add(h4);
        singLinkedList.addByOrder(h2);
        singLinkedList.addByOrder(h3);
        singLinkedList.addByOrder(h1);
        singLinkedList.addByOrder(h4);
        System.out.println("尾指针测试：singLinkedList.getTail().getNo() = " + singLinkedList.getTail().getNo());
        singLinkedList.list();
        // 反转单链表
//        System.out.println("反转单链表之前");
//        singLinkedList.list();
//        reversetList(singLinkedList.getHead());
//        System.out.println("反转单链表之后");
//        singLinkedList.list();

//        System.out.println("测试逆序打印单链表,不改变链表本身的结构");
//        reversePrint(list1.getHead());

//        // update操作测试
//        System.out.println("修改前");
//        singLinkedList.list();
//        singLinkedList.update(new HeroNode(1,"小卢","玉玉~"));
//        System.out.println("修改后");
//        singLinkedList.list();
//
//        // delete操作测试
//        System.out.println("删除前");
//        singLinkedList.list();
//        singLinkedList.delete(h4);
//        System.out.println("删除后");
//        singLinkedList.list();
//        // getLength测试
//        System.out.println("单链表有效节点个数为" + getLength(singLinkedList.getHead()));
//        // 查找单链表中的倒数第K个节点测试
//        HeroNode res = findLastNode(singLinkedList.getHead(),3);
//        System.out.println("找到的是："+res);
//
    }

    // 方法：利用链表的基本操作来实现两个有序链表的合并
    public static void combinationList(SingLinkedList L1,SingLinkedList L2) {
        // 如果两个链表都为空或者其中有一个是空的就无需合并
        if(L1.getHead().getNext()==null||L2.getHead().getNext()==null) {
            return;
        }
        // 从头指针遍历L2,加到L1中
        HeroNode heroNode = L2.getHead().getNext();
        while(heroNode!=null) {
            // 1、保存当前节点的next
            HeroNode temp = heroNode.getNext();
            // 将当前节点插入到L1，并在L2中删去
            L1.addByOrder(new HeroNode(heroNode.getNo(),heroNode.getName(),heroNode.getNickname()));
            L2.delete(heroNode);
            // 指向下一个节点
            heroNode=temp;
        }

    }

    // 方法：利用栈的数据结构来实现逆序打印
    public static void reversePrint(HeroNode head) {
        // 查看是否为空
        if(head.getNext() == null) {
            return;
        }
        // 将节点压入栈中
        Stack<HeroNode> stack = new Stack<>();
        HeroNode temp = head.getNext();
        // 将链表的所有节点压入栈中
        while(temp!=null) {
            stack.push(temp);
            temp=temp.getNext();
        }
        // 将栈中的节点进行打印
        while(!stack.empty()) {
            System.out.println(stack.pop());
        }
    }


    //将单链表反转
    public static void reversetList(HeroNode head) {
        // 如果当前链表为空，或者只有一个节点，无需反转直接返回
        if(head.getNext()==null || head.getNext().getNext() == null) {
            return;
        }
        // 定义辅助变量指针
        HeroNode p = head.getNext();
        HeroNode q = null;
        HeroNode reverseHead = new HeroNode(0,null,null);
        /**
         * 1、遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表
         * reverseHead的最前端
         * 动脑筋
         */
        while(p!=null) {
            /*
                1、保存当前节点的next节点
                2、将当前节点的next指向新的链表的头结点
                3、链表头节点next设置为当前节点
                4、p指向p的next
             */
            q = p.getNext();
            p.setNext(reverseHead.getNext());
            reverseHead.setNext(p);
            p=q;
        }
        // 将head.next 指向reverseHead.next,实现单链表的反转操作
        head.setNext(reverseHead.getNext());

    }

    // 查找单链表中的倒数第K个节点【新浪面试题】
    // 思路
    // 1、编写一个方法，接收head和index
    // 2、head 表示单链表头结点
    // 3、index 表示倒数第index个节点
    // 4、从头到尾遍历，获取链表长度
    // 5、得到链表长度后，我们再从第一个开始遍历链表到（长度-index）个
    public static HeroNode findLastNode(HeroNode head,int index) {
        // 如果链表为空
        if(head.getNext()==null) {
            return null;
        }
        // 第一次遍历
        int size = getLength(head);
        // 第二次遍历，遍历到size-index
        if(index <= 0 || index > size){
            return null;
        }
        // 定义辅助
        HeroNode temp = head.getNext();
        for (int i = 0; i < size-index; i++) {
            temp=temp.getNext();
        }
        return temp;
    }

    /**
     * 返回链表的有效长度
     * @param head 链表头结点
     * @return 返回有效节点个数
     */
    public static int getLength(HeroNode head) {
        if(head.getNext()==null) {
            return 0;
        }
        int length = 0;
        HeroNode heroNode = head.getNext();
        while(heroNode!=null) {
            length++;
            heroNode=heroNode.getNext();
        }
        return length;
    }
}
// 定义一个SingleLinkedList管理我们的英雄

class SingLinkedList {

    /**
     * 头结点引用
     * 指向链表头
     */
    private HeroNode head;
    /**
     * 尾结点引用
     * 指向链表尾
     */
    private HeroNode tail;
    /**
     * 用于初始化头结点和尾结点
     * 不可更改其值
     */
    private final HeroNode heroNode = new HeroNode(0, null, null);

    // 无参构造
    public SingLinkedList() {
        // 1、初始时，节点的next值为空
        // 2、头引用和尾引用指向同一个节点
        this.heroNode.setNext(null);
        this.head = this.tail = heroNode;
    }

    // 头插法：插入到链表头部
    public void HeadInsert(HeroNode heroNode) {
        /**
         * 1、将新节点的next指向原头结点的next
         * 2、将当前节点设置头结点
         */
        if(this.head.getNext()==null) {
            this.tail = heroNode;
        }
        heroNode.setNext(this.head.getNext());
        this.head.setNext(heroNode);
    }
    // 尾插法：插入到链表尾部
    public void add(HeroNode heroNode) {
        /**
         * 1、将新节点的next设置为空
         * 2、尾结点的next指向新节点
         * 3、将当前节点设置为尾结点
         */
        heroNode.setNext(null);
        this.tail.setNext(heroNode);
        this.tail = heroNode;
    }
    // 根据编号来顺序插入数据
    public void addByOrder(HeroNode heroNode) {
        /**
         * 头结点不能动，通过辅助变量来找到添加的位置
         * 找到临时节点的上一个节点
         */
        HeroNode temp = head;
        boolean flag = false;

        while(temp.getNext() != null) {

            // 查看temp的下一个节点是否大于当前待插入节点
            if(temp.getNext().getNo() == heroNode.getNo()) {
                flag = true;
                break;
            } else if(temp.getNext().getNo() > heroNode.getNo()) {
                break;
            }
            temp=temp.getNext();
        }

        if(flag) {
            System.out.println("已存在" + heroNode + ",请勿重复添加");
        } else {
            if(temp.getNext() == null) {
                this.tail = heroNode;
            }
            heroNode.setNext(temp.getNext());
            temp.setNext(heroNode);
        }
    }

    // 根据编号，修改节点信息
    public void update(HeroNode heroNode) {
        // 判断是否为空
        if(head.getNext()==null){
            System.out.println("链表为空，没有数据可遍历");
            return;
        }

        HeroNode temp = head.getNext();
        boolean flag = false;
        // 循环查找是否存在该节点
        while(temp!=null) {
            if(temp.getNo() == heroNode.getNo()) {
                // 表示找到了
                flag = true;
                break;
            }
            temp = temp.getNext();
        }

        // 通过flag变量判断是否找到了该节点变量
        if(flag) {
            // 进行修改
            temp.setName(heroNode.getName());
            temp.setNickname(heroNode.getNickname());
        } else {
            System.out.println("查无此人，修改失败");
        }
    }

    // 删除节点
    public void delete(HeroNode heroNode) {

        // 判断是否为空
        if(head.getNext()==null){
            System.out.println("链表为空，没有数据可遍历");
            return;
        }

        /**
         * 头结点不能动，通过辅助变量来找到添加的位置
         * 找到临时节点的上一个节点
         */
        HeroNode temp = head;
        boolean flag = false;
        // 循环查找是否存在该节点
        while(temp.getNext()!=null) {
            if(temp.getNext().getNo() == heroNode.getNo()) {
                // 表示找到了
                flag = true;
                break;
            }
            temp = temp.getNext();
        }

        // 通过flag变量判断是否找到了该节点变量
        if(flag) {
            // 将temp指向待删除节点的next
            temp.setNext(temp.getNext().getNext());
        } else {
            System.out.println("查无此人，删除失败");
        }
    }

    public boolean check(HeroNode heroNode) {
        if(head.getNext()==null) {
            return false;
        }
        HeroNode temp = head.getNext();
        while(temp!=null) {
            if(temp.getNo()==heroNode.getNo()) {
                return true;
            }
            temp=temp.getNext();
        }
        return false;
    }



    // 遍历链表
    public void list(){

        if(head.getNext()==null){
            System.out.println("链表为空，没有数据可遍历");
            return;
        }
        //创建临时变量来遍历链表
        // 初始时指向链表的头结点的next
        HeroNode heroNode = this.head.getNext();
        // 遍历链表
        while(heroNode!=null) {
            // 输出节点信息
            System.out.println(heroNode);
            // 指向下一个节点
            heroNode = heroNode.getNext();
        }

    }
    // getter、setter方法
    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    public HeroNode getTail() {
        return tail;
    }

    public void setTail(HeroNode tail) {
        this.tail = tail;
    }

    @Override
    public String toString() {
        return "SingLinkedList{" +
                "head=" + head +
                ", tail=" + tail +
                '}';
    }
}


// 定义一个HereNode，每个HeroNode对象是一个节点
class HeroNode {

    /**
     * 英雄编号
     */
    private int no;
    /**
     * 英雄名称
     * 例如：宋江
     */
    private String name;
    /**
     * 英雄外号
     * 例如：及时雨
     */
    private String nickname;
    /**
     * 指向下一个节点的引用
     */
    private HeroNode next;

    // 多参构造器
    public HeroNode(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    // getter、setter方法
    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}