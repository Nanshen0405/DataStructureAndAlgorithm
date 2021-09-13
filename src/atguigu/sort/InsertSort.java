package atguigu.sort;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 实现插入排序
 * 直接插入排序（Straight Insertion Sort）的基本操作是将一个记录
 * 插入到已经排序好的有序表中，从而得到一个新的、记录数增1的有序表。
 * @date 2021/3/22 - 12:17
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {10,9,1,5,8,3,7,4,6,2,};
//        int[] arr = {8,9,1,7,2,3,5,4,6,0};
//        int[] arr = {101, 34, 119, 1,-1,89};
        int num = 80000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(Math.random()* num*10);// 生成[0,800000)之间的数
        }
        long startTime = System.currentTimeMillis();
        InsertSort.sort(arr);
        long endTime = System.currentTimeMillis();
//        System.out.println(Arrays.toString(arr));
        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");

    }

    public static void sort(int[] arr) {
        /**
         * 使用推导的方式来理解
         */
        int temp = 0, i, j;
        for (i = 1; i < arr.length; i++) {
            temp = arr[i];
//            System.out.println("第" + i + "次排序");
            for (j = i - 1; j >= 0 && temp < arr[j]; j--) {
                arr[j + 1] = arr[j];
            }
            if ((j + 1) != i) {
                arr[j + 1] = temp;
            }
//            System.out.println(Arrays.toString(arr));
        }


    }
}
