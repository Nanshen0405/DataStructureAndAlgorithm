package atguigu.hash;

import java.util.Scanner;


/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 实现简单的哈希表
 * @date 2021/8/5 - 13:47
 */
public class HashTableDemo {

    public static void main(String[] args) {

        // 创建哈希表
        HashTable<Integer, String> hashTable = new HashTable<>(7);

        // 创建需要使用到的变量
        String key = "", name;
        int id;

        Scanner in = new Scanner(System.in);
//        BufferedReader buf = new BufferedReader(new InputStreamReader((System.in)));

        while (!key.equals("exit")) {
            show();
            key = in.next();
            switch (key) {
                case "add":
                    System.out.println("请输入雇员id：");
                    id = in.nextInt();
                    System.out.println("请输入雇员姓名：");
                    name = in.next();
                    hashTable.add(id, name);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "get":
                    System.out.println("请输入雇员id以查找：");
                    id = in.nextInt();
                    name = hashTable.get(id);
                    if (name != null) {
                        System.out.println("查到的雇员名称为 " + name);
                    } else {
                        System.out.println("查无此人");
                    }
                    break;
                case "remove":
                    System.out.println("请输入雇员id以移除：");
                    id = in.nextInt();
                    hashTable.remove(id);
                    break;
                case "exit":
                    in.close();
                    break;
                default:
                    System.out.println("您的输入有误，请再试一次");
                    break;
            }
        }

    }

    /**
     * 显示界面
     */
    public static void show() {
        System.out.println("\n\n欢迎使用本系统：");
        System.out.println("add：    添加一个雇员");
        System.out.println("get：    获取一个雇员");
        System.out.println("remove： 移除一个雇员");
        System.out.println("list：   显示所有雇员");
        System.out.println("exit：   退出系统");
    }
}





