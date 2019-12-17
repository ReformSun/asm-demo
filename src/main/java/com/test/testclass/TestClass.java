package com.test.testclass;

import com.test.annotation.Test;
import com.test.annotation.Test1;
import com.test.annotation.TestTarget;

import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.List;

@Test(id=1)
@TestTarget
public class TestClass implements Serializable{
    private String a = "ddd";
//    static final long serialVersionUID = 6795334403749147491L;

    /**
     * 基本块的概念
     * 一个“基本块”（basic block）就是一个方法中的代码最长
     * 的直线型一段段代码序列。“直线型”也就是说代码序列中除了
     * 末尾之外不能有控制流（跳转）指令。
     *
     * 一个基本块的开头可以是方法的开头，也可以是某条跳转指令的跳转目标；
     *
     * 一个基本块的结尾可以是方法的末尾，也可以是某条跳转指令
     * （Java中就是goto、if*系列等；invoke*系列的方法调用指令不算在跳转指令中）。
     *
     * 类型检查器都需要一组栈帧映射 而栈帧映射来自于Code的属性StackMapTable
     * 这样的设计是为了保证在一个方法中每个基本块的起始位置都必须有一个栈帧映射
     * 这个栈帧映射提供了每个基本块起始处的操作数栈项和局部变量的验证类型
     *
     * 类型检查器会读取每个基本块对应的栈帧映射，并使用这些映射来为那些code属性中的
     * 指令生成类型检查的安全证明
     *
     * @param c
     */
    public TestClass() {
        // basic block 1 start
        int i = 0;
        int j = 0;
        if (i > 0) {
            int k = 0;
            // basic block 1 end
        }
        // basic block 2 start
        int l = 0;
        // basic block 2 end
    }

    public TestClass(String a) {
        this.a = a;
    }

    public String testMethod6(int a){
        if (a > 10){
            return "aa";
        }else {
            return "cc";
        }
    }

    public String testMethod6(){
        return "cc";
    }

    @Test1
    public String testMethod() {

//        String s1 = (@TestTarget String)s;
        return "ddd";
    }

    public void testMethod0(){
    }
    
    public void testMethod1(int i){
        System.out.println(i);
    }

    public void testMethod2(int a,int b){
        int  f = 1;
        int c = a+ b + f;
    }

    public void testMethod3(int a){
        if(a == 1){
            int b = a;
        }else {
            int b = a + 1;
        }
    }

    public void testMethod4(){

        Thread thread = new Thread(()->{
            System.out.println("ccc");
        });
        thread.start();
    }

    /**
     * 实例化方法中locals=1, args_size=1有无参数都会有最低一个
     * 静态方法就不同
     * 下面的方法 locals=0, args_size=0都为0
     * 因为在非静态方法中
     * 我们可以使用this方法直接访问自己实例化对象内的任何方法
     * 怎么实现这样的功能的那
     * 就是在编译器在编译的时候会把this关键字的访问转变成对普通方法参数的访问
     * 所以虚拟机调用实例化方法时，会自动传入此参数而已
     */
    public static void testMethod5(){
        
    }




    @Override
    public String toString() {
        System.out.println("aa");
        return super.toString();
    }
}
