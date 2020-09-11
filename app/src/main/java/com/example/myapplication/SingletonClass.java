package com.example.myapplication;

/**
 * 一个高效且线程安全的单例模式
 * <p>
 * 1.高效  尽量减少锁的时间
 * 2.线程安全  需要锁来保证线程同步
 */
public class SingletonClass {

    private static SingletonClass instance = null;

    //禁止通过new的方式创建实例
    private SingletonClass() {

    }

    public static SingletonClass newInstance() {
        if (instance == null) {
            //可能有多个线程在这里等待锁
            synchronized (SingletonClass.class) {
                //当等待锁的线程进来之后，如果发现实例已经被创建，那么直接返回即可
                if (instance == null) {
                    instance = new SingletonClass();
                }
            }
        }
        return instance;
    }

    /**
     * 该方法的缺陷是，每次创建实例时，都会加锁，而加锁是耗时操作
     * 实际上，我们只要确保，创建实例的过程是线程安全的即可
     * 如果实例已经被创建出来，那么就不需要加锁
     *
     * @return
     */
    public static SingletonClass newInstance2() {
        synchronized (SingletonClass.class) {
            if (instance == null) {
                instance = new SingletonClass();
            }
            return instance;
        }
    }
}
