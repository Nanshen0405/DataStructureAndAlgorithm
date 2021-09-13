package atguigu.sort;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 简单选择排序
 * 就是通过n-i次关键字间的比较，从n-i+1个记录中选出
 * 关键字最小的记录，并和第i（1≤i≤n）个记录交换。
 * @date 2021/3/19 - 8:54
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = {10,9,1,5,8,3,7,4,6,2,};
//        int[] arr = {101,34,119,1};
        int num = 80000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            // 生成[0,800000)之间的数
            arr[i] = (int)(Math.random()* num);
        }
        long startTime = System.currentTimeMillis();
        SelectSort.sort(arr);
        long endTime = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");
    }

    /**
     * 简单选择排序
     * 总的来说，选择排序是一种很容易理解和实现的排序算法
     * 它有两个很鲜明的特点
     * - 运行时间和输入无关
     * - 数据移动是最少的
     * @param arr 要排序的数组
     */
    public static void sort(int[] arr) {

        /**
         * 假设要从小到大排序
         */
        for (int i = 0; i < arr.length-1; i++) {
            /**
             * 设定第min个为最小/最大点，即为i
             * 从i的下一个找到数组最后一个元素，如果有比i更小/大的
             * 则把下标赋值给min，直到遍历完所有的元素出内层循环
             * 如果发生了交换，则min不会i，交换数组中min下标和i下标
             * 的值，以此类推，每一个值都会比前一个值大/小，因为更小的值
             * 在前一次循环就交换出去了
             */
            int min = i;
//            System.out.println("第" + (i+1) + "次排序");
            for (int j = i+1; j < arr.length; j++) {
                if(arr[min] > arr[j]) {
                    min = j;
                }
            }
            if(min!=i) {
                arr[min]^=arr[i];
                arr[i]^=arr[min];
                arr[min]^=arr[i];
            }
//            System.out.println(Arrays.toString(arr));
        }
    }
}
