package atguigu.sort;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 实现冒泡排序
 * 冒泡排序（Bubble Sort）是一种交换排序，它的基本思想是：
 * 两两比较相邻记录的关键字，如果反序则交换，直到没有反序的
 * 记录为止，冒泡的实现在细节上可以有很多种变化
 * @date 2021/3/11 - 9:55
 */
public class BubbleSort {

    public static void main(String[] args) {
//        int[] arr = {10, 9, 1, 5, 8, 3, 7, 4, 6, 2,};
//        int[] arr = {3,9,-1,10,-2};
        // 测试
        int num = 80000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            // 生成[0,800000)之间的数
            arr[i] = (int)(Math.random()* num);
        }

        long startTime = System.currentTimeMillis();
        BubbleSort.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");

    }

    /**
     * <p>
     * 原始的冒泡排序算法
     * </p>
     * 8W数据总耗时为8307毫秒
     * @param arr 待排序的数组
     */
    public static void sort(int[] arr) {
        int i, j;
        for (i = 0; i < arr.length - 1; i++) {
//            System.out.println("第" + (i + 1) + "次排序");

            // 注意j是从前往后循环的
            for (j = i + 1; j < arr.length; j++) {
                /*
                    若前者大于后者
                    进行交换
                 */
                if (arr[i] > arr[j]) {
                    arr[j] ^= arr[i];
                    arr[i] ^= arr[j];
                    arr[j] ^= arr[i];
//                    System.out.println(Arrays.toString(arr));
                }
            }
        }
    }

    /**
     * <p>
     * 改进的冒泡排序算法，这一算法比起之前的没有flag的有进步
     * 在上十万条的数据的排序中，这种差异会体现出来，图中较小
     * 的数字如同气泡般慢慢浮到上面，因此就将此算法命名为
     * 冒泡算法
     * </p>
     * 冒泡排序的时间复杂度为O(N^2)
     * 8W数据总耗时为9815毫秒
     * @param arr 待排序的数组
     */
    public static void sort1(int[] arr) {

        /*
            flag 初始值为真
            如果中间没有交换，说明中间无需排序
         */
        boolean flag = true;
        int i, j;
        for (i = 0; i < arr.length - 1 && flag; i++) {
            flag = false;
//            System.out.println("第" + (i + 1) + "次排序");
            // 注意j是从后往前循环的
            for (j = arr.length - 2; j >= i; j--) {
                /*
                    若前者大于后者
                    进行交换，并且把flag置为true
                 */
                if (arr[j] > arr[j + 1]) {
                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    flag = true;
//                    System.out.println(Arrays.toString(arr));
                }

            }
            // 从前往后循环
//            for (j = 0; j < arr.length - 1 - i; j++) {
//                /*
//                    若前者大于后者
//                    进行交换，并且把flag置为true
//                 */
//                if (arr[j] > arr[j + 1]) {
//                    arr[j + 1] ^= arr[j];
//                    arr[j] ^= arr[j + 1];
//                    arr[j + 1] ^= arr[j];
//                    flag = true;
//                    System.out.println(Arrays.toString(arr));
//                }
//            }

        }



    }



    /**
     * <p>
     * 改进后的冒泡排序算法
     * 有一个变量指向上次交换的最后一个位置，下一趟我们就从上
     * 次交换的位置的前面开始作为我们这一趟“冒泡”最后一个位置
     * 就可以了
     * </p>
     * 8W数据总耗时为11668毫秒
     * @param arr 待排序的数组
     */
    public static void sort2(int[] arr) {

        int m = arr.length - 1, j;
//        int i = 0;

        while(m > 1)
        {
//            System.out.println("第" + (++i) + "次排序");
            int lastExchangeIndex = 1;
            for(j = 0; j < m; j++)
            {
                if(arr[j] > arr[j+1]) {

                    arr[j + 1] ^= arr[j];
                    arr[j] ^= arr[j + 1];
                    arr[j + 1] ^= arr[j];
                    // 记录下进行交换的位置
                    lastExchangeIndex = j;
//                    System.out.println(Arrays.toString(arr));
                }
            }
            /* 本趟最后一次进行交换的位置 */
            m = lastExchangeIndex;
        }
    }
}
