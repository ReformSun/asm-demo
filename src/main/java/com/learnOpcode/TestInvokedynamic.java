package com.learnOpcode;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestInvokedynamic {
    public static void main(String[] args) throws IOException {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        确认字节码头部信息
        classWriter.visit(Opcodes.V1_7,Opcodes.ACC_PUBLIC,"com/test/TestClass",null,"java/lang/Object",null);

//      定义类的方法
        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC,"testMethod","()V",null,null);

//        mv.visitInvokeDynamicInsn();


        classWriter.visitEnd();

        byte[] data = classWriter.toByteArray();
        File file = new File("/Users/apple/Documents/AgentJava/intellProject/LearnASM/src/test/testClassFile/com/test/TestClass.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}
