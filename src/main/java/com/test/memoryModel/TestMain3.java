package com.test.memoryModel;

import java.io.Serializable;

/**
 * 静态分配
 */
public class TestMain3 {
    public static void main(String[] args) {
        TestMain3 testMain3 = new TestMain3();
        testMain3.sayHello('a');
    }

    public void sayHello(int a){
        System.out.println("Hello int");
    }
    public void sayHello(char a){
        System.out.println("Hello char");
    }
    public void sayHello(long a){
        System.out.println("Hello long");
    }
    public void sayHello(double a){
        System.out.println("Hello double");
    }
    public void sayHello(Character a){
        System.out.println("Hello Character");
    }
    public void sayHello(Object a){
        System.out.println("Hello Object");
    }
    public void sayHello(Serializable a){
        System.out.println("Hello Serializable");
    }
    public void sayHello(float a){
        System.out.println("Hello float");
    }
    public void sayHello(char... a){
        System.out.println("Hello char...");
    }
}
