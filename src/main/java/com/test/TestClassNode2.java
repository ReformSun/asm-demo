package com.test;

import com.test.util.ByteUtil;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import static com.test.util.ASMUtil.convertToClassNode;
import static com.test.util.Common.testClassBaseUrl;

public class TestClassNode2 {
    static ClassNode classNode;
    public static void main(String[] args) {
        String classN = "com/test/testclass/TestClass.class";
        byte[] bytes = ByteUtil.getByteArray(testClassBaseUrl+classN);
        classNode = convertToClassNode(bytes);
//        testMethod1();
        testMethod2();
    }

    /**
     * 获取方法的签名
     */
    public static void testMethod1(){
        for (MethodNode methodNode:classNode.methods){
            System.out.println(methodNode.desc);
        }
    }

    /**
     * 获取类的属性
     */
    public static void testMethod2(){
        for (FieldNode fieldNode:classNode.fields){
            System.out.println(fieldNode.name);
        }
    }
}
