package com.test.memoryModel;

import java.util.concurrent.Callable;

public class TestFinal {

    /**
     * 基本数据类型加final
     */
    private final String a = "";
    /**
     * 对象类型加final
     */
    private final Test2 test2 = new Test2();

    /**
     * 这种内部类和下面的内部类的区别同时被final修饰
     */
    private final Callable callable = new Callable() {
        @Override
        public Object call() throws Exception {
            return null;
        }
    };

    /**
     * 类加final
     */
    final class Test3{

    }

}
