package atguigu.design;

import java.io.*;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 单例设计模式
 * @date 2021/7/26 - 21:25
 */
public class SingletonMode {

    public static void main(String[] args) {
//        testSingleThread();
        testMultiThreading();
//        testSerializable();
    }

    public static void testSingleThread() {
        System.out.println("单线程环境test：");
        for (int i = 0; i < 10; i++) {
            System.out.println(Singleton7.getInstance().hashCode());
        }
    }

    public static void testMultiThreading() {
        System.out.println("多线程环境test：");
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(Singleton7.getInstance().hashCode());
            }, "线程" + i).start();
        }
    }

    /**
     * 测试序列化,反序列化会破坏单例模式，需要使用readResolve()方法
     */
    public static void testSerializable() {

        Singleton5 singleton = Singleton5.getInstance();

        File file = new File("Singleton.txt");
        System.out.println("序列化到文件中");
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(singleton);
            System.out.println(singleton.hashCode());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("反序列化到内存中");
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {

            Singleton5 singleton5 = (Singleton5) ois.readObject();

            System.out.println(singleton5.hashCode());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}


/**
 * 1、适用于单线程的单例模式 —— 懒汉式单例
 * 由于要求只能生成一个实例，因此我们必须把构造方法设为私有方法，
 * 以禁止他人创建实例，我们可以定义一个静态的实例，在需要的时候再创建
 */
class Singleton1 {

    private static Singleton1 instance = null;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {

        // 如果为空才创建实例，否则直接返回
        if (instance == null) {
            instance = new Singleton1();
        }
        return instance;
    }

}

/**
 * 2、适用于多线程的环境，但是效率不高 —— 懒汉式单例
 * 解法一中的代码在单线程中可以正常工作，但在多线程环境下
 * 就有问题了，设想一下如果两个线程同时运行到判断instance
 * 是否为null的if语句，并且instance的确没有创建时，那么
 * 两个线程都会创建
 */
class Singleton2 {

    private static Singleton2 instance = null;

    private Singleton2() {

    }

    public static synchronized Singleton2 getInstance() {

        // 如果为空才创建实例，否则直接返回
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}

/**
 * 3、加锁前后进行两次判断(DCL) —— 懒汉式单例
 * 我们只是在实例还没有创建出来之前进行加锁操作，以保证
 * 只有一个线程可以创建出实例，而当实例已经创建完毕之后
 * 就不需要在执行加锁操作了，我们对解法二中的代码进行
 * 改进,volatile保证其有序性
 */
class Singleton3 {

    private volatile static Singleton3 instance = null;

    private Singleton3() {

    }

    public static Singleton3 getInstance() {

        // 双重检查
        if (instance == null) {
            synchronized (Singleton3.class) {
                if (instance == null) {
                    instance = new Singleton3();
                }
            }
        }
        return instance;
    }
}

/**
 * 4、使用前加载实例 —— 饿汉式单例
 */
class Singleton4 {

    private static Singleton4 instance = new Singleton4();

    private Singleton4() {

    }

    public static Singleton4 getInstance() {
        return instance;
    }
}

/**
 * 5、实现按需创建实例 —— 饿汉式单例
 * 使用静态内部类实现
 */

class Singleton5 implements Serializable {

    private Singleton5() {

    }

    public static Singleton5 getInstance() {
        return nested.instance;
    }

    /**
     * 该方法会在反序列时被调用
     */
    private Object readResolve() throws ObjectStreamException {
        System.out.println("调用了readResolve方法！");
        return nested.instance;
    }

    static class nested {
        private static Singleton5 instance = new Singleton5();
    }

}

/**
 * 6、使用静态代码块创建单例 —— 饿汉式单例
 */
class Singleton6 {

    private static Singleton6 instance = null;

    static {
        instance = new Singleton6();
    }

    private Singleton6() {

    }

    public static Singleton6 getInstance() {
        return instance;
    }

}

/**
 * 7、使用枚举类型创建单例 —— 懒汉式单例
 */
class Singleton7 {

    private Singleton7() {

    }

    public static Singleton7 getInstance() {
        return EnumSingleton.singletonFactory.getInstance();
    }

    private enum EnumSingleton {
        singletonFactory;

        private Singleton7 instance;

        // 枚举类的构造方法在类加载时实例化
        private EnumSingleton() {
            instance = new Singleton7();
        }

        public Singleton7 getInstance() {
            return instance;
        }
    }
}
