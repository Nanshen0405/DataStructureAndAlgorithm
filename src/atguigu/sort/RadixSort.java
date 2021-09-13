package atguigu.sort;

import java.util.Arrays;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 基数排序（radix sort）属于“分配式排序”（distribution sort），又称“桶子法”
 * （bucket sort）或bin sort，顾名思义：它是通过键值的各个位的值，将要排序的元
 * 素分配至某些“桶”中，达到排序的目的。
 * @date 2021/8/4 - 13:34
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};

//        int num = 80000;
//        int[] arr = new int[num];
//        for (int i = 0; i < arr.length; i++) {
//            // 生成[0,800000)之间的数
//            arr[i] = (int) (Math.random() * num * 10);
//        }

        long startTime = System.currentTimeMillis();
        RadixSort.sort(arr);
        long endTime = System.currentTimeMillis();
//        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");

    }

    /**
     * 基数排序，不能使用负数
     *
     * @param arr 要排序的数组
     */
    public static void sort(int[] arr) {
        // 根据前面的推导过程。我们可以得到最终的基数排序代码
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        // 得到最大数的位数
        int maxLen = (max + "").length();
        /*
            基数排序是经典用空间换时间的排序算法
            定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            ，包含大概10个一维数组说明：为了防止在放入数的时候，
            数据不会溢出，则每个一维数组（桶），大小定义为arr.length
         */
        int[][] bucket = new int[10][arr.length];
        /*
            为了记录每个桶中实际存放了多少个数据，我们
            定义一个一维数组来记录各个桶的每次放入的数据个数
         */
        int[] bucketCounts = new int[10];

        for (int i = 0, n = 1; i < maxLen; i++, n *= 10) {

            /*
                每一轮：针对每个元素对应的位进行排序处理
                第一次是个位，第二次是十位，第三次是百位
             */
            for (int value : arr) {
                // 取出每个元素对应的位值
                int digit = value / n % 10;
                // 放到对应的桶里
                bucket[digit][bucketCounts[digit]] = value;
                bucketCounts[digit]++;
            }
            /*
                按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
                遍历每一个桶，并将桶中的数据放入到原数组中
             */
            int idx = 0;
            for (int j = 0; j < bucketCounts.length; j++) {
                // 如果桶中有数据，我们才放入到原数组中
                if (bucketCounts[j] != 0) {
                    // 循环该桶即第 i 个桶（第 i 个一维数组）放入
                    for (int k = 0; k < bucketCounts[j]; k++) {
                        // 取出元素放入到arr中
                        arr[idx++] = bucket[j][k];
                    }
                }
                // 将其置空
                bucketCounts[j] = 0;
            }
            System.out.println("第" + (i + 1) + "轮处理过后的：" + Arrays.toString(arr));
        }
    }


    /**
     * 基数排序
     *
     * @param arr 要排序的数组
     */
    public static void radixSort(int[] arr) {

        /*
            基数排序是经典用空间换时间的排序算法
            定义一个二维数组，表示10个桶，每个桶就是一个一维数组
            ，包含大概10个一维数组说明：为了防止在放入数的时候，
            数据不会溢出，则每个一维数组（桶），大小定义为arr.length
         */
        int[][] bucket = new int[10][arr.length];
        /*
            为了记录每个桶中实际存放了多少个数据，我们
            定义一个一维数组来记录各个桶的每次放入的数据个数
         */
        int[] bucketCounts = new int[10];
        int idx = 0;

        /*
            第一轮：针对每个元素的个位进行排序处理
         */
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素个位的值
            int digit = arr[i] % 10;
            // 放到对应的桶里
            bucket[digit][bucketCounts[digit]] = arr[i];
            bucketCounts[digit]++;
        }
        /*
            按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            遍历每一个桶，并将桶中的数据放入到原数组中
         */
        for (int i = 0; i < bucketCounts.length; i++) {
            // 如果桶中有数据，我们才放入到原数组中
            if (bucketCounts[i] != 0) {
                // 循环该桶即第 i 个桶（第 i 个一维数组）放入
                for (int j = 0; j < bucketCounts[i]; j++) {
                    // 取出元素放入到arr中
                    arr[idx++] = bucket[i][j];
                }
            }
            // 将其置空
            bucketCounts[i] = 0;
        }
//        arr = {542, 53, 3, 14, 214, 748}
        System.out.println("第一轮处理过后的：" + Arrays.toString(arr));
         /*
            第二轮：针对每个元素的十位进行排序处理
         */
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素个位的值
            int digit = arr[i] / 10 % 10;
            // 放到对应的桶里
            bucket[digit][bucketCounts[digit]] = arr[i];
            bucketCounts[digit]++;
        }
        /*
            按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            遍历每一个桶，并将桶中的数据放入到原数组中
         */
        idx = 0;
        for (int i = 0; i < bucketCounts.length; i++) {
            // 如果桶中有数据，我们才放入到原数组中
            if (bucketCounts[i] != 0) {
                // 循环该桶即第 i 个桶（第 i 个一维数组）放入
                for (int j = 0; j < bucketCounts[i]; j++) {
                    // 取出元素放入到arr中
                    arr[idx++] = bucket[i][j];
                }
            }
            // 将其置空
            bucketCounts[i] = 0;
        }
//        arr = {3, 14, 214, 542, 748, 53}
        System.out.println("第二轮处理过后的：" + Arrays.toString(arr));

         /*
            第三轮：针对每个元素的百位进行排序处理
         */
        for (int i = 0; i < arr.length; i++) {
            // 取出每个元素个位的值
            int digit = arr[i] / 100 % 10;
            // 放到对应的桶里
            bucket[digit][bucketCounts[digit]] = arr[i];
            bucketCounts[digit]++;
        }
        /*
            按照桶的顺序（一维数组的下标依次取出数据，放入原来的数组）
            遍历每一个桶，并将桶中的数据放入到原数组中
         */
        idx = 0;
        for (int i = 0; i < bucketCounts.length; i++) {
            // 如果桶中有数据，我们才放入到原数组中
            if (bucketCounts[i] != 0) {
                // 循环该桶即第 i 个桶（第 i 个一维数组）放入
                for (int j = 0; j < bucketCounts[i]; j++) {
                    // 取出元素放入到arr中
                    arr[idx++] = bucket[i][j];
                }
            }
            // 将其置空
            bucketCounts[i] = 0;
        }
//        arr = {3, 14, 53, 214, 542, 748}
        System.out.println("第三轮处理过后的：" + Arrays.toString(arr));

    }

}
