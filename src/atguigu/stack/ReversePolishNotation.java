package atguigu.stack;

import java.util.*;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description:
 * 利用后缀表达式实现简单的整数四则运算器：9 + （ 3 - 1 ） * 3 + 10 % 2
 * @date 2021/2/19 - 20:32
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        Count count = new Count();
        Scanner in = new Scanner(System.in);
//        System.out.println("size:" + count.getSuffix().size());
//        String s = "9+(3-1)*3+10/2";

        System.out.println("欢迎使用计算器");
        //test
        do {
            String msg = in.nextLine();
            if(msg.equals("exit")||msg.equals("quit")){
                break;
            }
            int result = count.compute(msg);
            System.out.println(msg + " = " + result);
        }while(true);


    }
}

class Count {

    private Stack<String> op;
    private List<String>suffix;
    private final static int CAPACITY_SUFFIX = 20;

    public Count() {
        this.op = new Stack<>();
        this.suffix = new ArrayList<>(CAPACITY_SUFFIX);
    }

    public int compute(String s) {
        // 非空校验
        if(s==null||s.length()==0||s.equals("")) {
//            System.out.println("请输入有效的四则运算表达式");
            throw new RuntimeException("请输入有效的四则运算表达式");
        }
        /**
         * 1、将中缀表达式转化为后缀表达式
         * 2、将后缀表达式进行运算得出结果
         */
        reverseSuffix(s);
        return realCount();
    }


    private void reverseSuffix(String msg) {

        // 用于保存上一个字符
        char pre = ' ';
        int counter = 0;
        for (int i = 0; i < msg.length(); i++) {
            /**
             * 如果当前字符为数字则输出至后缀表达式
             */
            if(Character.isDigit(msg.charAt(i))) {
                // 查看上一个字符是否为数字
                if(Character.isDigit(pre)) {
                    // 把两位数字相加转为数字再存入
                    Integer sum = (pre-'0')*10+(msg.charAt(i)-'0');
                    suffix.set(counter-1,String.valueOf(sum));
                } else {
                    // 如果只有一位数字就直接输出
                    suffix.add(counter,String.valueOf(msg.charAt(i)));
                    counter++;
                }

            } else {
                // 转成字符串
                if(op.empty()) {
                   op.push(String.valueOf(msg.charAt(i)));
                } else {
                    boolean flag = compare(String.valueOf(msg.charAt(i)),op.peek());
                    // 大于栈顶元素或者是左括号直接进栈
                    if(flag) {
                        op.push(String.valueOf(msg.charAt(i)));
                    } else  {
                        String s = "";
                        // 小于栈顶元素或者右括号直接出栈
                        if(String.valueOf(msg.charAt(i)).equals(")")){
                            // 栈顶依次出栈，直到遇见“（”出栈为止

                            do{
                                if(!op.empty()){
                                    s = op.pop();
                                } else {
                                    throw new RuntimeException("无配对符号“（”");
                                }

                                if(!s.equals("(")){
                                    suffix.add(counter,s);
                                    counter++;
                                }
                            }while(!s.equals("("));
                        } else {
                            /**
                             * 如果当前符号小于栈顶符号
                             * 且不是右括号，就依次出栈直到
                             * 没有小于当前符号的
                             * 然后再进栈
                             */
                            do{
                                if(!op.empty()){
                                    s = op.pop();
                                    suffix.add(counter,s);
                                    counter++;
                                    flag = compare(String.valueOf(msg.charAt(i)),s);
                                } else {
                                    System.out.println("元素已全部出栈");
                                    break;
                                }
                            }while(!flag);

                            op.push(String.valueOf(msg.charAt(i)));
                        }

                    }
                }
            }

            pre = msg.charAt(i);
        }

        /**
         * 因为已经到最后，所以将栈中符号全部出栈
         */
        while(!op.empty()) {
            suffix.add(counter,String.valueOf(op.pop()));
            counter++;
        }

    }

    private boolean compare(String s1,String s2) {

        /**
         * 左括号直接进栈
         * 右括号出栈直到碰见左括号
         */
        switch(s1){
            case"(":
                return true;
            case")":
                return false;
        }

        return compareOp(s1) > compareOp(s2);
    }
    private int compareOp(String s){
        int t;
        switch(s){
            case"+":
                t = 1;
                break;
            case"-":
                t = 2;
                break;
            case"*":
                t = 3;
                break;
            case"/":
                t = 4;
                break;
            case"%":
                t = 5;
                break;
            default:
                t = 0;
        }

        return t;
    }

    private int realCount(){
        // 前置校验
        if(this.suffix.size()==0){
            throw new RuntimeException("未转化中缀表达式");
        }
        // 置空计算栈
        while(!op.empty()){
            op.pop();
        }

        Iterator it = this.suffix.iterator();
        String s = "";
        // 计算结果
        while(it.hasNext()){
            s = (String)it.next();
            /**
             * 1、如果是数字直接进栈
             * 2、如果是符号就出栈两个数
             */
            if(Character.isDigit(s.charAt(0))){
                op.push(s);
            } else {

                /**
                 * 出栈两个数并使用符号来运算
                 * 将结果保存到栈中
                 */
                int a = 0;
                int b = 0;
                if(Character.isDigit(op.peek().charAt(0))){
                    a = Integer.parseInt(op.pop());
                }
                if(Character.isDigit(op.peek().charAt(0))){
                    b = Integer.parseInt(op.pop());
                }

                int result = 0;
                switch (s){
                    case"+":
                        result = b + a;
                        break;
                    case"-":
                        result = b - a;
                        break;
                    case"*":
                        result = b * a;
                        break;
                    case"/":
                        result = b / a;
                        break;
                    case"%":
                        result = b % a;
                        break;
                }
                op.push(String.valueOf(result));
            }
        }
        // 清空
        suffix.clear();

        if(!op.empty()) {
            return Integer.parseInt(op.pop());
        } else {
            System.out.println("计算出错，请检查代码");
        }
        return -1;
    }



    public Stack<String> getOp() {
        return op;
    }

    public List<String> getSuffix() {
        return suffix;
    }
}
