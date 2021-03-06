package com.test.memoryModel;

/**
 *
 * 数据竞争和顺序一致性
 *
 * 数据竞争在java内存模型中的定义：
 * 1. 在一个线程中写一个变量
 * 2. 在另一个线程读同一个变量
 * 3. 而且写和读没有通过同步来排序
 * 当代码中出现数据竞争时，程序执行往往会出现违反直觉的结果
 *
 * 如果在多线程中程序能够正确同步，这个数据将不存在数据竞争
 *
 * JMM 对正确同步的多线程程序的内存一致性做了如下保证：
 *    如果程序是正确同步的，程序的执行将具有顺序一致性
 *    即程序的执行结果与该程序在顺序一致性内存模型中的执行结 果相同
 *
 * 常用同步原语 这里的同步是广义上的同步
 * synchronized，volatile 和 final
 *
 * 顺序一致性内存模型
 * 顺序一致性内存模型是一个被计算机科学家理想化了的理论参考模型
 * 它为程序员提供了极强的内存可见性保证
 *
 * 顺序一致性内存模型有两大特性:
 * 1. 一个线程中的所有操作必须按照程序的顺序来执行
 * 2. (不管程序是否同步)所有线程都只能看到一个单一的操作执行顺序
 *     在顺序一致性内存模型中，每个操作都必须原子执行且立刻对所有线程可见。
 *
 * 在顺序一致性模型
 *
 *
 *
 *
 *
 *
 */
public class TestMain5 {


    /**
     * 线程a和线程b对类进行不同的操作
     *
     * 这是一个正确同步的多线程程序
     * 根据 JMM 规范，该程序的执行结果将与该程序在 顺序一致性模型中的执行结果相同
     * 在jmm中的执行
     * a获取锁
     * 1        在这里叫做临界区 在这个区内操作1和2的执行顺序也可能是2 -> 1或者并行执行
     * 2        应为1和2操作没有数据依赖关系，编译器和处理器会进行优化，做到执行的并行化，提高执行效率和速度
     * a释放锁
     * b获取锁
     * 3        3和4可能重排序，两者虽然没有数据依赖关系但是拥有控制依赖关系，但是也可以重排序，
     * 4        具体看TestMain4
     * b释放锁
     *
     *
     * 在顺序一致性模型中
     *
     * * a获取锁
     * 1        按程序顺序执行
     * 2
     * a释放锁
     * b获取锁
     * 3        按程序顺序执行
     * 4
     * b释放锁
     *
     * 在顺序一致性模型中，所有操作完全按程序的顺序串行执行
     * 在 JMM 中，临界 区内的代码可以重排序(但 JMM 不允许临界区内的代码“逸出”到临界区之外， 那样会破坏监视器的语义)
     * JMM 会在退出临界区和进入临界区两个关键时间节点上做一些特别处理，使得线程在这两个时间点具有与顺序一致性模型相同
     * 的内存视图，虽然线程 A 在临界区内做了重排序，但由于监视器的 互斥执行的特性，这里的线程 B 根本无法“观察”到线程
     * A 在临界区内的重排序。 这种重排序既提高了执行效率，又没有改变程序的执行结果。
     *
     * 上面是同步程序的执行
     *
     * 下面是未同步程序的特性在jmm和顺序一致性模型特性的差别
     * 1 顺序一致性模型保证单线程内的操作会按程序的顺序执行，而 JMM 不保证单 线程内的操作会按程序的顺序执行(比如上面
     *   正确同步的多线程程序在临界区 内的重排序)。这一点前面已经讲过了，这里就不再赘述
     * 2 顺序一致性模型保证所有线程只能看到一致的操作执行顺序，而 JMM 不保证 所有线程能看到一致的操作执行顺序。这一点
     *   前面也已经讲过，这里就不再赘 述。
     * 3 JMM 不保证对 64 位的 long 型和 double 型变量的读/写操作具有原子性，而 顺序一致性模型保证对所有的内存读/
     *   写操作都具有原子性
     *
     *
     *
     */
    public static void main(String[] args) {
        TestMain5 testMain5 = new TestMain5();
        Thread threadA = new Thread(()->{
            testMain5.writer();
        });

        Thread threadB = new Thread(()->{
            testMain5.reader();
        });
    }

    int a = 0;
    boolean flag = false;

    public synchronized void writer() { // 获取锁
        a = 1; // 1
        flag = true; // 2
    }// 释放锁

    public synchronized void reader() {     //获取锁

         if (flag) {  // 3
             int i = a;  //4
         }
    }// 释放锁
}
