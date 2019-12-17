package com.test.memoryModel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程私有区域和对象所在的堆内存，都是在一个内存中，只是作用不同
 * 线程私有区域包含寄存器和栈，这部分内存空间主要是为线程函数调用服务的，每个线程都有独立的寄存器和栈，栈反应了程序运行位置的变化
 * ，寄存器反应了所执行指令的变化。
 * 堆，是供各个线程共享的运行时内存区域，也供所有类实例和数组对象分配内存的区域
 * cpu 的高速缓冲区 供指令执行时，基本内存变量的存储
 */
public class TestVolatitle1 {
    private volatile int[] ints;
    private static volatile TestC testC = new TestC();

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        testMethod1();
//        testMethod2();
        testMethod3();
    }

    /**
     * 当我们对TestC4中的属性TestC3不加volatile修饰时，会出现当一秒时，停止打印aaa，当2秒时，开始打印aaa，但是开始
     * 时不会再打印aaa，如果增加volatile修饰TestC3，会按照我们的预期，1秒时停止打印aaa，2秒时开始打印aaa
     * 当我们在run方法中增加想TestC5中的代码时，会出现和TestC4中不加volatile修饰的情况
     */
    public static void testMethod3(){
//        TestC4 testC = new TestC4(new TestC3());
//        TestC5 testC = new TestC5(new TestC3());
        TestC6 testC = new TestC6(new TestC3_1(new TestC3()));
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future = executor.submit(testC);

       Future<?> future1 = executor.submit(()->{
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
           testC.stop();
       });
        Future<?> future2 = executor.submit(()->{
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            testC.start();
        });

        try {
            future.get();
            future1.get();
            future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void testMethod2(){
        TestC2 testC2 = new TestC2();
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future = executor.submit(()->{
            for (int i = 0; i < 10000; i++) {
                testC2.inc();
            }
        });

        Future<?> future2 = executor.submit(()->{
            for (int i = 0; i < 10000; i++) {
                testC2.inc();
            }
        });

        Future<?> future3 = executor.submit(()->{
            for (int i = 0; i < 10000; i++) {
                testC2.inc();
            }
        });

        try {
            future.get();
            future2.get();
            future3.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(testC2.a);
    }

    public static void testMethod1(){
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<?> future = executor.submit(()->{
            for (int i = 0; i < 10000; i++) {
                testC.inc();
            }
        });

        Future<?> future2 = executor.submit(()->{
            for (int i = 0; i < 10000; i++) {
                testC.inc();
            }
        });

        try {
            future.get();
            future2.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(testC.a);
    }
}

class TestC {
    public int a = 0;
    public void inc(){
        a ++;
    }
}

class TestC2 {
    public volatile int a = 0;
    public void inc(){
        a++;
    }
}

class TestC3 {
    private boolean flag = true;
    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
class TestC3_1 {
    private TestC3 testC3;

    public TestC3_1(TestC3 testC3) {
        this.testC3 = testC3;
    }

    public TestC3 getTestC3() {
        return testC3;
    }
}


class TestC4 implements Runnable{
    private volatile TestC3 testC3;

    public TestC4(TestC3 testC3) {
        this.testC3 = testC3;
    }

    public TestC3 getTestC3() {
        return testC3;
    }

    @Override
    public void run() {
        while (true){
            if (testC3.isFlag()){
                System.out.println("aaa");
            }
        }

    }

    public void stop(){
        testC3.setFlag(false);
    }

    public void start(){
        testC3.setFlag(true);
    }
}

class TestC5 implements Runnable{
    private volatile TestC3 testC3;

    public TestC5(TestC3 testC3) {
        this.testC3 = testC3;
    }

    public TestC3 getTestC3() {
        return testC3;
    }

    @Override
    public void run() {
        TestC3 testC3 = getTestC3();
        while (true){
            if (testC3.isFlag()){
                System.out.println("aaa");
            }
        }

    }

    public void stop(){
        testC3.setFlag(false);
    }

    public void start(){
        testC3.setFlag(true);
    }
}

class TestC6 implements Runnable{
    private volatile TestC3_1 testC3_1;

    public TestC6(TestC3_1 testC3_1) {
        this.testC3_1 = testC3_1;
    }

    public TestC3_1 getTestC3() {
        return testC3_1;
    }

    @Override
    public void run() {
        while (true){
            if (testC3_1.getTestC3().isFlag()){
                System.out.println("aaa");
            }
        }

    }

    public void stop(){
        testC3_1.getTestC3().setFlag(false);
    }

    public void start(){
        testC3_1.getTestC3().setFlag(true);
    }
}



