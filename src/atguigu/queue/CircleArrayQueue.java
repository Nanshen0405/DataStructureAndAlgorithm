package atguigu.queue;

import java.util.Scanner;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/1/20 - 21:26
 */
public class CircleArrayQueue {
    public static void main(String[] args) {
        // 创建一个队列
//        CircleArray01 queue = new CircleArray01(3);
        CircleArray02 queue = new CircleArray02(3);
        //接收用户输入
        char key = ' ';
        //接收用户输入
        Scanner in = new Scanner(System.in);
        boolean loop = true;
        System.out.println("测试环形队列的实现");
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add):  添加数据");
            System.out.println("g(get):  取出数据");
            System.out.println("h(head): 显示队列头");
            key = in.next().charAt(0);
            switch (key) {
                case's':    // 查看队列
                    queue.showQueue();
                    break;
                case'a':
                    System.out.println("请输入一个数");
                    int value = in.nextInt();
                    queue.addQueue(value);
                    break;
                case'g':    // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的数据res = " + res);
                    } catch (Exception e){

                        System.out.println(e.getMessage());
                    }
                    break;
                case'h':    // 查看队列头数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是：%d\n",res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case'e':
                    in.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }

}

// 使用数组来模拟队列
// 需要预留一个空间来判断队空队满

class CircleArray01 {
    /**
     * maxSize 表示数组最大容量
     */
    private int maxSize;
    /**
     * 队列头下标
     * 初始值 = 0
     * front指向队列的第一个元素
     */
    private int front;
    /**
     * 队列尾下标
     * 初始值 = 0
     * rear指向队列最后一个元素的后一个位置
     */
    private int rear;
    /**
     * 该数据用于存放数据，模拟队列
     */
    private int[] arr;

    // 创建队列的构造器
    public CircleArray01(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    // 判断队列是否为满
    public boolean isFull() {
       return (rear+1)%maxSize==front;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return rear == front;
    }

    // 添加数据到队列
    public void addQueue(int n) {
        // 判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据——");
            return;
        }
        // 后移rear，把数据加入到队列中
        arr[rear] = n;
        // 将rear后移，考虑取模
        rear = (rear+1)%maxSize;
    }
    // 获取队列数据
    public int getQueue() {
        // 判断队列是否空
        if(isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }

        // 需要分析出：front是指向队列的第一个元素
        // 1、先把front对应的值保留到一个临时变量
        // 2、将front后移,考虑取模
        // 3、将临时保存的变量返回
        int value = arr[front];
        front = (front+1)%maxSize;

        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        // 判断队列是否空
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        // 动脑筋front+当前数据大小
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    // 显示队列的头数据
    public int headQueue() {
        // 判断队列是否空
        if(isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列空，没有数据");
        }

        return arr[front];
    }

    public int size() {
        return(rear+maxSize-front)%maxSize;
    }
}

/**
 * 使用一个flag变量来判断队满队空
 */
class CircleArray02 {
    /**
     * maxSize 表示数组最大容量
     */
    private int maxSize;
    /**
     * 队列头下标
     * 初始值 = 0
     * front指向队列的第一个元素
     */
    private int front;
    /**
     * 队列尾下标
     * 初始值 = 0
     * rear指向队列最后一个元素
     */
    private int rear;
    /**
     * 该数据用于存放数据，模拟队列
     */
    private int[] arr;
    /**
     *  标志变量flag
     */
    private boolean flag;

    // 创建队列的构造器
    public CircleArray02(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        // 初始时，队列为空
        flag = false;
    }

    /**
     * front == rear 且flag为假时，队列为空
     * front == rear 且flag为真时，队列为满
     */
    // 判断队列是否为满
    public boolean isFull() {
        return front==rear&&flag;
    }

    // 判断队列是否为空
    public boolean isEmpty() {
        return front==rear&&!flag;
    }


    // 添加数据到队列
    public void addQueue(int n) {

        // 判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据——");
            return;
        }

        int t = (rear+1)%maxSize;
        // 表示队列已满
        if(t == front) {
            flag = true;
        }
        // 后移rear，把数据加入到队列中
        arr[rear] = n;
        // 将rear后移，考虑取模
        rear = t;


    }
    // 获取队列数据
    public int getQueue() {

        // 判断队列是否空
        if(isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }

        int t = (front+1)%maxSize;
        if(t == rear) {
            // 表示队列已空
            flag = false;
        }
        // 需要分析出：front是指向队列的第一个元素
        // 1、先把front对应的值保留到一个临时变量
        // 2、将front后移,考虑取模
        // 3、将临时保存的变量返回
        int value = arr[front];
        front = t;

        return value;
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        // 判断队列是否空
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        // 思路：从front开始遍历，遍历多少个元素
        // 动脑筋front+当前数据大小
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n",i%maxSize,arr[i%maxSize]);
        }
    }
    // 显示队列的头数据
    public int headQueue() {
        // 判断队列是否空
        if(isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列空，没有数据");
        }

        return arr[front];
    }

    public int size() {
//        return(rear+maxSize-front)%maxSize;
        // 如果队列已满，长度直接为队列总长
        if(isFull()){
            return maxSize;
        }
        return(rear+maxSize-front)%maxSize;
    }
}
