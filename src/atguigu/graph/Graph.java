package atguigu.graph;

import java.util.ArrayList;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/9/13 - 17:34
 */
public class Graph {

    /**
     * 存储顶点集合
     */
    private ArrayList<String> vertex;
    /**
     * 存储图对应的邻接矩阵
     */
    private int[][] edges;
    /**
     * 表示边的数目
     */
    private int numOfEdges;




    public static void main(String[] args) {

    }

    /**
     * 单参构造
     * @param n 要构造的节点数量
     */
    public Graph(int n) {
        // 初始化矩阵和定点集合
        edges = new int[n][n];
        vertex = new ArrayList<String>(n);
        numOfEdges = 0;
    }

    //    ****  图   常用方法    ****

    /**
     * 返回结点个数
     * @return 结点个数
     */
    public int getNumOfVertex() {
        return vertex.size();
    }

    /**
     * 返回边的数量
     * @return 边的数量
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回结点i对应的数据
     * @param i 结点
     * @return 返回其数据
     */
    public String getValueByIndex(int i) {
        return vertex.get(i);
    }


    /**
     * 插入节点
     * @param vertex 要插入的节点
     */
    public void insertVertex(String vertex) {
        this.vertex.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示点的下标，即第几个顶点
     * @param v2 第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        this.numOfEdges++;

    }

}
