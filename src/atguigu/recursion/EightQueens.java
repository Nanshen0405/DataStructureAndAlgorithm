package atguigu.recursion;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * 八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。
 * 该问题是国际西洋棋棋手马克斯·贝瑟尔于1848 年提出：
 * 在 8×8 格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即：任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法？
 * @date 2021/3/8 - 8:26
 */
public class EightQueens {


    /**
     * 定义一个max表示有多少个皇后
     */
    final private static int MAX_SIZE = 8;
    /**
     *  定义数组array，保存皇后放置位置的结果，比如arr[8] = {0,4,7,5,2,6,1,3}
     */
    private int[] arr;
    private int count;
    private int judgeCount;

    public int getJudgeCount() {
        return judgeCount;
    }

    public int getCount() {
        return count;
    }

    public EightQueens() {
        arr = new int[MAX_SIZE];
    }

    public static void main(String[] args) {
        // TEST http://www.7k7k.com/swf/49842.htm
        EightQueens eightQueens = new EightQueens();

        System.out.println("开始八皇后解法");
        eightQueens.check(0);
        System.out.println("一共有" + eightQueens.getCount() + "种解法");
        System.out.println("一共判断冲突的次数有" + eightQueens.getJudgeCount()+"次！");
    }

    /**
     * 编写方法来放置第n个皇后 ，
     * check方法每一次递归时，进入到check中都有for循环
     */
    private void check(int n) {
        // 八个皇后已经放好
        if(n == MAX_SIZE) {
            printArray();
            return;
        }

        /**
         * 依次放入皇后，并且判断是否有冲突
         */
        for (int i = 0; i < MAX_SIZE; i++) {
            // 当前皇后 n，放到该行的第一列
            arr[n]=i;
            // 判断当放置第n个皇后到i列时，是否冲突
            if(judge(n)){
                // 不冲突就接着放n+1个皇后
                check(n+1);
            }
            // 如果冲突，就继续执行arr[n] = i，即将第n个皇后，放置在本行后一个位置
        }

    }

    /**
     * 查看当我们放置第n个皇后，就去检测该皇后是否和前面已经摆放的皇后冲突.
     * @param n 表示第n个皇后
     * @return 如果冲突返回false，否则返回true
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            /**
             * 检查与前面的皇后
             * 1、是否同一列
             * 2、和 是否同一对角线
             * （斜率是否一致，行差等于列差，表示45°角，说明在同一斜线上）
             * n = 1 放置在第二列 n = 1 arr[1] = 1
             * Math.abs(1-0) == 1
             * Math.abs(arr[n]-arr[i]) == Math.abs(1-0)=1
             * 3、判断是否同一行不需要 n一直会递增
             */
            if(arr[i]==arr[n] || Math.abs(n-i) == Math.abs(arr[n]-arr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     *  打印皇后的摆放位置
     */
    private void printArray(){
        count++;
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\n");
    }

}
