package atguigu.test;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/5/15 - 22:26
 */
public class B {

    public static B B1 = new B();
    public static B B2 = new B();

    {
        System.out.println("构造块");
    }
    static {
        System.out.println("静态块");
    }

    public static void main(String[] args) {
        B b = new B();
    }
}
