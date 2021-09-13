package atguigu.hash;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 实现简单的哈希表
 * @date 2021/8/5 - 14:42
 */
public class HashTable<K, V> {

    /**
     * 哈希表默认长度
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /**
     * 实际的哈希表
     */
    transient Node<K, V>[] table;
    /**
     * 哈希表大小
     */
    transient int size;

    public HashTable() {
        this(DEFAULT_INITIAL_CAPACITY);
    }

    /**
     * 初始化哈希表
     *
     * @param size 哈希表容量
     */
    @SuppressWarnings({"unchecked"})
    public HashTable(int size) {
        Node<K, V>[] newTab;
        newTab = (Node<K, V>[]) new Node[size];
        table = newTab;
        this.size = size;
    }

    public void add(K key, V value) {
        addVal(hash(key), key, value);
    }

    /**
     * 哈希函数
     *
     * @param key key
     * @return hash值
     */
    private int hash(Object key) {
        return (key == null) ? 0 : key.hashCode() % size;
    }

    private void addVal(int hash, K key, V value) {
        /*
            头指针，执行第一个Node，
            因此我们链表的head
            是指向第一个Node的
        */
        if (table[hash] == null) {
            table[hash] = new Node<>(hash, key, value, null);
        } else {
            /*
                如果不是第一个雇员，则使用辅助
                指针，定位到链表尾部
            */
            Node<K, V> temp = table[hash];
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node<>(hash, key, value, null);
        }

    }

    /**
     * 根据id查找
     *
     * @param key 输入的id
     * @return 返回姓名
     */
    public V get(Object key) {
        int hash = hash(key);
        if (table[hash] == null) {
            System.out.println("当前链表节点为空");
            return null;
        }
        Node<K, V> node = table[hash];
        while (node != null) {
            if (hash == node.hash && (node.key != null && node.key.equals(key))) {
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 根据key删除一个雇员
     *
     * @param key 要删除的id
     */
    public void remove(Object key) {
        int hash = hash(key);
        if (table[hash] == null) {
            System.out.println("当前链表节点为空");
            return;
        }
        Node<K, V> node = table[hash];
        if (hash == node.hash && (node.key != null && node.key.equals(key))) {
            table[hash] = table[hash].next;
            System.out.println("成功移除一位雇员");
        } else {
            while (node.next != null) {

                Node<K, V> temp = node.next;
                if (hash == temp.hash && (temp.key != null && temp.key.equals(key))) {
                    node.next = temp.next;
                    System.out.println("成功移除一位雇员");
                    return;
                }
                node = node.next;
            }
        }
        System.out.println("查无此人");
    }

    /**
     * 遍历哈希表add
     */
    public void list() {

        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            System.out.println("第" + (i + 1) + "条链表");
            if (node == null) {
                System.out.println("为空");
            } else {
                while (node != null) {
                    System.out.println(node);
                    node = node.next;
                }
            }
        }

    }

    // 节点类
    static class Node<K, V> {

        final int hash;
        final K key;
        /**
         * 指向下一个，默认为空
         */
        public Node<K, V> next;
        V value;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
//            return key + "=" + value;
            return "id = " + key + ", name = " + value;
        }
    }

}