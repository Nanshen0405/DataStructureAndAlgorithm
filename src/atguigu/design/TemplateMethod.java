package atguigu.design;

/**
 * @author 兰柯万 1733100391@qq.com
 * @version V1.0
 * @Description: 模版方法模式
 * 在父类中定义一个操作的通用具体步骤，将这些步骤的实现细节交给子类来完成。
 * 这样可以使得子类在不更改操作的结构下，可以重新定义该操作的具体步骤。
 * @date 2021/9/6 - 13:06
 */
public class TemplateMethod {

    public static void main(String[] args) {
        /*
            模板方法模式就是在抽象父类中的模板方法中按照
            一定的规则和顺序调用基本方法，
            而这些基本方法在具体的子类中实现。
         */

        new Yang().executeProcess();
//        new Hua().executeProcess();
    }

}

class Hua extends AbstractHuman {

    @Override
    public void wear() {
        System.out.println("穿衣服");
    }

    @Override
    public void wash() {
        System.out.println("洗漱");
    }

    @Override
    public void eatBreakfast() {
        System.out.println("吃早饭");
    }

    @Override
    public void makeUp() {
        System.out.println("化妆");
    }

    @Override
    public void drive() {
        System.out.println("开车");
    }

    @Override
    public void work() {
        System.out.println("上班");
    }

    @Override
    public boolean isMakeUp() {
        return true;
    }

    @Override
    public boolean isDrive() {
        return false;
    }

}

class Yang extends AbstractHuman {

    @Override
    public void wear() {
        System.out.println("穿衣服");
    }

    @Override
    public void wash() {
        System.out.println("洗漱");
    }

    @Override
    public void eatBreakfast() {
        System.out.println("吃早饭");
    }

    @Override
    public void makeUp() {}

    @Override
    public void drive() {
        System.out.println("开车");
    }

    @Override
    public void work() {
        System.out.println("上班");
    }

    @Override
    public boolean isMakeUp() {
        return false;
    }

    @Override
    public boolean isDrive() {
        return true;
    }

}

abstract class AbstractHuman {

    // 穿衣服
    public abstract void wear();

    // 刷牙洗脸
    public abstract void wash();

    // 吃早饭
    public abstract void eatBreakfast();

    //化妆
    public abstract void makeUp();

    // 开车
    public abstract void drive();

    // 工作
    public abstract void work();

    //是否化妆
    public abstract boolean isMakeUp();
    //是否开车
    public abstract boolean isDrive();


    // 起床到工作这一期间动作的执行流程
//    public abstract void executeProcess();

    // 起床到工作这一期间动作的执行流程
    // 父类规定好的步骤顺序，不希望子类去重写
    public final void executeProcess() {
        this.wear();
        this.wash();
        this.eatBreakfast();
        if (isMakeUp()) {
            this.makeUp();
        }
        if (isDrive()) {
            this.drive();
        }
//        this.drive();
        this.work();
    }
}

