package atguigu.linkedlist;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/1/26 - 20:25
 */
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        System.out.println("双向链表测试");

        // 1、创建节点
        HeroNode2 h1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 h2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 h3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 h4 = new HeroNode2(4, "林冲", "豹子头");
        // 2、创建一个双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        // 、尾插法
//        doubleLinkedList.add(h1);
//        doubleLinkedList.add(h2);
//        doubleLinkedList.add(h3);
//        doubleLinkedList.add(h4);
        // 、头插法
//        doubleLinkedList.HeadInsert(h4);
//        doubleLinkedList.HeadInsert(h3);
//        doubleLinkedList.HeadInsert(h2);
//        doubleLinkedList.HeadInsert(h1);
        // 3、按顺序插入
        doubleLinkedList.addByOrder(h2);
        doubleLinkedList.addByOrder(h3);
        doubleLinkedList.addByOrder(h1);
        doubleLinkedList.addByOrder(h4);
        // 4、手动设置双向链表的尾引用
        doubleLinkedList.CheckTail();

        System.out.println("修改链表前");
        doubleLinkedList.list();
        doubleLinkedList.reverseList();

        // 5、修改测试
        HeroNode2 heroNode2 = new HeroNode2(2, "公孙胜", "入云龙");
        doubleLinkedList.update(heroNode2);
        System.out.println("修改后的链表");

        // 6、开始测试
        doubleLinkedList.delete(h3);
        System.out.println("删除后");

        // 7、输出最终结果
        System.out.println("尾引用测试：\ndoubleLinkedList.getTail().getNo() = " + doubleLinkedList.getTail().getNo());
        doubleLinkedList.list();
        doubleLinkedList.reverseList();


    }
}

// 创建双向链表类
class DoubleLinkedList {
    /**
     * 头结点引用
     * 指向链表头
     */
    private HeroNode2 head;
    /**
     * 尾结点引用
     * 指向链表尾
     */
    private HeroNode2 tail;
    /**
     * 用于初始化头结点和尾结点
     * 不可更改其值
     */
    private final HeroNode2 heroNode2 = new HeroNode2(0, null, null);

    public DoubleLinkedList() {
        // 1、初始时，节点的next值为空
        // 2、头引用和尾引用指向同一个节点
        this.heroNode2.setNext(null);
        this.heroNode2.setPre(null);
        this.head = this.tail = heroNode2;
    }

    public HeroNode2 getHead() {
        return head;
    }

    public void setHead(HeroNode2 head) {
        this.head = head;
    }

    public HeroNode2 getTail() {
        return tail;
    }

    public void setTail(HeroNode2 tail) {
        this.tail = tail;
    }

    // 头插法：插入到链表头部
    public void HeadInsert(HeroNode2 heroNode2) {
        /**
         * 1、如果头结点next不空，那么头结点next的pre指向新插入节点
         * 2、反之，头节点的next为空.尾引用指向新插入节点
         * 2、将新节点的next指向原头结点的next
         * 3、原头结点的next指向新插入节点
         * 4、将当前节点的pre设置为头结点
         */
        if (this.head.getNext() != null) {
            this.head.getNext().setPre(heroNode2);
        } else {
            this.tail = heroNode2;
        }
        heroNode2.setNext(this.head.getNext());
        this.head.setNext(heroNode2);
        heroNode2.setPre(this.head);
    }

    // 尾插法：插入到双向链表尾部
    public void add(HeroNode2 heroNode2) {
        /**
         * 1、将新节点的next设置为空
         * 2、尾结点的next指向新节点
         * 3、新节点的pre指向尾结点
         * 3、将当前节点设置为尾结点
         */
        heroNode2.setNext(null);
        this.tail.setNext(heroNode2);
        heroNode2.setPre(this.tail);
        this.tail = heroNode2;
    }

    // 根据编号来顺序插入数据
    public void addByOrder(HeroNode2 heroNode) {
        /**
         * 头结点不能动，通过辅助变量来找到添加的位置
         * 找到临时节点的上一个节点
         */
        HeroNode2 temp = head;
        boolean flag = false;

        while (temp.getNext() != null) {

            // 查看temp的下一个节点是否大于当前待插入节点
            if (temp.getNext().getNo() == heroNode.getNo()) {
                flag = true;
                break;
            } else if (temp.getNext().getNo() > heroNode.getNo()) {
                break;
            }
            temp = temp.getNext();
        }

        if (flag) {
            System.out.println("已存在" + heroNode + ",请勿重复添加");
        } else {
            heroNode.setNext(temp.getNext());
            if (temp.getNext() != null) {
                temp.getNext().setPre(heroNode);
            }
            temp.setNext(heroNode);
            heroNode.setPre(temp);
        }
    }

