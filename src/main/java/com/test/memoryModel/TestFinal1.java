package com.test.memoryModel;

/**
 * 对于final域编译器和处理器遵守两个重排序规则
 *
 * 1. 在构造函数内对一个 final 域的写入，与随后把这个被构造对象的引用赋值给一个引用变量，这两个操作之间不能重排序
 * 2. 初次读一个包含 final 域的对象的引用，与随后初次读这个 final 域，这两个操作之间不能重排序。
 *
 * 个人理解：
 * 对于添加了final域的对象属性
 * 比如i是普通域
 * TestFinal1类的构造函数的执行，对普通域进行初始化操作。
 * 在构造函数中。执行了初始化操作。当时编译器有可能会优化执行顺序，把对普通域i的赋值
 * 重排序到构造函数之外，这是加入线程A进行写操作时。
 * 方法写操作分为两个步骤。1.构造TestFinal1类型的对象 2. 把这个对象的引用赋值给引用变量obj
 * 当线程A写操作时。这是B线程也进行读操作。但是当引用类型有值时。这时普通域由于重排序，放到了构造函数之外，
 * 这是B进行读操作时，读到的值很有可能是i的系统初始化值，而不是用户想要初始化的值，这个风险是一定存在的
 *
 *
 */
public class TestFinal1 {
    int i;
    final int j;
    static TestFinal1 obj;

    public TestFinal1() {
        i = 1;
        j = 2;
    }

    public static void writer(){ // 写线程A
        obj = new TestFinal1();
    }


    public static void reader(){ // 读线程B
        TestFinal1 testFinal = obj;
        int a =testFinal.i;
        int b = testFinal.j;
    }
}
