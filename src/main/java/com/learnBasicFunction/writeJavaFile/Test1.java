package com.learnBasicFunction.writeJavaFile;


import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Test1 {
    public static void main(String[] args) {
        try{
            testMethod();



        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void testMethod() throws IOException {
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
//        确认字节码头部信息
        classWriter.visit(Opcodes.V1_7,Opcodes.ACC_PUBLIC,"com/test/TestClass",null,"java/lang/Object",null);
//        定义类的属性
        classWriter.visitField(Opcodes.ACC_PRIVATE,"a","java/lang/String",null,"dddd").visitEnd();

        MethodVisitor mv = classWriter.visitMethod(Opcodes.ACC_PUBLIC,"<init>","()V",null,null);
//        methodVisitor.visitCode();
//        methodVisitor.visitVarInsn(Opcodes.ALOAD,0);
//        methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL,"java/lang/Object","<init>","()V");
//        methodVisitor.visitVarInsn(Opcodes.ALOAD,0);
//        methodVisitor.visitLdcInsn("ddd");
//        methodVisitor.visitFieldInsn(Opcodes.PUTFIELD,"com/test/TestClass","a","Ljava/lang/String");
//        methodVisitor.visitInsn(Opcodes.RETURN);
//        methodVisitor.visitMaxs(2,1);
//        methodVisitor.visitEnd();

        mv.visitCode();
        Label l0 = new Label();
        mv.visitLabel(l0);
        mv.visitLineNumber(7, l0);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
        Label l1 = new Label();
        mv.visitLabel(l1);
        mv.visitLineNumber(8, l1);
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitLdcInsn("ddd");
        mv.visitFieldInsn(Opcodes.PUTFIELD, "com/test/TestClass", "a", "Ljava/lang/String;");
        mv.visitInsn(Opcodes.RETURN);
        Label l2 = new Label();
        mv.visitLabel(l2);
        mv.visitLocalVariable("this", "Lcom/test/TestClass;", null, l0, l2, 0);
        mv.visitMaxs(2, 1);
        mv.visitEnd();



//        定义类的方法
        classWriter.visitMethod(Opcodes.ACC_PUBLIC,"testMethod","()V",null,null).visitEnd();
        classWriter.visitMethod(Opcodes.ACC_PUBLIC,"testMethod","(ID)V",null,null).visitEnd();

        classWriter.visitEnd();

        byte[] data = classWriter.toByteArray();
        File file = new File("/Users/apple/Documents/AgentJava/intellProject/LearnASM/src/test/testClassFile/com/test/TestClass.class");
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(data);
        fout.close();
    }
}
