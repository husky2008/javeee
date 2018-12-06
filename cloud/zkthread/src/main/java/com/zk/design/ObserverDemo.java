package com.zk.design;/**
 * Created by husky on 2018/11/15.
 */

import java.util.Vector;

/**
 * 观察者模式
 * 类似于发布订阅模式
 * @author zhangkai
 * @create 2018-11-15 19:41
 **/
public class ObserverDemo implements OberverI {

    private String name;

    public ObserverDemo(String name) {
        this.name = name;
    }

    @Override
    public void update(Object data) {
        System.out.println(this.name + "--我接受到了通知,接收的数据是：" + data);
    }


    public static void main(String[] args) {
        ObserverDemo 观察者1 = new ObserverDemo("观察者1");
        ObserverDemo 观察者2 = new ObserverDemo("观察者2");
        ObserverDemo 观察者3 = new ObserverDemo("观察者3");
        ObserverDemo 观察者4 = new ObserverDemo("观察者4");

        Vector<OberverI> observers = new Vector<>();
        observers.add(观察者1);
        observers.add(观察者2);
        observers.add(观察者3);
        observers.add(观察者4);
        SubjectImpl subject = new SubjectImpl(observers);
        subject.wake("我更新了内容");


    }


}


//抽象的观察者接口
interface OberverI {
    /**
     * 接受通知的方法
     */
    void update(Object data);
}

/**
 * 主体接口
 */
interface Subject {

    /**
     * 添加一个观察者
     *
     * @param oberverI
     */
    void add(OberverI oberverI);

    /**
     * 移除一个观察者
     *
     * @param oberverI
     */
    void delete(OberverI oberverI);

    /**
     * 唤醒观察者并传递相应的数据
     *
     * @param data
     */
    void wake(Object data);
}


/**
 * 具体的主题
 */

class SubjectImpl implements Subject {


    //观察者列表
    private Vector<OberverI> obervers;

    public SubjectImpl(Vector<OberverI> obervers) {
        this.obervers = obervers;
    }

    @Override
    public void add(OberverI oberverI) {
        obervers.add(oberverI);
    }

    @Override
    public void delete(OberverI oberverI) {
        obervers.remove(oberverI);
    }

    @Override
    public void wake(Object data) {
        if(obervers != null){
            //遍历观察者列表,进行通知
            for(OberverI observer:obervers){
                observer.update(data);
            }
        }

    }
}






