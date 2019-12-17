package com.test;

import com.test.customClassWriter.AgentClassWriter;
import com.test.util.ByteUtil;
import com.test.util.DefineClass;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.commons.SerialVersionUIDAdder;

import java.net.MalformedURLException;
import java.net.URL;

import static com.test.util.WriterUtil.*;

import static com.test.util.Common.testClassBaseUrl;

/**
 * 接口中不能定义普通属性
 * 只能定义静态常量类型
 * ACC_PUBLIC, ACC_STATIC, ACC_FINAL 被这三种修饰符修饰
 * 当我写的时候感觉可以写成public  int a = 0;
 * 当时编译器已经帮我们处理了这些操作，编译后增加上修饰符
 * 访问标识
 */
public class TestADDSerialVersionUID {

    private static byte[] bytes;
    public static void main(String[] args) {
        String classN = "com/test/testclass/TestClass.class";
        bytes = ByteUtil.getByteArray(testClassBaseUrl+classN);
//        testMethod1_0();
        testMethod1();
//       testMethod2();
//       testMethod3();
    }

    public static void testMethod1_0(){
        bytes = ByteUtil.getByteArray(testClassBaseUrl+"com/test/testclass/TestInterface.class");
        DefineClass.defineByte(null,bytes);
    }

    public static void testMethod1(){
        bytes = ByteUtil.getByteArray(testClassBaseUrl+"com/test/testclass/TestInterface.class");
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new ClassWriter(classReader,2);
        ClassVisitor cv = new SerialVersionUIDAdder(classWriter);
        classReader.accept(cv,8);
//        writer(classWriter.toByteArray(),"TestInterface.class");
        DefineClass.defineByte(null,classWriter.toByteArray());
    }

    public static void testMethod2(){
        ClassReader classReader = new ClassReader(bytes);
        ClassWriter classWriter = new AgentClassWriter(classReader,2,TestADDSerialVersionUID.class.getClassLoader());
        ClassVisitor cv = new SerialVersionUIDAdder(classWriter);
        classReader.accept(cv,8);
        DefineClass.defineByte(null,classWriter.toByteArray());
    }

    public static void testMethod3(){
        String baseUrl = "/Users/apple/Documents/AgentJava/intellProject/idaelJavaAgent/idealAgent";
        String baseU = baseUrl + "/src/test/testJarSource";
        String jarName = "struts2-core-2.5.20.jar";
        String jarName2 = "ognl-3.1.21.jar";
        String jarName3 = "ideal.jar";
        try {
            URL classUrl1 = new URL("file:"+ baseU + "/" + jarName);
            URL classUrl2 = new URL("file:"+ baseU + "/" + jarName2);
            URL classUrl3 = new URL("file:"+ baseU + "/" + jarName3);

//            URL[] urls = new URL[]{classUrl2,classUrl3};

            String className = "com/opensymphony/xwork2/ActionInvocation.class";
            String outClassName = "ActionInvocation";

            bytes = ByteUtil.getByteArray(classUrl1.getPath(),className);

            ClassReader classReader = new ClassReader(bytes);
//            ClassWriter classWriter = new AgentClassWriter(classReader,2,TestADDSerialVersionUID.class.getClassLoader());
            ClassWriter classWriter = new ClassWriter(classReader,2);
            ClassVisitor cv = new SerialVersionUIDAdder(classWriter);
            classReader.accept(cv,8);
            DefineClass.defineByte(null,classWriter.toByteArray());

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


    }

}
