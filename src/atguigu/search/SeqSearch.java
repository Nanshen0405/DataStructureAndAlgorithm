package atguigu.search;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 线性查找
 * @date 2021/8/5 - 10:52
 */
public class SeqSearch {

    public static void main(String[] args) {
        // 无序数组
        int[] arr = {1, 9, 11, -1, 34, 89};
        int value = -1;
        int index = search(arr, value);

        if(index != -1) {
            System.out.println("找到了，当 value = " + value + "，其下标值为 [" + index + "]");
        } else {
            System.out.println("查无此项");
        }

    }

    /**
     * 这里我们实现线性查找是
     * 找到一个满足条件的值就返回
     * @param arr 要查找的数组
     * @param value 要查找的值
     * @return 找到返回其下标，如果没找到返回 -1
     */
    public static int search(int[] arr, int value) {
        /*
            线性查找是逐一比对，发现有相同值
            就返回下标
         */
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == value) {
                return i;
            }
        }
        return -1;
    }
}
