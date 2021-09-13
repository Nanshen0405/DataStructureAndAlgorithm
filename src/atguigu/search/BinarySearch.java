package atguigu.search;

import java.util.ArrayList;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/8/5 - 12:19
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1000, 1000, 1000, 1234};

        int index = search(arr,0,arr.length - 1, 1000);
        System.out.println("index = " + index);

        ArrayList<Integer> list = searchList(arr, 0, arr.length - 1, 1000);
        list.forEach(item -> System.out.print(item + " "));
    }

    /**
     * 二分查找
     * @param arr 要查找的数组
     * @param left  左索引
     * @param right 右索引
     * @param value 要查找的值
     * @return  如果查找到了返回其下标，反之返回 -1
     */
    public static int search(int[] arr,int left, int right, int value) {

        if(left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        // 右递归
        if(value > midVal) {
            return search(arr, mid + 1, right, value);
        } else if(value < midVal) {
            return search(arr,left,mid - 1,value);
        } else {
            return mid;
        }
    }

    /*
        课后思考题： {1,8, 10, 89, 1000, 1000，1234}
        当一个有序数组中，有多个相同的数值时，如何将所有的数值都查找到，比如这里的 1000.
        思路分析
        1. 在找到 mid 索引值之后，不要立刻返回
        2. 向 mid 索引值的左边扫描，将所有满足条件的元素下标，加入到集合中
        3. 向 mid 索引值的右边扫描，将所有满足条件的元素下标，加入到集合中
     */
    public static ArrayList<Integer> searchList(int[] arr, int left, int right, int value) {

        if(left > right) {
            return new ArrayList<>();
        }

        int mid = (left + right) / 2;
        int midVal = arr[mid];

        // 右递归
        if(value > midVal) {
            return searchList(arr, mid + 1, right, value);
        } else if(value < midVal) {
            return searchList(arr,left,mid - 1,value);
        } else {
            /*
                思路分析
                1. 在找到 mid 索引值之后，不要立刻返回
                2. 向 mid 索引值的左边扫描，将所有满足条件的元素下标，加入到集合中
                3. 向 mid 索引值的右边扫描，将所有满足条件的元素下标，加入到集合中
             */
            ArrayList<Integer> list = new ArrayList<>();

            // 向左扫描
            int temp = mid - 1;
            while (!(temp < 0 || arr[temp] != value)) {
                   list.add(temp);
                   temp--;
            }
            list.add(mid);
            // 向右扫描
            temp = mid + 1;
            while(!(temp > arr.length -1 || arr[temp] != value)) {
                list.add(temp);
                temp++;
            }
            return list;
        }
    }

}
