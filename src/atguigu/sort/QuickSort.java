package atguigu.sort;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * 快速排序是一种分治的排序算法，他将一个数组分成两个子数组，
 * 将两部分独立地排序。快速排序和归并排序是互补的
 * @date 2021/3/24 - 11:10
 */
public class QuickSort {

    /**
     * 80000数据测试
     * 和快排查看
     */
    private static Boolean FLAG = false;


    public static void main(String[] args) {

        int[] arr;
        if(FLAG) {
            arr = new int[]{-9,78,0,23,-567,70,-1,900,4561};
//            arr = new int[]{-9,78,0,23,-567,70};
//            arr = new int[]{1,2,3,4,0,4,5};
        } else {
            int num = 80000;
            arr = new int[num];
            for (int i = 0; i < arr.length; i++) {
                // 生成[0,800000)之间的数
                arr[i] = (int)(Math.random()* num*10);
            }
        }

        long startTime = System.currentTimeMillis();
        QuickSort.sort(arr,0,arr.length-1);
        long endTime = System.currentTimeMillis();
//        System.out.println("arr = " + Arrays.toString(arr));

        System.out.println("总耗时为" + (endTime - startTime) + "毫秒");
    }

    /**
     * 快速排序
     * @param arr 要排序的数组
     * @param left 左下标
     * @param right 右下标
     */

    public static void sort(int[] arr,int left,int right) {

        // 左右下标
        int i = left;
        int j = right;
        // pivot 枢轴值
        int pivot = arr[(left+right)>>1];
        int temp;


        /*
            while循环的目的是让
            比pivot小的放到左边
            比pivot大的放到右边
         */
        while(i < j) {
           /*
                在枢轴值的两边进行查找
                找到大于等于pivot的才退出
            */
           while(arr[i] < pivot) {
               i++;
           }
           // 找到小于等于pivot的才退出
           while(arr[j] > pivot) {
               j--;
           }
            /*
                如果i >= j说明pivot左右两边的值都是有序的
                左边小于等于pivot，右边大于等于pivot
             */
           if(i >= j) {
                break;
           }
           // 交换位置
           temp = arr[i];
           arr[i] = arr[j];
           arr[j] = temp;


           //  如果交换完后，发现 arr[i] == pivot，j 前移
           if(arr[i] == pivot) {
               j--;
           }
           // 如果交换完后，发现 arr[j] == pivot，i 后移
           if(arr[j] == pivot) {
               i++;
           }

        }

        // 如果i == j 必须 i++,j-- 不加可能会栈溢出
        if(i == j){
            i++;
            j--;
        }

        // 左递归
        if(left < j) {
            sort(arr,left,j);
        }
        // 右递归
        if(right > i) {
            sort(arr,i,right);
        }


    }
}
