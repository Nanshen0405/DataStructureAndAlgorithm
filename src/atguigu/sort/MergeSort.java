package atguigu.sort;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 归并排序（merge-sort）是利用归并的思想实现的排序方法，
 * 该算法采用经典的分治（divide-andconquer）策略，分治法将
 * 问题分(divide)成一些小的问题然后递归求解。而治（conquer）
 * 的阶段则将分的阶段得到的各答案“修补”在一起，即，分而治之。
 * @date 2021/8/4 - 9:30
 */
public class MergeSort {

    public static void main(String[] args) {
//        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2,};
//        int[] arr = {8,4,5,7,1,3,6,2,0,234};

        int num = 80000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            // 生成[0,800000)之间的数
            arr[i] = (int)(Math.random()* num*10);
        }

        long startTime = System.currentTimeMillis();
        mergeSort(arr, 0, arr.length - 1, new int[arr.length]);
        long endTime = System.currentTimeMillis();
//        System.out.println("arr = " + Arrays.toString(arr));
        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");

    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            // 中间索引
            int mid = (left + right) / 2;
            // 左递归
            mergeSort(arr, left, mid, temp);
            // 右递归
            mergeSort(arr, mid + 1, right, temp);
            // 每分解一次就合并一次
            sort(arr, left, mid, right, temp);
        }
    }

    /**
     * 归并排序
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  中转数组
     */
    public static void sort(int[] arr, int left, int mid, int right, int[] temp) {
//        System.out.println("xxxx");
        /*
            初始化 i、j 作为有序序列的索引
            curIdx：指向temp的当前索引
         */
        int i = left;
        int j = mid + 1;
        int curIdx = 0;
        /*
            1、先把左右两边有序的数据按照规则填充到temp数组
            直到左右两边的有序序列，有一边处理完毕为止
         */
        while (i <= mid && j <= right) {
            /*
                2、如果左边的有序序列的当前元素小于等于右边有序序列的当前元素
                那么就将左边的当前元素拷贝到temp数组中，然后两边下标各 +1
             */
            // temp[curIdx++] = arr[i] <= arr[j]? arr[i++]:arr[j++];
            if (arr[i] <= arr[j]) {
                temp[curIdx] = arr[i];
                i++;
            } else {
                temp[curIdx] = arr[j];
                j++;
            }
            curIdx++;
        }

        /*
            3、如果左边的有序序列还有剩余的元素
            就把有剩余数据的一边的数据依次全部填充到temp中
         */
        while (i <= mid) {
            temp[curIdx++] = arr[i++];
        }
        while (j <= right) {
            temp[curIdx++] = arr[j++];
        }
        /*
            4、将temp数组的元素拷贝到arr
            注意不是每次都拷贝所有
         */
        curIdx = 0;
        int tempLeft = left;
//        System.out.println("tempLeft = " + tempLeft + " right = " + right);
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[curIdx++];
        }
    }
}
