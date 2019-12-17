package com.test;

import com.test.util.ByteUtil;
import com.test.util.InstructionType;
import com.test.util.OpcodesUtil;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodNode;

import java.util.Iterator;

import static com.test.util.ASMUtil.convertToClassNode;
import static com.test.util.Common.testClassBaseUrl;

public class TestMain {
    static ClassNode classNode;
    public static void main(String[] args) {
        String classN = "com/test/testclass/TestClass.class";
        byte[] bytes = ByteUtil.getByteArray(testClassBaseUrl+classN);
        classNode = convertToClassNode(bytes);
        testMethod1();
    }

    public static void testMethod1(){
        System.out.println(classNode.name);
        for (MethodNode methodNode:classNode.methods){
            System.out.println(methodNode.desc);
            System.out.println(methodNode.name);
        }
    }
}
