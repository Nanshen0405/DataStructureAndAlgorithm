package atguigu.sparsearray;

import java.io.*;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * @date 2021/1/19 - 13:18
 */
public class SparseArray {

    public static void main(String[] args) {

        // 创建一个原始的二维数组 11 * 11
        // 0 表示没有棋子	1 表示黑子	2 表示 蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;

        // 1、获取稀疏数组
        System.out.println("获取稀疏数组");
        int[][] sparseArray = getSparseArray(chessArr1);

//        // 2、将稀疏数组转置为原始二维数组
//        System.out.println("将稀疏数组转置为原始二维数组");
//        getChessArray(sparseArray);

//        // 将稀疏数组转存入文件中
        System.out.println("将稀疏数组写入到文件中");
        File file = new File("F:\\map.dat");
        WriteToFile(sparseArray,file);
        System.out.println("将稀疏数组写入到文件中完成");

        System.out.println("将稀疏数组从文件中读取出来转化为原始二维数组");
        getChessArrayFromFile(file);

    }
    /**
     *
     * 二维数组转稀疏数组的思路
     * 1、遍历二维数组，得到有效数据的个数sum
     * 2、根据sum就可以创建稀疏数组sparseArray int[sum+1][3];
     * 3、将二维数组的有效数据存入到稀疏数组
     */
    public static int[][] getSparseArray(int[][] chessArr) {
        // 输出原始的二维数组
        System.out.println("原始的二维数组");

        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        // 将二维数组转稀疏数组的思路
        // 1、先遍历二维数组 得到非零数据的个数
        int sum =  0;

        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);

        // 2、创建对应的稀疏数组
        int sparseArray[][] = new int[sum+1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = chessArr.length;
        sparseArray[0][1] = chessArr[0].length;
        sparseArray[0][2] = sum;

        // 遍历二维数组，将非零数据放到稀疏数组中
        int count = 0;  // 用于记录是第几个非零数据
        for (int i = 0; i < chessArr.length; i++) {
            for (int j = 0; j < chessArr[i].length; j++) {
                if(chessArr[i][j] != 0) {
                    count++;
                    // 设置行列数据
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr[i][j];
                }
            }
        }
        // 输出稀疏数组的形式
        System.out.println();
        System.out.println("得到稀疏数组为------");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
        }
        System.out.println();
        
        return sparseArray;
    }
    /**
     *
     * 稀疏数组转原始二维数组的思路
     * 1、先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的chessArr = int[11][12]
     * 2、再读取稀疏数组后几行的数据，并赋值给原始的二维数组即可
     */
    public static int[][] getChessArray(int[][] sparseArray) {
        //
        int [][] chessArr = new int[sparseArray[0][0]][sparseArray[0][1]];
        // 遍历稀疏数组
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }
        // 输出原始的二维数组
        System.out.println("原始的二维数组");

        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

        return chessArr;
    }

    /**
     *
     * @param sparseArray
     * 将稀疏数组写入到文件中
     */
    public static void WriteToFile(int[][] sparseArray, File file){
        // 创建对应的文件输出流
        OutputStreamWriter osw = null;
        BufferedWriter bw = null;

        try {
            // 1、判断文件
            if(file.exists() && file.isFile()){
                // 使用缓存机制来往文件内写入数据
                osw = new OutputStreamWriter(new FileOutputStream(file),"utf-8");
                bw = new BufferedWriter(osw);
//                bw.write("Test");
                for (int i = 0; i < sparseArray.length; i++) {
                    String msg = String.format("%d\t%d\t%d\n",sparseArray[i][0],sparseArray[i][1],sparseArray[i][2]);
                    bw.write(msg,0,msg.length());
                    System.out.println("正在写入");
                }

            } else {
                System.out.println("无法打开文件");
            }

            if(bw != null){
                bw.close();
            }
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @return ChessArray
     * // 从文件中的稀疏数组获取原数组
     */
    public static int[][] getChessArrayFromFile(File file) {
        InputStreamReader isr = null;
        BufferedReader br = null;

        try{
            if(file.exists() && file.isFile()){
                // 使用缓存机制来往文件内读取数据
                isr = new InputStreamReader(new FileInputStream(file));
                br = new BufferedReader(isr);
                // 从文件中获取原数组长度设置原数组长度
                String[] s = br.readLine().split("\\s+");
                int [][] chessArr = new int[Integer.parseInt(s[0])][Integer.parseInt(s[1])];
//                int sum = Integer.parseInt(s[2]);
                // 从文件中读取数据放到数组中
                String msg = null;
                while((msg = br.readLine())!=null){
                    String[] str = msg.split("\\s+");
                    int i = Integer.parseInt(str[0]);
                    int j = Integer.parseInt(str[1]);
                    int val = Integer.parseInt(str[2]);
                    chessArr[i][j] = val;
                }

                System.out.println("输出一遍原始的二维数组");

                for (int[] row : chessArr) {
                    for (int data : row) {
                        System.out.printf("%d\t",data);
                    }
                    System.out.println();
                }

                if (chessArr != null) {
                    return chessArr;
                }



            } else {
                System.out.println("无法打开文件");
            }

            if(br != null){
                br.close();
            }

        }catch(IOException ioe){
            ioe.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
