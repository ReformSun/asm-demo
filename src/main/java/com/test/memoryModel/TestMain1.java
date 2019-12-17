package com.test.memoryModel;

/**
 * 在java虚拟机中 堆内存是在线程之间共享的 也可以叫做共享变量
 *
 * java内存模型的理解
 * java中其实以线程去理解能够更好的理解
 *
 * 首先理解java代码
 * java代码在执行的时候会首先转化成java字节码
 * java字节码是转化成机器能够认识的机器码的中间阶段
 * 其实当java代码本编写完成后，代码会被编译成.class 文件 .class文件中就是存储字节码的文件
 * （其实编写代码其实相当于在一个平的纸上写东西，但是代码在执行的时候是有空间的，写代码时我们也要有空间感，
 * 其实在编写代码时。每一个操作只是想告诉机器你想做什么，想让发号时令罢了。）
 *
 * 线程之间的通信方式 两种
 * 1： 共享内存
 *     线程之间共享程序的公共状态，线程之间通过写-读内 存中的公共状态来隐式进行通信
 * 2： 消息传递
 *     线程之间没有公共状态，线程之间必须通过明确的发送消息来显式进行通信
 *
 * 同步：程序用于控制不同线程之间操作发生相对顺序的机制
 * 共享内存-》同步是显式进行的
 * 共享内存-》同步是隐式进行的
 *
 * 显示和隐式的理解：显示代表程序员可以操作控制的，比如共享内存 程序员必须显示指定某个方法和
 * 某段代码在线程之间互斥执行，来操作共享内存
 * 而消息传递，消息的发送必须是在消息的接受之前所以是隐式的
 *
 * Java 的并发采用的是共享内存模型，Java 线程之间的通信总是隐式进行（理解
 * 通信的操作jvm已经定好的，但是同步是显示的。我们可以控制），整个通信过程对程序员完全透明。
 * 如果编写多线程程序的 Java 程序员不理解隐式进行的 线程之间通信的工作机制，很可能会遇到各种奇怪的内存可见性问题。
 *
 * JMM （java memory model java内存模型）
 *
 * 主内存和本地内存
 * 线程之间的共享变量存储在主内存
 * 每个线程私有的内存区交本地内存
 * 本地内存-》存储了该线程以读/写共享变量的副本，本地内存是JMM的一个抽象概念
 *     本地内存我也可以理解为工作内存，工作内存中存储了线程需要进行操作的变量，这些变量是
 *     主内存的工作副本，当线程对变量进行操作结束后，会刷新到主内存中
 * 主内存-》线程之间的内存共享区
 *
 *
 * 在java中，所有实例域，静态域，数据元素都存储在堆内存中
 * 堆内存在线程之间是共享的
 *
 * 方法中局部变量，方法定义参数，异常处理器参数不会在线程间共享，不会有内存可见性问题
 *
 *
 */
public class TestMain1 {
    public static void main(String[] args) {
//        testMethod1();
//        testMethod2();
//        testMethod3();
//        testMethod4();
        testMethod5();
    }


    /**
     * 验证实例域在堆内存中
     *
     * 都过结果可以发现，Test1 的实例化对象虽然是在
     * 主线程内实现的，但是线程1 和 线程2 都能够调用他的方法
     *
     */
    public static void testMethod1(){
        // 实例化一个类
        Test1 test1 = new Test1();

        Thread thread1= new Thread(()->{
            test1.testMethod1();
            Thread.dumpStack();
        });

        Thread thread2 = new Thread(()->{
            test1.testMethod1();
        });

        thread1.start();
        thread2.start();

        // 等待线程全部结束
        try {
            thread2.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread.dumpStack();
    }

    /**
     * 虚拟机对线程的创建过程
     * 比如下面的饿代码
     * 根据字节码的描述 invokespecial #17                 // Method javaBasedLearn/memoryModel/TestMain1$1."<init>":(Ljava/lang/String;Ljava/lang/String;)V
     * 发现在创建线程类的实例化对象时，如果内部类使用了局部变量
     * 在创建内部类的实例化对象时，会把两个局部变量的引用作为参数传入内部类中。这样内部类就拥有了
     * 局部变量的内存地址信息，这样线程启动时，直接调用内部类的操作指令，实现内部类对外部类的修改
     */
    public static void testMethod2(){
        String a = "dd";
        String b = "dd";
        Thread thread1= new Thread(()->{
            a.equals("dd");
            b.length();
            Thread.dumpStack();
        });
        thread1.start();
    }

    public static void testMethod3(){
        String a = "dd";
        String b = "dd";
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                a.equals("dd");
                b.length();
                Thread.dumpStack();
            }
        });
        thread1.start();
    }

    /**
     * 上面方法testMethod3 里面的编译后的字节码
     * 和下面方法的逻辑是一样的，只是我们看不到，编译器为我们
     * 做了处理，这样我们就知道内部类调用局部变量的逻辑了
     * 编译出的字节码两者也是相同的
     * 所以说上面方法的代码，与下面方法的代码是一样的
     * 当时上面代码的简洁度更高，这同样也是现在语言的发展趋势
     * 更加简洁，优雅
     * 其实编译器只是把代码逻辑给隐藏掉了
     */
    public static void testMethod4(){
        String a = "dd";
        String b = "dd";
        Thread thread1= new Thread(new Test2(a,b));
        thread1.start();
    }

     static class Test2 implements Runnable {
         String a;
         String b;

         public Test2(String a, String b) {
             this.a = a;
             this.b = b;
         }

         @Override
         public void run() {
             a.equals("dd");
             b.length();
             Thread.dumpStack();
         }
     }

    /**
     * 了解基本数据类型，本内部类调用的详情
     * 这样我们要对上面的代码进行一个更改
     * 内部类的代码应该是Test3内部类一样
     */
    public static void testMethod5(){
        String a = "dd";
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                // 从下面的代码可以看出，基本数据类型，只能调用他的方法。不能对他进行赋值操作
                a.equals("dd");
//                a = "dd";
                Thread.dumpStack();
            }
        });
        thread1.start();
     }

    /**
     * 在此测试同样不行
     * 所以推测虚拟机最后把内部类生成的字节码和Test3生成的字节码一样
     */
    public static void testMethod6(){
        Test1 test1 = new Test1();
        Thread thread1= new Thread(new Runnable() {
            @Override
            public void run() {
                // 从下面的代码可以看出，基本数据类型，只能调用他的方法。不能对他进行赋值操作
                test1.testMethod1();
//                test1 = new Test1();
                Thread.dumpStack();
            }
        });
        thread1.start();
    }

    static class Test3 implements Runnable {
        final String a;
        final String b;

        public Test3(String a, String b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            a.equals("dd");
            b.length();
            Thread.dumpStack();
        }
    }

}
