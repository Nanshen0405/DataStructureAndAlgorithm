package atguigu.sort;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 希尔排序
 * @date 2021/3/23 - 11:26
 */
public class ShellSort {

    public static void main(String[] args) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        int num = 80000;
        int[] arr = new int[num];
        for (int i = 0; i < arr.length; i++) {
            // 生成[0,800000)之间的数
            arr[i] = (int)(Math.random()* num*10);
        }

        long startTime = System.currentTimeMillis();
        ShellSort.sort(arr);
        long endTime = System.currentTimeMillis();
        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");
    }

    public static void sort(int[] arr) {
//        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        /**
         * 希尔排序
         * 将10个数据分成了5组
         */
        int temp, i, j;
        int increment = arr.length >> 1;

        do {
//            System.out.println("increment: " + increment);
            for (i = increment; i < arr.length; i++) {

                // 如果不大于或者小于，可以直接换下一组进行比较
                if (arr[i] < arr[i - increment]) {

                    temp = arr[i];
                    // 遍历各组中所有的元素，每组两个元素，步长increment
                    for (j = i - increment; j >= 0 && temp < arr[j]; j -= increment) {
                        arr[j + increment] = arr[j];
                    }
                    // 如果找到了插入的位置直接放进去
                    if ((j + increment) != i) {
                        arr[j + increment] = temp;
                    }
//                    System.out.println(Arrays.toString(arr));
                }
            }
            increment >>= 1;
        } while (increment > 0);

    }
}