    // 查找尾结点并设置
    public void CheckTail() {

        if (head.getNext() == null) {
            System.out.println("链表为空，没有数据可遍历");
            return;
        }
        //创建临时变量来遍历链表
        // 初始时指向链表的头结点的next
        HeroNode2 heroNode2 = this.head;

        // 遍历链表
        while (heroNode2.getNext() != null) {
            // 指向下一个节点
            heroNode2 = heroNode2.getNext();
        }
        this.tail = heroNode2;
    }

    // 根据编号，修改节点信息
    public void update(HeroNode2 heroNode2) {
        // 判断是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空，没有数据可遍历");
            return;
        }

        HeroNode2 temp = head.getNext();
        boolean flag = false;
        // 循环查找是否存在该节点
        while (temp != null) {
            if (temp.getNo() == heroNode2.getNo()) {
                // 表示找到了
                flag = true;
                break;
            }
            temp = temp.getNext();
        }

        // 通过flag变量判断是否找到了该节点变量
        if (flag) {
            // 进行修改
            temp.setName(heroNode2.getName());
            temp.setNickname(heroNode2.getNickname());
        } else {
            System.out.println("查无此人，修改失败");
        }
    }


    // 删除节点
    public void delete(HeroNode2 heroNode2) {

        // 判断是否为空
        if (head.getNext() == null) {
            System.out.println("链表为空，没有数据可遍历");
            return;
        }

        /**
         * 头结点不能动，通过辅助变量来找到添加的位置
         * 找到临时节点的上一个节点
         */
        HeroNode2 temp = head.getNext();
        boolean flag = false;
        // 循环查找是否存在该节点
        while (temp != null) {
            if (temp.getNo() == heroNode2.getNo()) {
                // 表示找到了
                flag = true;
                break;
            }
            temp = temp.getNext();
        }

        // 通过flag变量判断是否找到了该节点变量
        if (flag) {
            // temp的上一个节点指向temp的下一个节点
            temp.getPre().setNext(temp.getNext());

            // temp的下个节点指向temp的上一个节点
            // 如果是最后一个节点，无需执行下面一行，否则会出现空指针异常: temp.next() == null
            if (temp.getNext() != null) {
                temp.getNext().setPre(temp.getPre());
            }
        } else {
            System.out.println("查无此人，删除失败");
        }
    }

    // 遍历双向链表的方法
    public void list() {

        if (head.getNext() == null) {
            System.out.println("链表为空，没有数据可遍历");
            return;
        }
        //创建临时变量来遍历链表
        // 初始时指向链表的头结点的next
        HeroNode2 heroNode2 = this.head.getNext();
        System.out.println("顺序遍历链表:\n");
        // 遍历链表
        while (heroNode2 != null) {
            // 输出节点信息
            System.out.println(heroNode2);
            // 指向下一个节点
            heroNode2 = heroNode2.getNext();
        }


    }

    // 反向遍历双向链表的方法
    public void reverseList() {

        if (head.getNext() == null) {
            System.out.println("链表为空，没有数据可遍历");
            return;
        }
        //创建临时变量来遍历链表
        // 初始时指向链表的头结点的next
        HeroNode2 heroNode2 = this.tail;
        System.out.println("反向遍历链表:\n");
        // 遍历链表
        while (heroNode2 != this.head) {
            // 输出节点信息
            System.out.println(heroNode2);
            // 指向下一个节点
            heroNode2 = heroNode2.getPre();
        }


    }

}


// 定义一个HereNode，每个HeroNode对象是一个节点
class HeroNode2 {

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
     * 默认为null
     */
    private HeroNode2 next;
    /**
     * 指向上一个节点的引用
     * 默认为null
     */
    private HeroNode2 pre;

    // 多参构造器
    public HeroNode2(int no, String name, String nickname) {
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

    public HeroNode2 getNext() {
        return next;
    }

    public void setNext(HeroNode2 next) {
        this.next = next;
    }

    public HeroNode2 getPre() {
        return pre;
    }

    public void setPre(HeroNode2 pre) {
        this.pre = pre;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}