package com.test.memoryModel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * volatile 的重排序规则
 * 当第一个操作为普通操作的读或者写，第二个操作为volatile操作的读时，允许处理器重排序
 * 当第一个操作为普通操作的读或者写，第二个操作为volatile操作的写时，不允许处理器重排序
 * 当第一个操作为volatile操作的读，第二个操作为volatile操作的读或者写，或者普通操作的读或者写，不允许处理器重排序
 * 当第一个操作为volatile操作的写，第二个操作为volatile操作的读或者写，不允许处理器重排序
 * 当第一个操作为volatile操作的写，第二个操作为普通操作的读或者写，允许处理器重排序
 *
 * 根据上面的规则可以看出
 * 对于instance = new TestV2();的初始化的三步
 * 1 分配内存空间
 * 2 初始化对象
 * 3 将对象指向刚分配的内存空间
 * 第2步为普通变量的读或者写 第三步为volatile操作的写 说明第2和第3步是不能出现重排序的
 *
 * 下面就是对于锁的理解
 * 当在被锁的区域内一个线程正在执行时，2和3步骤进行了重排序，先对instance赋值了，这是正好有另外一个线程
 * 获取实例对象值，正好有值了直接返回，但是还没有初始化完毕，这时会出现错误
 *
 */
public class TestVolatitle2 {
    public static void main(String[] args) {
        testMethod1();
    }

    public static void testMethod1(){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future = executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });

        Future<?> future2 = executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });
        Future<?> future3 = executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });

        Future<?> future4 = executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });
        executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });

        executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });
        executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });

        executor.submit(()->{
            TestV testV = TestV.getInstance();
            System.out.println(testV);
        });

        try {
            future.get();
            future2.get();
            future3.get();
            future4.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

/**
 * 当两个线程同时访问getInstance方法时，在cpu的高速缓冲区内的instance都为null这时会生成两个实例
 * 直接加锁 这种方式可以解决问题，当时对性能的影响太大，并且当对象实例化之后，每次访问都会加锁，其实是没有必要的，
 * 只要保证第一次的时候，同时几个线程访问造成多实例的情况避免后，后面就不会有这种情况，
 */
class TestV{
    private static TestV instance;

    public TestV() {
    }

    public synchronized static TestV getInstance(){
        if (instance == null){
            instance = new TestV();
        }
        return instance;
    }
}

/**
 * instance = new TestV1();会经历三个过程
 * 1 分配内存空间
 * 2 初始化对象
 * 3 将对象指向刚分配的内存空间
 * 当时编译器为了性能的问题，可能会对2和3进行重排序，可能先执行3再执行2
 *
 */
class TestV1{
    private static TestV1 instance;

    public TestV1() {
    }

    public static TestV1 getInstance(){
        if (instance == null){
            synchronized (TestV1.class){
                if (instance == null){
                    instance = new TestV1();
                }
            }
        }
        return instance;
    }
}

/**
 * 双重检查锁
 */
class TestV2{
    private static volatile TestV2 instance;

    public TestV2() {
    }

    public static TestV2 getInstance(){
        if (instance == null){
            synchronized (TestV2.class){
                if (instance == null){
                    instance = new TestV2();
                }
            }
        }
        return instance;
    }
}
