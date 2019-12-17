package com.test.memoryModel;

/**
 * 并发线程中分析线程安全问题的切入点，可以从两个核心：
 * 1. JMM抽象内存模型和happens-before规则
 * 2. 三条性质，原子性，有序性，可见性
 *
 * 原子性：指一个操作是不可中断的，要么全部执行成功
 *
 * 原子性操作我们可以分到两个层面进行理解
 * 对单个基本数据类型的操作的原子性
 * 比如对单个int类型的读操作或者写操作是具有原子性的
 * 但是对于32位的处理器对于long或者double类型的操作，处理器把他们分为了两步
 * 所以操作是非原子性的但是对于jvm来说他对针对long或者double的读操作做了处理
 * 使读操作具有原子性
 * 其实jmm做的处理就是理解的第二个层面（对多个操作的执行过程的原子性）这种原子性不是在处理器
 * 层面的，是在jmm层面的。jmm通过做的特殊处理实现了在jmm层面的原子性
 *
 * 但是对于long或者double的操作如果在程序员层面实现实现原子性
 * 我们可以使用锁的方式，实现程序员层面的原子性
 *
 * 所以我们在理解原子性的时候要看到不同层面的原子性，这样就能理解各种文章提到的原子性
 *
 * 处理器层面的原子性是通过总线实现的
 *
 * jmm通过内存屏障实现操作的原子性 猜测
 *
 * 程序员层面实现操作的原子性是通过java给的一下关键字。或者直接加锁
 *
 *
 *
 * 可见性：
 * 在处理器层面：当一个数据被处理完成后。能够直接刷新到主内存中，对其它处理器立刻可见
 * 比如：jmm内存模型中添加的内存屏障：
 *      Load Barrier 既读屏障：在读指令前加上屏障，可以实现让高速缓冲区内的数据失效，重新从主内存中获取数据
 *      Store Barrier 即写屏障：在写指令之后添加屏障，可以让高速缓冲区内的数据立刻刷新到主内存中
 *      全能屏障，拥有既读屏障和即写屏障的功能
 *
 * 程序员层面：synchronzed加锁 当线程获取锁的时候。会从主内存中获取共享变量的最新值，当释放锁的时候会把共享变量的最新值
 *       刷新到主内存中
 *
 *
 * 有序性：
 *
 *    在程序员的层面：synchronzed加锁 当一个线程获取一系列操作的锁时。这些操作将被锁定。其他线程无法执行，只能等待
 *                  知道那个线程把锁释放。这样就保证了访问读写共享变量的串行化执行，
 *
 *
 *
 *
 *
 *
 */
public class TestMain6 {
    /**
     * 其实在处理java语言的指令集的时候，都是交给处理器进行处理的，处理器为了提高执行执行效率
     * 每个处理器都会有一个写缓冲区，
     * 它可以避免由于处理器停顿下来等待向内存写入数据而产生的延迟。
     * 同时，通过以批处理的方式刷新写缓冲区，以及合并写缓冲区中对同一内存地 址的多次写，可以减少对内存总线{@link com.test.memoryModel.TestMain7}的占用
     *
     * 虽然写缓冲区非常好但是每个写缓冲区仅仅对它的处理器可见的
     *
     * 猜猜下面那个是原子操作那个不是
     * int a = 10；
     * 这个操作只是一个赋值操作：把10这个值赋值给局部变量a
     * a++
     * 先把局部变量的a值读到处理器的读写缓冲区，然后把a的值加1，再把缓冲区内的值写入到a中
     *
     * int b=a
     * 先从线程栈帧局部变量表中获取a的值，在把a的值赋值给b
     *
     *
     *
     *
     */
    public static void testMethod1(){

        int a = 10; //1 是
        a++; //2
        int b=a; //3
        a = a+1; //4
    }


    public static void main(String[] args) {

        VolatileFeaturesExample2 volatileFeaturesExample2 = new VolatileFeaturesExample2();
        VolatileFeaturesExample volatileFeaturesExample = new VolatileFeaturesExample();
        TestMain7 testMain7 = new TestMain7();
        Thread thread = new Thread(()->{
            volatileFeaturesExample.getVl();
        });

        Thread thread2 = new Thread(()->{
            volatileFeaturesExample.getVl();
//            volatileFeaturesExample.setVl(22);
        });

        thread.start();
        thread2.start();


    }

