package atguigu.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * 把一个数组的值存入二叉树中，然后进行3种方式的遍历
 * @date 2021/4/26 - 18:02
 */
public class BinTreeTraverse {
    /**
     * root：
     * 二叉树的根节点
     * binTreeNodeList：
     * 二叉树的层序遍历列表
     */
    private BinTreeNode root;
    private List<BinTreeNode> binTreeNodeList;
    private int[] data;

    public BinTreeTraverse() {
        this.root = null;
        this.data = new int[]{1,2,3,4,5,6,7,8,9};
        this.binTreeNodeList = new ArrayList<>(data.length);
    }
    public BinTreeTraverse(int[] data) {
        this.root = null;
        this.data = data;
        this.binTreeNodeList = new ArrayList<>(data.length);
    }

    public BinTreeNode getRoot() {
        return root;
    }

    public List<BinTreeNode> getBinTreeNodeList() {
        return binTreeNodeList;
    }

    /**
     * 内部节点类
     */
    private static class BinTreeNode {
        BinTreeNode lChild;
        BinTreeNode rChild;
        int data;

        public BinTreeNode() {
        }

        BinTreeNode(int data) {
            this.lChild = null;
            this.rChild = null;
            this.data = data;
        }

        public BinTreeNode(BinTreeNode lChild, BinTreeNode rChild, int data) {
            this.lChild = lChild;
            this.rChild = rChild;
            this.data = data;
        }

        @Override
        public String toString() {
            return "BinTreeNode{" +
                    "data=" + data +
                    '}';
        }


    }

    /**
     * 层序遍历创建二叉树
     */
    public void createBinTree() {

        int len = this.data.length,i;
        // 将数组中的数据转换为列表
        for (i = 0; i < len; i++) {
            binTreeNodeList.add(new BinTreeNode(this.data[i]));
        }
        // 保存根节点
        this.root = binTreeNodeList.get(0);

        BinTreeNode temp = null;
        len = this.data.length>>1;
        // 按照父节点与孩子节点的数字关系层寻遍历建立二叉树
        for (i = 0; i < len; i++) {
            // 从列表中获取当前父节点
            temp =  binTreeNodeList.get(i);
            // 链接它的左孩子
            temp.lChild = binTreeNodeList.get((i<<1)+1);
            // 链接它的右孩子:当最后一个时，可能是没有右孩子的
            if((i<len-1) || (data.length%2!=0)) {
                temp.rChild = binTreeNodeList.get((i<<1)+2);
            }
        }
    }

    /**
     * 先序遍历二叉树
     */
    public void preOrderTraverse(BinTreeNode root) {
       if(root==null){
           return;
       }
        System.out.print(root.data + " ");
        preOrderTraverse(root.lChild);
        preOrderTraverse(root.rChild);
    }
    /**
     * 中序遍历二叉树
     */
    public void inOrderTraverse(BinTreeNode root) {
        if(root==null){
            return;
        }
        preOrderTraverse(root.lChild);
        System.out.print(root.data + " ");
        preOrderTraverse(root.rChild);
    }
    /**
     * 后序遍历二叉树
     */
    public void postOrderTraverse(BinTreeNode root) {
        if(root==null){
            return;
        }
        preOrderTraverse(root.lChild);
        preOrderTraverse(root.rChild);
        System.out.print(root.data + " ");
    }




    public static void main(String[] args) {
        // 创建二叉树
        BinTreeTraverse binTree = new BinTreeTraverse();
        binTree.createBinTree();
        // 获取头结点
        BinTreeNode root = binTree.getRoot();
        // 三种不同的遍历方法
        System.out.println("\n先序遍历：");
        binTree.preOrderTraverse(root);
        System.out.println("\n中序遍历：");
        binTree.inOrderTraverse(root);
        System.out.println("\n后序遍历：");
        binTree.postOrderTraverse(root);
    }

    @Override
    public String toString() {
        return "BinTreeTraverse{" +
                "root=" + root +
                '}';
    }
}
