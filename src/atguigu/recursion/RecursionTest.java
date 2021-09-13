package atguigu.recursion;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/2/22 - 21:08
 */
public class RecursionTest {

    public static void main(String[] args) {
        test(4);
    }

    public static void test(int n){
        if(n > 2) {
            test(n-1);
        }
        System.out.println("n = " + n);
    }
}
