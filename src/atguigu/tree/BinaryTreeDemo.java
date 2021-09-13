package atguigu.tree;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/8/7 - 18:51
 */
public class BinaryTreeDemo {

    public static void main(String[] args) {
        // 1. 创建一颗空树
        BinaryTree binaryTree = new BinaryTree();
        // 2. 创建需要的节点
        BinaryTree.TreeNode root = new BinaryTree.TreeNode(1, "及时雨宋江");
        BinaryTree.TreeNode treeNode2 = new BinaryTree.TreeNode(2, "智多星吴用");
        BinaryTree.TreeNode treeNode3 = new BinaryTree.TreeNode(3, "玉麒麟卢俊义");
        BinaryTree.TreeNode treeNode4 = new BinaryTree.TreeNode(4, "豹子头林冲");
        BinaryTree.TreeNode treeNode5 = new BinaryTree.TreeNode(5, "鼓上蚤时迁");
        // 3. 手动创建二叉树，后面使用递归的方式创建
        root.left = treeNode2;
        root.right = treeNode3;
        treeNode3.right = treeNode4;
        treeNode3.left = treeNode5;
        binaryTree.setRoot(root);
        // 4. 测试
        binaryTree.preOrder();
        binaryTree.infixOrder();
        binaryTree.postOrder();
        binaryTree.levelOrder();
    }
}
