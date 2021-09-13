package atguigu.sort;

import java.util.Arrays;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 实现冒泡排序
 * 冒泡排序（Bubble Sort）是一种交换排序，它的基本思想是：
 * 两两比较相邻记录的关键字，如果反序则交换，直到没有反序的记录为止，
 * 冒泡的实现在细节上可以有很多种变化
 * @date 2021/3/11 - 9:55
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {10,9,1,5,8,3,7,4,6,2,};
//        int[] arr = {3,9,-1,10,-2};
        // 测试
//        int num = 80000;
//        int[] arr = new int[num];
//        for (int i = 0; i < arr.length; i++) {
//            // 生成[0,800000)之间的数
//            arr[i] = (int)(Math.random()* num);
//        }

        long startTime = System.currentTimeMillis();
        BubbleSort.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");

    }

    // 改进的冒泡排序算法

    /**
     * @param arr 需要排序的数组
     * 改进的冒泡排序算法，这一算法比起之前的没有flag的有进步
     * 在上十万条的数据的排序中，这种差异会体现出来，
     * 图中较小的数字如同气泡般慢慢浮到上面，因此就将此算法命名为
     * 冒泡算法
     * 冒泡排序的时间复杂度为O(N^2)
     */
    public static void sort(int[] arr){

        /**
         * flag 初始值为真
         * 如果中间没有交换，说明中间无需排序
         */
        boolean flag = true;
        int i,j;
        for (i = 0; i < arr.length-1&&flag; i++) {
            flag = false;
            System.out.println("第" + (i+1) + "次排序");
            // 注意j是从后往前循环的
            for (j = arr.length-2; j >= i ; j--) {
                /*
                    若前者大于后者
                    进行交换，并且把flag置为true
                 */
                if(arr[j] > arr[j+1]){
                    arr[j+1]^=arr[j];
                    arr[j]^=arr[j+1];
                    arr[j+1]^=arr[j];
                    flag = true;
                    System.out.println(Arrays.toString(arr));
                }

            }
            // 从后往前循环
//            for(j = 0; j < arr.length-1-i; j++) {
//                /**
//                 * 若前者大于后者
//                 * 进行交换，并且把flag置为true
//                 */
//                if(arr[j] > arr[j+1]){
//                    arr[j+1]^=arr[j];
//                    arr[j]^=arr[j+1];
//                    arr[j+1]^=arr[j];
//                    flag = true;
//                    System.out.println(Arrays.toString(arr));
//                }
//            }

        }

    }
}