    /**
     * 在jmm中 如果一个操作执行的结果需要对另一个操作可见，那么这两个操作之间必须存在
     * happens-before关系
     * happens-before与程序相关的规则
     * 1. 程序顺序规则：一个线程中的每个操作，happens-before于该线程中的任意后续操作
     * 2. 监视器锁规则：对于一个监视器锁的解锁，happens-before于随后对这个监视器加锁
     * 3. volatile变量规则：对一个volatile域的写，happens-before于任意后续对这个volatile域的读
     * 4. 传递性： 如果a happens-before b，且b happens-before c 那么a happens-before c
     *
     * 注意：两个操作具有happens-before关系，并不代表前一个操作要在后一个操作之前执行，happens-before
     * 仅仅要求前一个操作的执行结果对后一个操作可见，且前一个操作按顺序排在第二个操作之前
     * 注意的情况不是很了解：
     *
     *
     * 内存可见性的理解
     * 在java内存模型中。使用的线程通信方式是共享内存的方式。
     * 但是每个线程都会有自己的内存区。当对共享变量进行读写操作时。会先把共享内存中的变量在线程
     * 内存中创建一个副本，进行操作。然后再刷新到主内存中。但是这个过程就会出现线程中副本数据已经被修改但是
     * 主内存中的数据还没有被修改。这时候如果另一个线程去读取数据，还是原来的老数据。这样就出现了另个线程所看到的数据
     * 并不是已经被修改的数据，这样就说明了两个线程操作的不可见性的问题
     *
     * 解决不可见性的方式：
     * 当线程对数据进行修改后直接刷新到主内存中也就是直接执行刷新操作
     * 保证读和写操作的顺序性。就是当一个线程进行写操作时进行加锁，只有写操作进行完成另个线程才能进行读操作。
     * 以上两个方式都能实现操作的可见性
     *
     *
     *
     * 下面的代码假设有两个线程执行
     * 线程a和线程b
     * 线程a执行writer方法
     * 线程b执行reader方法
     *
     * 根据happens-before规则
     * 1. 根据程序次序规则 1 happens before 2； 3 happens before 4
     * 2. 根据volatile规则 2 happens before 3
     * 3. 根据happens-before的传递性规则 1 happens before 4
     *
     *
     * volatile 对于变量的读写的内存含义
     * 1. 当写一个volatile变量时，JMM会把该线程对应的本地内存中的共享变量值刷新到主内存中
     * 2. 当读一个volatile变量时，JMM会把该线程的本地内存置为无效，线程接下来将从主内存个中读取共享变量
     * 如果把volatile变量的读写综合起来看的话
     * 在读线程 B 读一 个 volatile 变量后，写线程 A 在写这个 volatile 变量之前所有可见的共享变量的值
     * 都将立即变得对读线程 B 可见。
     *
     *
     * volatile 并不能保证自增或者自减的原子性
     * volatile只能保证对于变量的读/写内存可见性和原子性
     * 对于volatile的复合操作不具有原子性
     *
     *
     *
     *
     */
    class TestC{
        int a = 0;
        volatile boolean flag = false;

        public  void writer() { // 获取锁
            a = 1; // 1
            flag = true; // 2
        }// 释放锁

        public  void reader() {     //获取锁

            if (flag) {  // 3
                int i = a;  //4
            }
        }// 释放锁
    }



    /**
     * 对于volatile关键字的理解
     * 当一个变量被volatile关键修饰时。
     *
     *
     */
    static class VolatileFeaturesExample{
        volatile long vl = 0L;

        public long getVl() {
            System.out.println("getVl" + Thread.currentThread());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return vl;
        }

        public void setVl(long vl) {
            System.out.println("setVl" + Thread.currentThread());
            this.vl = vl;
        }
    }

    static class VolatileFeaturesExample2{
        long vl = 0L;

        public synchronized long getVl() {
            System.out.println("getVl" + Thread.currentThread());
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return vl;
        }

        public synchronized void setVl(long vl) {
            System.out.println("setVl" + Thread.currentThread());
            this.vl = vl;
        }
    }
}
