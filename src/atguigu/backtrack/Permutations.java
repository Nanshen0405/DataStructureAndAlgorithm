package atguigu.backtrack;

import java.util.LinkedList;
import java.util.List;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 回溯算法
 * 我们在高中的时候就做过排列组合的数学题，
 * 我们也知道 n 个不重复的数，全排列共有 n! 个。
 * PS：为了简单清晰起见，我们这次讨论的全排列问题不包含重复的数字。
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * @date 2021/8/20 - 18:46
 */
public class Permutations {

    static List<List<Integer>> res = new LinkedList<>();

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        permutations
                .permute(new int[]{1,2,3})
                .forEach(System.out::println);
    }

    /*
        回溯算法框架
        result = []
        def backtrack(路径, 选择列表):
            if 满足结束条件:
                result.add(路径)
                return

            for 选择 in 选择列表:
                做选择
                backtrack(路径, 选择列表)
                撤销选择
     */

    /**
     * 主函数，输入一组不重复的数字，返回它们的全排列
     * @param nums 不重复的数字
     * @return 返回的全排列
     */
    List<List<Integer>> permute(int[] nums) {
        // 记录「路径」
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    // 路径：记录在 track 中
    // 选择列表：nums 中不存在于 track 的那些元素
    // 结束条件：nums 中的元素全都在 track 中出现
    private void backtrack(int[] nums, LinkedList<Integer> track) {
        // 触发结束条件
        if (track.size() == nums.length) {
            res.add(new LinkedList<>(track));
            return;
        }

        for (int num : nums) {
            // 排除不合法的选择
            if (track.contains(num))
                continue;
            // 做选择
            track.add(num);
            // 进入下一层决策树
            backtrack(nums, track);
            // 取消选择
            track.removeLast();
        }
    }
}
