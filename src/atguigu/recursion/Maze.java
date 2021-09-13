package atguigu.recursion;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/3/2 - 8:50
 */
public class Maze {

    public static void main(String[] args) {

        /**
         * 八种策略：
         * 上左下右、上右下左
         * 右下左上、右上左下
         * 下左上右、下右上左
         * 左上右下、左下右上
         */
        int[] strategy = new int[8];
        int min = 0;
        for (int i = 0; i < strategy.length; i++) {
            int [][]map = new int[8][7];
            initialMap(map);

            checkWay(map,1,1,i);

            for (int a = 0; a < map.length; a++) {
                for (int b = 0; b < map[a].length; b++) {
                    System.out.print(map[a][b]+"\t");
                }
                System.out.println();
            }

            // 查看地图里的map的2的数量
            for (int x = 0; x < map.length; x++) {
                for (int y = 0; y < map[x].length; y++) {
                    if(map[x][y] == 2) {
                        strategy[i]++;
                    }
                }
            }
            System.out.println("strategy[i] = " + strategy[i]);
        }

    }

    public static void initialMap(int[][] map) {
        // 创建地图
        /**
         * ********
         * 上下初始化为墙
         * ********
         */
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        /**
         * ******
         * *    *
         * *    *
         * ******
         * 左右初始化为墙
         */
        for (int i = 0; i < map[0].length; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;
//        map[1][2] = 1;
//        map[2][2] = 1;

        System.out.println("开始查找路径");
    }

    /**
     * 约定：
     *   map[6][5] 为出口，
     *   如果map[i][j] ==
     *   0：未走过 1：墙 2：通路 3：已走过但不通
     * 八种策略：走不通就回溯
     * 上左下右、上右下左
     * 右下左上、右上左下
     * 下左上右、下右上左
     * 左上右下、左下右上
     * @param map 地图
     * @param i 初始位置
     * @param j
     * @param flag 使用的策略
     * @return 如果找到出口返回true，否则返回false
     */
    public static boolean checkWay(int[][]map,int i,int j,int flag){
        // 2：通路已找到
        if(map[6][5] == 2) {
            return true;
        } else {

            // 0：未走过
            if(map[i][j] == 0) {
                // 假定走得通
                map[i][j] = 2;
                switch(flag){
                    // 上左下右
                    case 0:
                        // UP
                        if(checkWay(map,i-1,j,flag)) {
                            return true;
                            // Left
                        } else if(checkWay(map,i,j-1,flag)) {
                            return true;
                            // Down
                        } else if(checkWay(map,i+1,j,flag)){
                            return true;
                            // Right
                        } else if(checkWay(map,i,j+1,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                    case 1:
                        // 上右下左
                        // UP
                        if(checkWay(map,i-1,j,flag)) {
                            return true;
                            // Right
                        } else if(checkWay(map,i,j+1,flag)) {
                            return true;
                            // Down
                        } else if(checkWay(map,i+1,j,flag)){
                            return true;
                            // Left
                        } else if(checkWay(map,i,j-1,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                    case 2:
                        // 右下左上
                        // Right
                        if(checkWay(map,i,j+1,flag)) {
                            return true;
                            // Down
                        } else if(checkWay(map,i+1,j,flag)) {
                            return true;
                            // Left
                        } else if(checkWay(map,i,j-1,flag)){
                            return true;
                            // UP
                        } else if(checkWay(map,i-1,j,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                    case 3:
                        // 右上左下
                        // Right
                        if(checkWay(map,i,j+1,flag)) {
                            return true;
                            // UP
                        } else if(checkWay(map,i-1,j,flag)) {
                            return true;
                            // Left
                        } else if(checkWay(map,i,j-1,flag)){
                            return true;
                            // Down
                        } else if(checkWay(map,i+1,j,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                    case 4:
                        // 下左上右
                        // Down
                        if(checkWay(map,i+1,j,flag)) {
                            return true;
                            // Left
                        } else if(checkWay(map,i,j-1,flag)) {
                            return true;
                            // UP
                        } else if(checkWay(map,i-1,j,flag)){
                            return true;
                            // Right
                        } else if(checkWay(map,i,j+1,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                    case 5:
                        // 下右上左
                        // Down
                        if(checkWay(map,i+1,j,flag)) {
                            return true;
                            // Right
                        } else if(checkWay(map,i,j+1,flag)) {
                            return true;
                            // UP
                        } else if(checkWay(map,i-1,j,flag)){
                            return true;
                            // Left
                        } else if(checkWay(map,i,j-1,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                    case 6:
                        // 左上右下
                        // Left
                        if(checkWay(map,i,j-1,flag)) {
                            return true;
                            // UP
                        } else if(checkWay(map,i-1,j,flag)) {
                            return true;
                            // Right
                        } else if(checkWay(map,i,j+1,flag)){
                            return true;
                            // Down
                        } else if(checkWay(map,i+1,j,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                    case 7:
                        // 左下右上
                        // Left
                        if(checkWay(map,i,j-1,flag)) {
                            return true;
                            // Down
                        } else if(checkWay(map,i+1,j,flag)) {
                            return true;
                            // Right
                        } else if(checkWay(map,i,j+1,flag)){
                            return true;
                            // UP
                        } else if(checkWay(map,i-1,j,flag)) {
                            return true;
                        } else {
                            // 说明该点走不通，为死路
                            map[i][j] = 3;
                            return false;
                        }
                }

            } else { // 可能是 1, 2, 3
                return false;
            }
        }
        return false;
    }


    /**
     * 约定：
     *      * map[6][5] 为出口，
     *      * 如果map[i][j] ==
     *      * 0：未走过 1：墙 2：通路 3：已走过但不通
     *      * 策略： 上 -> 左 -> 下 -> 右 ，如果走不通就回溯
     * @param map 地图
     * @param i 初始位置
     * @param j
     * @return
     */
    public static boolean setWay(int[][]map,int i, int j) {


        // 2：通路已找到
        if(map[6][5] == 2) {
            return true;
        } else {

            // 0：未走过
            if(map[i][j] == 0) {
                // 假定走得通
                map[i][j] = 2;
                // 往上走
                if(setWay(map,i-1,j)) {
                    return true;
                    // 往左走
                } else if(setWay(map,i,j-1)) {
                    return true;
                    // 向下走
                } else if(setWay(map,i+1,j)){
                    return true;
                    // 向右走
                } else if(setWay(map,i,j+1)) {
                    return true;
                } else {
                    // 说明该点走不通，为死路
                    map[i][j] = 3;
                    return false;
                }

            } else { // 可能是 1, 2, 3
                return false;
            }
        }
    }

}
