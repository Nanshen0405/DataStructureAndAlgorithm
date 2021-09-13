package atguigu.tree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 简单二叉树
 * @date 2021/8/7 - 18:52
 */
public class BinaryTree {

    private TreeNode root;

    public BinaryTree() {
    }

    /**
     * 节点类
     */
    static class TreeNode {

        int number;
        String name;
        TreeNode left;
        TreeNode right;

        public TreeNode(int number, String name) {
            this.number = number;
            this.name = name;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "number=" + number +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println("前序遍历二叉树：");
        PreOrderTraverse(this.root);
    }

    private void PreOrderTraverse(TreeNode root) {

        if(root == null) return;
        System.out.println(root);
        // 遍历左子树
        PreOrderTraverse(root.left);
        // 遍历右子树
        PreOrderTraverse(root.right);
    }

    /**
     * 前序遍历查找
     */
    public TreeNode preOrder(int no) {
        return preOrderSearch(root, no);
    }
    public TreeNode preOrderSearch(TreeNode root, int no) {
        if(root != null &&root.number == no) {
            return root;
        }
        assert root != null;
        if(root.left!= null) {
            return preOrderSearch(root.left,no);
        }
        if(root.right != null) {
            return preOrderSearch(root.right,no);
        }
        return null;
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        System.out.println("中序遍历二叉树：");
        InOrderTraverse(root);
    }

    private void InOrderTraverse(TreeNode root) {

        if(root == null) return;
        // 遍历左子树
        PreOrderTraverse(root.left);
        // 输出数据
        System.out.println(root);
        // 遍历右子树
        PreOrderTraverse(root.right);
    }

    /**
     * 中序遍历查找
     */
    public TreeNode infixOrder(int no) {
        return infixOrderSearch(root,no);
    }

    public TreeNode infixOrderSearch(TreeNode root, int no) {

        if(root.left!= null) {
            return infixOrderSearch(root.left,no);
        }
        if(root.number == no) {
            return root;
        }
        if(root.right != null) {
            return infixOrderSearch(root.right,no);
        }
        return null;
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        System.out.println("后序遍历二叉树：");
        PostOrderTraverse(root);
    }
    private void PostOrderTraverse(TreeNode root) {

        if(root == null) return;
        // 遍历左子树
        PreOrderTraverse(root.left);
        // 遍历右子树
        PreOrderTraverse(root.right);
        // 输出数据
        System.out.println(root);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        System.out.println("层序遍历二叉树：");
        levelOrderTraverse (this.root);
    }

    /**
     *
     * @param root 需要遍历的根节点
     */
    public void levelOrderTraverse (TreeNode root) {

        // 用于存储数据
        Deque<TreeNode> deque = new LinkedList<>();
        // 先将根节点加入到队列尾部
        deque.offer(root);
        // 层序遍历
        while(!deque.isEmpty()) {
            int size = deque.size();
            List<TreeNode> tempList = new ArrayList<>(size);
            // 循环加入到队列中并移除
            for(int i = 0; i < size; i++) {

                TreeNode temp = deque.poll();

                if(temp != null) {
                    tempList.add(temp);
                    if(temp.left != null) {
                        deque.offer(temp.left);
                    }
                    if(temp.right != null) {
                        deque.offer(temp.right);
                    }
                }
            }
            tempList.forEach(System.out::println);
        }
    }
}
