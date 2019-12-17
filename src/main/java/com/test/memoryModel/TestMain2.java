package com.test.memoryModel;

/**
 * 方法区
 * 还可以叫永久带，non-heap非堆
 * 是各个线程共享的区
 * 保存被虚拟机加载的类信息，常量，静态变量，编译器编译之后的代码
 *
 * 运行时常量区
 * 是方法区的一部分
 *
 *
 * 对象的内存布局
 * 1，对象头 header
 *    1，对象自身的运行时数据（哈希值，GC分代年龄，锁状态标志，线程持有的锁，偏向线程ID,偏向时间戳等）
 *    2， 类型指针 对象指向它的类元数据指针，通过这个指针可以确定这个对象属于哪个类的实例
 *    3，如果对象是数据，还有保存数据长度的数据
 * 2，实例数据 instance data
 * 3，对其填充 padding
 *
 *
 *
 * 对象的访问定位
 * 1，使用句柄
 * 2，直接指针
 *
 */
public class TestMain2 {
    static abstract class Human{

    }
    static class Man extends Human{

    }
    static class Woman extends Human{

    }

    public void sayHello(Human human){
        System.out.println("Hello guy");
    }
    public void sayHello(Man man){
        System.out.println("Hello gentlmen");
    }

    public void sayHello(Woman woman){
        System.out.println("Hello lady");
    }

    public static void main(String[] args) {
        // 实际类型
        Human man = new Man();
        Human woman = new Woman();

        TestMain2 testMain2 = new TestMain2();
        // 静态类型
        testMain2.sayHello(man);
        testMain2.sayHello(woman);
    }


}
