package atguigu.queue;

import java.util.Scanner;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/1/20 - 19:03
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        // test
        // 创建一个队列
        ArrayQueue queue = new ArrayQueue(3);
        char c;//接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add):  添加数据");
            System.out.println("g(get):  取出数据");
            System.out.println("h(head): 显示队列头");
            c = scanner.next().charAt(0);
            switch (c) {
                case's':    // 查看队列
                    queue.showQueue();
                    break;
                case'a':
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
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
                    scanner.close();
                    loop = false;
                    break;
            }
        }
        System.out.println("程序退出");
    }
}

// 使用数组来模拟队列

class ArrayQueue {
    /**
     * maxSize 表示数组最大容量
     */
    private int maxSize;
    /**
     * 队列头下标
     */
    private int front;
    /**
     * 队列尾下标
     */
    private int rear;
    /**
     * 该数据用于存放数据，模拟队列
     */
    private int[] arr;

    // 创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
        front = -1; // 指向队列头，初始化时指向队列头的前一个位置
        rear = -1;  // 指向队列尾，指向队列尾的数据
    }

    // 判断队列是否为满
    public boolean isFull() {
        return rear == maxSize-1;
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
        arr[++rear] = n;
    }
    // 获取队列数据
    public int getQueue() {
        // 判断队列是否空
        if(isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列空，不能取数据");
        }

        // 后移front
        front++;
        return arr[front];
    }

    // 显示队列的所有数据
    public void showQueue() {
        // 遍历
        // 判断队列是否空
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }
    // 显示队列的头数据
    public int headQueue() {
        // 判断队列是否空
        if(isEmpty()){
            // 通过抛出异常来处理
            throw new RuntimeException("队列空，没有数据");
        }

        return arr[front+1];
    }
}