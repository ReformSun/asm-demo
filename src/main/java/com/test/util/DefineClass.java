package com.test.util;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class DefineClass {
    public static void defineByte(URL[] urls,byte[] bytes){
        URLClassLoader urlClassLoader = null;
        if (urls == null){
            urlClassLoader = (URLClassLoader) DefineClass.class.getClassLoader();
        }else {
            urlClassLoader = new URLClassLoader(urls);
        }

        try {
            InvokePrivateMethod.invokeMethod(urlClassLoader,"defineClass",new Object[]{bytes,0,bytes.length},"b",bytes.getClass(),int.class,int.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
