package atguigu.test;

import java.util.ArrayList;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/2/23 - 20:27
 */
public class PowerCode {

    volatile static int i = 10;

    public static void main(String[] args) {

        // 请说出下面这段代码的运行结果
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        list.removeIf("1"::equals);

        System.out.println(list);
        System.out.println("i = " + i);

        int num = findRepeatNumber(new int[]{2, 3, 1, 0, 2, 5, 3});
        System.out.println("数组中重复的数字为 = " + num);
    }

    public static int findRepeatNumber(int[] nums) {

        // 1、循环查看是否有不出现在对应位置的数字
        int i;
        for (i = 0; i < nums.length; i++) {
            /**
             * 2、如果当前值不等于当前下标
             * 说明不在正确的位置上
             */
            while(nums[i] != i) {
                // 3、如果在某个位置已经有值了，说明重复了，直接返回即可
                if(nums[i] == nums[nums[i]]) {
                    return nums[i];
                }
                // 交换 nums[i] 和 nums[nums[i]]
                int temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        return -1;
    }

}

