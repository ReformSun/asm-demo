package com.learnBasicFunction.writeJavaFile;

import com.test.testclass.TestClass;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class LearnMethodHandle {
    public static void main(String[] args) throws Throwable {
        MethodType methodType = MethodType.methodType(void.class,int.class);

        MethodHandle methodHandle = MethodHandles.lookup().findVirtual(TestClass.class,"testMethod1",methodType);
        methodHandle.invoke(TestClass.class.newInstance(),1);
    }
}
