package com.learnBasicFunction.writeJavaFile;

import com.test.testclass.SubTestClass;
import com.test.testclass.TestClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test2 {
    public static void main(String[] args) {

        try{
            testMethod2();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void testMethod1() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TestClass testClass = new TestClass();
            Class cl = testClass.getClass();
            System.out.println("classNmae:" +  cl.getName());
            Method method  = cl.getDeclaredMethod("testMethod1",null);
            Method[] methods = cl.getMethods();
            System.out.println(method.getName());
            Class mehtodClass = method.getClass();
            Method method1 = mehtodClass.getDeclaredMethod("getGenericSignature",null);
            method1.setAccessible(true);
        System.out.println(method1.invoke(method,null));

    }

    public static void testMethod2() throws NoSuchMethodException {
        SubTestClass subTestClass = new SubTestClass();

        TestClass testClass = new TestClass();
        try {
            Method[] methods = subTestClass.getClass().getMethods();
            System.out.println(methods);
            Method[] methods1 = testClass.getClass().getMethods();
            System.out.println(methods1);

        }catch (SecurityException e) {
            e.printStackTrace();
        }

    }
}
