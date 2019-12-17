package com.test.memoryModel;

/**
 * 1. 在构造函数内对一个 final 引用的对象的成员域的写入，与随后在构造函数外把
 * 这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序。
 * 个人理解：根据final对非引用类型的规则：对final域的写入和随后对final域的读不能重排序
 * 说明1和2不能重排序，根据上面对final域为引用类型的规则，1和3不能重排序，2和3也不能重排序
 * JMM 可以确保读线程 C 至少能看到写线程 A 在构造函数中对 final 引用对象的成 员域的写入。
 * 即 C 至少能看到数组下标 0 的值为 1。而写线程 B 对数组元素的写 入，读线程 C 可能看的到，
 * 也可能看不到。JMM 不保证线程 B 的写入对读线程 C 可见，因为写线程 B 和读线程 C 之间存在数据竞争，
 * 此时的执行结果不可预知。
 * 如果想要确保读线程 C 看到写线程 B 对数组元素的写入，写线程 B 和读线程 C 之间需要使用同步
 * 原语(lock 或 volatile)来确保内存可见性。
 */
public class TestFinal2 {
    final int[] intArray;
    static TestFinal2 testFinal2;

    public TestFinal2() { // 构造函数
        this.intArray = new int[1]; // 1
        this.intArray[0] = 1; // 2
    }

    public static void writerOne(){// 写线程A执行
        testFinal2= new TestFinal2(); // 3
    }

    public static void writerTwo(){// 写线程B执行
        testFinal2.intArray[0] = 2; // 4
    }

    public static void reader(){// 读线程C执行
        if (testFinal2 != null){ // 5
            int temp = testFinal2.intArray[0]; // 6
        }
    }


}
