package com.test.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokePrivateMethod {

    public static Method getMethod(Object instance, String methodName,String s,Class[] classTypes)
            throws   NoSuchMethodException {
        Method accessMethod = getMethod(instance.getClass(), methodName, classTypes);
        if (accessMethod == null){
            throw new NoSuchMethodException("没有：" + methodName + "这样的类");
        }
        //参数值为true，禁用访问控制检查
        accessMethod.setAccessible(true);
        return accessMethod;
    }
    private static Method getMethod(Class thisClass, String methodName, Class[] classTypes){
        boolean b = true;
        Class cls = thisClass;
        Method method = null;
        while (method == null){
            try {
                method = cls.getDeclaredMethod(methodName, classTypes);
            } catch (NoSuchMethodException e) {

            }
            if (method == null){
                cls = cls.getSuperclass();
            }
        }
        return method;

    }

    //调用无参
    public static Object invokeMethod(Object instance, String methodName, ClassLoader classLoader)
            throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Method method = instance.getClass().getDeclaredMethod(methodName,null);
        method.setAccessible(true);
        return method.invoke(instance,null);
    }

    //调用含单个参数的方法
    public static Object invokeMethod(Object instance, String methodName, Object arg,Class clas)
            throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        Object[] args = new Object[1];
        args[0] = arg;
        return invokeMethod(instance, methodName,args,"d",clas);
    }

    //调用含多个参数的方法
    public static Object invokeMethod(Object instance, String methodName, Object[] args,String s,Class...classes)
            throws NoSuchMethodException,
            IllegalAccessException, InvocationTargetException {
        System.out.println(s);
        return getMethod(instance, methodName,"e",classes).invoke(instance, args);
    }


    public static String classNameToPath(String className) {
        return className.replace(".","/") + ".class";
    }
}
